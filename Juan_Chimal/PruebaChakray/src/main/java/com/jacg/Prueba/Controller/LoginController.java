package com.jacg.Prueba.Controller;

import com.jacg.Prueba.Entity.LoginReq;
import com.jacg.Prueba.Entity.LoginRes;
import com.jacg.Prueba.Entity.User;
import com.jacg.Prueba.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "API de autenticación")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    @Operation(
        summary = "Autenticar usuario",
        description = "Autenticación usando tax_id como username y password"
    )
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginRequest) {
        try {
            User user = userService.authenticate(loginRequest.getTaxId(), loginRequest.getPassword());
            
            LoginRes response = new LoginRes(
                true,
                "Autenticación exitosa",
                user
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LoginRes response = new LoginRes(
                false,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(401).body(response);
        }
    }
}