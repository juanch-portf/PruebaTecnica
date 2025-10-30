package com.jacg.Prueba.Repository;

import com.jacg.Prueba.Entity.Address;
import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Services.Encrypt;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    
    private final Map<UUID, User> users = new ConcurrentHashMap<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final ZoneId MADAGASCAR_ZONE = ZoneId.of("Indian/Antananarivo");
    
    private final Encrypt encrypt;
    
    public UserRepository(Encrypt encrypt) {
        this.encrypt = encrypt;
        initializeUsers();
    }
    
    private void initializeUsers() {
        // Password en texto plano: "password123"
        String encryptedPassword = encrypt.encrypt("password123");
        
        User user1 = User.builder()
                .id(UUID.randomUUID())
                .email("user1@mail.com")
                .name("user1")
                .phone("+1 55 555 555 55")
                .password(encryptedPassword)
                .taxId("AARR990101XXX")
                .createdAt(getCurrentMadagascarTime())
                .addresses(Arrays.asList(
                        Address.builder().id(1L).name("workaddress").street("street No. 1").countryCode("UK").build(),
                        Address.builder().id(2L).name("homeaddress").street("street No. 2").countryCode("AU").build()
                ))
                .build();
                
        User user2 = User.builder()
                .id(UUID.randomUUID())
                .email("user2@mail.com")
                .name("user2")
                .phone("+52 55 123 456 78")
                .password(encryptedPassword)
                .taxId("BBCC950505YYY")
                .createdAt(getCurrentMadagascarTime())
                .addresses(Arrays.asList(
                        Address.builder().id(3L).name("office").street("street No. 3").countryCode("MX").build()
                ))
                .build();
                
        User user3 = User.builder()
                .id(UUID.randomUUID())
                .email("user3@example.com")
                .name("user3")
                .phone("55 987 654 32")
                .password(encryptedPassword)
                .taxId("DDEE880808ZZZ")
                .createdAt(getCurrentMadagascarTime())
                .addresses(Arrays.asList(
                        Address.builder().id(4L).name("home").street("street No. 4").countryCode("US").build()
                ))
                .build();
                
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
    }
    
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }
    
    public Optional<User> findByTaxId(String taxId) {
        return users.values().stream()
                .filter(user -> user.getTaxId().equals(taxId))
                .findFirst();
    }
    
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(getCurrentMadagascarTime());
        }
        users.put(user.getId(), user);
        return user;
    }
    
    public void deleteById(UUID id) {
        users.remove(id);
    }
    
    public boolean existsByTaxId(String taxId) {
        return users.values().stream()
                .anyMatch(user -> user.getTaxId().equals(taxId));
    }
    
    public String getCurrentMadagascarTime() {
        return ZonedDateTime.now(MADAGASCAR_ZONE).format(FORMATTER);
    }
}
