package com.jacg.Prueba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacg.Prueba.Controller.UserController;
import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private UserService userService;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(UUID.randomUUID())
                .email("test@mail.com")
                .name("testuser")
                .phone("+52 55 123 456 78")
                .taxId("AARR990101XXX")
                .createdAt("29-10-2025 12:00:00")
                .addresses(new ArrayList<>())
                .build();
    }
    
    @Test
    void testGetUsers_WithoutParams() throws Exception {

        when(userService.getAllUsers(null, null)).thenReturn(Arrays.asList(testUser));
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@mail.com"))
                .andExpect(jsonPath("$[0].name").value("testuser"));
        
        verify(userService, times(1)).getAllUsers(null, null);
    }
    
    @Test
    void testGetUsers_WithSorting() throws Exception {

        when(userService.getAllUsers("name", null)).thenReturn(Arrays.asList(testUser));
        mockMvc.perform(get("/users?sortedBy=name"))
                .andExpect(status().isOk());
        
        verify(userService, times(1)).getAllUsers("name", null);
    }
    
    @Test
    void testGetUsers_WithFilter() throws Exception {

        when(userService.getAllUsers(null, "name+co+test")).thenReturn(Arrays.asList(testUser));
        mockMvc.perform(get("/users?filter=name+co+test"))
                .andExpect(status().isOk());
        
        verify(userService, times(1)).getAllUsers(null, "name+co+test");
    }
    
    @Test
    void testCreateUser() throws Exception {

        User newUser = User.builder()
                .email("new@mail.com")
                .name("newuser")
                .phone("+52 55 999 888 77")
                .password("password123")
                .taxId("CCCC990101XXX")
                .build();
        
        when(userService.createUser(any(User.class))).thenReturn(testUser);
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@mail.com"));
        
        verify(userService, times(1)).createUser(any(User.class));
    }
    
    @Test
    void testUpdateUser() throws Exception {

        UUID userId = testUser.getId();
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "updatedName");
        
        when(userService.updateUser(eq(userId), any())).thenReturn(testUser);
        mockMvc.perform(patch("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk());
        
        verify(userService, times(1)).updateUser(eq(userId), any());
    }
    
    @Test
    void testDeleteUser() throws Exception {

        UUID userId = testUser.getId();
        doNothing().when(userService).deleteUser(userId);
        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Usuario eliminado exitosamente"));
        
        verify(userService, times(1)).deleteUser(userId);
    }
}