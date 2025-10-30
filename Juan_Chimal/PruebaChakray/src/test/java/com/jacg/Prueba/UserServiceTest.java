package com.jacg.Prueba;

import com.jacg.Prueba.Exception.BadRequestException;
import com.jacg.Prueba.Exception.NotFoundException;
import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Repository.UserRepository;
import com.jacg.Prueba.Services.Encrypt;
import com.jacg.Prueba.Services.UserService;
import com.jacg.Prueba.Validations.NumberValidator;
import com.jacg.Prueba.Validations.RFCValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private Encrypt encrypt;
    
    @Mock
    private RFCValidator rfcVal;
    
    @Mock
    private NumberValidator NumberVal;
    
    @InjectMocks
    private UserService userService;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(UUID.randomUUID())
                .email("test@mail.com")
                .name("testuser")
                .phone("+52 55 123 456 78")
                .password("encrypted_password")
                .taxId("AARR990101XXX")
                .createdAt("29-10-2025 12:00:00")
                .addresses(new ArrayList<>())
                .build();
    }
    
    @Test
    void testGetAllUsers_WithoutFilters() {
        
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, null);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.get(0).getPassword());
        verify(userRepository, times(1)).findAll();
    }
    
    @Test
    void testGetAllUsers_WithSorting() {

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .email("aaa@mail.com")
                .name("aaa")
                .phone("+52 55 111 111 11")
                .taxId("BBBB990101XXX")
                .createdAt("29-10-2025 12:00:00")
                .build();
        
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser, user2));
        List<User> result = userService.getAllUsers("name", null);
        assertEquals(2, result.size());
        assertEquals("aaa", result.get(0).getName());
    }
    
    @Test
    void testGetAllUsers_WithFilter_Contains() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "name+co+test");
        assertEquals(1, result.size());
        assertTrue(result.get(0).getName().contains("test"));
    }
    
    @Test
    void testGetAllUsers_WithFilter_Equals() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "tax_id+eq+AARR990101XXX");
        assertEquals(1, result.size());
        assertEquals("AARR990101XXX", result.get(0).getTaxId());
    }
    
    @Test
    void testGetAllUsers_WithFilter_StartsWith_Email() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "email+sw+test");
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEmail().startsWith("test"));
    }
    
    @Test
    void testGetAllUsers_WithFilter_StartsWith_Phone() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "phone+sw+52");
        assertEquals(0, result.size());
    }
    
    @Test
    void testGetAllUsers_WithFilter_StartsWith() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "phone+sw++52");
        assertEquals(1, result.size());
        assertTrue(result.get(0).getPhone().startsWith("+52"));
    }
    
    @Test
    void testGetAllUsers_WithFilter_EndsWith() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        List<User> result = userService.getAllUsers(null, "email+ew+mail.com");
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEmail().endsWith("mail.com"));
    }
    
    @Test
    void testGetAllUsers_WithInvalidFilter() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        assertThrows(BadRequestException.class, () -> {
            userService.getAllUsers(null, "invalid_filter");
        });
    }
    
    @Test
    void testGetAllUsers_WithInvalidOperator() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        assertThrows(BadRequestException.class, () -> {
            userService.getAllUsers(null, "name+invalid+test");
        });
    }
    
    @Test
    void testGetAllUsers_WithInvalidField() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        assertThrows(BadRequestException.class, () -> {
            userService.getAllUsers(null, "invalid_field+co+test");
        });
    }
    
    @Test
    void testCreateUser_Success() {

        User newUser = User.builder()
                .email("new@mail.com")
                .name("newuser")
                .phone("+52 55 999 888 77")
                .password("plainPassword")
                .taxId("CCCC990101XXX")
                .build();
        
        when(rfcVal.isValid(any())).thenReturn(true);
        when(NumberVal.isValid(any())).thenReturn(true);
        when(userRepository.existsByTaxId(any())).thenReturn(false);
        when(encrypt.encrypt(any())).thenReturn("encrypted_password");
        when(userRepository.save(any())).thenReturn(newUser);
        User result = userService.createUser(newUser);
        assertNotNull(result);
        assertNull(result.getPassword());
        verify(encrypt, times(1)).encrypt("plainPassword");
        verify(userRepository, times(1)).save(any());
    }
    
    @Test
    void testCreateUser_InvalidRFC() {

        User newUser = User.builder()
                .email("new@mail.com")
                .name("newuser")
                .phone("+52 55 999 888 77")
                .password("plainPassword")
                .taxId("INVALID")
                .build();
        
        when(rfcVal.isValid(any())).thenReturn(false);
        assertThrows(BadRequestException.class, () -> {
            userService.createUser(newUser);
        });
    }
    
    @Test
    void testCreateUser_InvalidPhone() {

        User newUser = User.builder()
                .email("new@mail.com")
                .name("newuser")
                .phone("123")
                .password("plainPassword")
                .taxId("CCCC990101XXX")
                .build();
        
        when(rfcVal.isValid(any())).thenReturn(true);
        when(NumberVal.isValid(any())).thenReturn(false);
        assertThrows(BadRequestException.class, () -> {
            userService.createUser(newUser);
        });
    }
    
    @Test
    void testCreateUser_DuplicateTaxId() {

        User newUser = User.builder()
                .email("new@mail.com")
                .name("newuser")
                .phone("+52 55 999 888 77")
                .password("plainPassword")
                .taxId("AARR990101XXX")
                .build();
        
        when(rfcVal.isValid(any())).thenReturn(true);
        when(NumberVal.isValid(any())).thenReturn(true);
        when(userRepository.existsByTaxId(any())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> {
            userService.createUser(newUser);
        });
    }
    
    @Test
    void testUpdateUser_Success() {

        UUID userId = testUser.getId();
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "updatedName");
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);
        User result = userService.updateUser(userId, updates);
        assertNotNull(result);
        verify(userRepository, times(1)).save(any());
    }
    
    @Test
    void testUpdateUser_NotFound() {

        UUID userId = UUID.randomUUID();
        Map<String, Object> updates = new HashMap<>();
        
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            userService.updateUser(userId, updates);
        });
    }
    
    @Test
    void testDeleteUser_Success() {

        UUID userId = testUser.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
    
    @Test
    void testDeleteUser_NotFound() {

        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            userService.deleteUser(userId);
        });
    }
    
    @Test
    void testAuthenticate_Success() {

        String taxId = "AARR990101XXX";
        String plainPassword = "mypassword";
        String encryptedPassword = "encrypted";
        
        testUser.setPassword(encryptedPassword);
        
        when(userRepository.findByTaxId(taxId)).thenReturn(Optional.of(testUser));
        when(encrypt.decrypt(encryptedPassword)).thenReturn(plainPassword);
        User result = userService.authenticate(taxId, plainPassword);
        assertNotNull(result);
        assertNull(result.getPassword());
        assertEquals(taxId, result.getTaxId());
    }
    
    @Test
    void testAuthenticate_InvalidCredentials() {
        String taxId = "AARR990101XXX";
        when(userRepository.findByTaxId(taxId)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> {
            userService.authenticate(taxId, "password");
        });
    }
    
    @Test
    void testAuthenticate_WrongPassword() {

        String taxId = "AARR990101XXX";
        String plainPassword = "wrongpassword";
        String correctPassword = "mypassword";
        String encryptedPassword = "encrypted";
        
        testUser.setPassword(encryptedPassword);
        
        when(userRepository.findByTaxId(taxId)).thenReturn(Optional.of(testUser));
        when(encrypt.decrypt(encryptedPassword)).thenReturn(correctPassword);
        assertThrows(BadRequestException.class, () -> {
            userService.authenticate(taxId, plainPassword);
        });
    }
}