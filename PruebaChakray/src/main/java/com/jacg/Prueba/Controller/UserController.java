package com.jacg.Prueba.Controller;

import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API de gestión de usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Retorna una lista de usuarios con opciones de ordenamiento y filtrado"
    )
    public ResponseEntity<List<User>> getUsers(
            @Parameter(description = "Campo para ordenar: email, id, name, phone, tax_id, created_at")
            @RequestParam(required = false) String sortedBy,
            
            @Parameter(description = "Filtro: field+operator+value. Operadores: co (contains), eq (equals), sw (starts with), ew (ends with)")
            @RequestParam(required = false) String filter
    ) {
        List<User> users = userService.getAllUsers(sortedBy, filter);
        return ResponseEntity.ok(users);
    }
    
    @PostMapping
    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Crea un nuevo usuario en el sistema. El password será encriptado con AES256."
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PatchMapping("/{id}")
    @Operation(
        summary = "Actualizar un usuario",
        description = "Actualiza uno o más campos de un usuario existente"
    )
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID del usuario")
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates
    ) {
        User updatedUser = userService.updateUser(id, updates);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un usuario",
        description = "Elimina un usuario del sistema por su ID"
    )
    public ResponseEntity<Map<String, String>> deleteUser(
            @Parameter(description = "ID del usuario")
            @PathVariable UUID id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of(
            "message", "Usuario eliminado exitosamente",
            "id", id.toString()
        ));
    }
}