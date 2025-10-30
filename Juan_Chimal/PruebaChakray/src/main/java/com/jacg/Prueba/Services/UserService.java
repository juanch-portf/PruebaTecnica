package com.jacg.Prueba.Services;

import com.jacg.Prueba.Exception.BadRequestException;
import com.jacg.Prueba.Exception.NotFoundException;
import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Repository.UserRepository;
import com.jacg.Prueba.Validations.NumberValidator;
import com.jacg.Prueba.Validations.RFCValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository rep;
    
    @Autowired
    private Encrypt encrypt;
    
    @Autowired
    private RFCValidator rfcVal;
    
    @Autowired
    private NumberValidator noV;
    
    public List<User> getAllUsers(String sortedBy, String filter) {
        List<User> users = rep.findAll();

        if (filter != null && !filter.isEmpty()) {
            users = applyFilter(users, filter);
        }

        if (sortedBy != null && !sortedBy.isEmpty()) {
            users = applySort(users, sortedBy);
        }

        users.forEach(user -> user.setPassword(null));
        
        return users;
    }
    
    private List<User> applyFilter(List<User> users, String filter) {
        String[] parts;

        if (filter.contains(" ")) {
            parts = filter.split("\\s+", 3);
        } else {
            parts = filter.split("\\+", 3);
        }
        
        if (parts.length != 3) {
            throw new BadRequestException("Filter format invalid. Use: field+operator+value (received: " + filter + ")");
        }
        
        String field = parts[0].trim();
        String operator = parts[1].trim();
        String value = parts[2].trim();
        
        return users.stream()
                .filter(user -> matchesFilter(user, field, operator, value))
                .collect(Collectors.toList());
    }
    
    private boolean matchesFilter(User user, String field, String operator, String value) {
        String fieldValue = getFieldValue(user, field);
        if (fieldValue == null) return false;
        
        return switch (operator) {
            case "co" -> fieldValue.contains(value);
            case "eq" -> fieldValue.equals(value);
            case "sw" -> fieldValue.startsWith(value);
            case "ew" -> fieldValue.endsWith(value);
            default -> throw new BadRequestException("Operator not supported: " + operator);
        };
    }
    
    private String getFieldValue(User user, String field) {
        return switch (field) {
            case "email" -> user.getEmail();
            case "id" -> user.getId().toString();
            case "name" -> user.getName();
            case "phone" -> user.getPhone();
            case "tax_id" -> user.getTaxId();
            case "created_at" -> user.getCreatedAt();
            default -> throw new BadRequestException("Field not supported: " + field);
        };
    }
    
    private List<User> applySort(List<User> users, String sortedBy) {
        Comparator<User> comparator = switch (sortedBy) {
            case "email" -> Comparator.comparing(User::getEmail);
            case "id" -> Comparator.comparing(User::getId);
            case "name" -> Comparator.comparing(User::getName);
            case "phone" -> Comparator.comparing(User::getPhone);
            case "tax_id" -> Comparator.comparing(User::getTaxId);
            case "created_at" -> Comparator.comparing(User::getCreatedAt);
            default -> throw new BadRequestException("Sort field not supported: " + sortedBy);
        };
        
        return users.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    
    public User createUser(User user) {
        
        if (!rfcVal.isValid(user.getTaxId())) {
            throw new BadRequestException("tax_id no tiene formato RFC válido");
        }
        
        
        if (!noV.isValid(user.getPhone())) {
            throw new BadRequestException("phone no cumple con el formato AndresFormat (10 dígitos mínimo)");
        }
        
        
        if (rep.existsByTaxId(user.getTaxId())) {
            throw new BadRequestException("tax_id ya existe");
        }
        
        
        if (user.getPassword() != null) {
            user.setPassword(encrypt.encrypt(user.getPassword()));
        }
        
        
        User savedUser = rep.save(user);
        
        savedUser.setPassword(null);
        
        return savedUser;
    }
    
    public User updateUser(UUID id, Map<String, Object> updates) {
        User user = rep.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        
        updates.forEach((key, value) -> {
            switch (key) {
                case "email" -> user.setEmail((String) value);
                case "name" -> user.setName((String) value);
                case "phone" -> {
                    String phone = (String) value;
                    if (!noV.isValid(phone)) {
                        throw new BadRequestException("phone no cumple con el formato AndresFormat");
                    }
                    user.setPhone(phone);
                }
                case "password" -> user.setPassword(encrypt.encrypt((String) value));
                case "tax_id" -> {
                    String taxId = (String) value;
                    if (!rfcVal.isValid(taxId)) {
                        throw new BadRequestException("tax_id no tiene formato RFC válido");
                    }
                    if (!taxId.equals(user.getTaxId()) && rep.existsByTaxId(taxId)) {
                        throw new BadRequestException("tax_id ya existe");
                    }
                    user.setTaxId(taxId);
                }
                case "addresses" -> user.setAddresses((List) value);
                default -> throw new BadRequestException("Campo no válido: " + key);
            }
        });
        
        User updatedUser = rep.save(user);
        updatedUser.setPassword(null);
        
        return updatedUser;
    }
    
    public void deleteUser(UUID id) {
        if (!rep.findById(id).isPresent()) {
            throw new NotFoundException("Usuario no encontrado");
        }
        rep.deleteById(id);
    }
    
    public User authenticate(String taxId, String password) {
        User user = rep.findByTaxId(taxId)
                .orElseThrow(() -> new BadRequestException("Credenciales inválidas"));
        
        String decryptedPassword = encrypt.decrypt(user.getPassword());
        
        if (!decryptedPassword.equals(password)) {
            throw new BadRequestException("Credenciales inválidas");
        }
        
        user.setPassword(null);
        return user;
    }
}