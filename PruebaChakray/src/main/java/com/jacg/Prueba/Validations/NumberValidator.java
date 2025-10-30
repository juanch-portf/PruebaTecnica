package com.jacg.Prueba.Validations;

import org.springframework.stereotype.Component;

@Component
public class NumberValidator {
    
    public boolean isValid(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }

        String digitsOnly = number.replaceAll("[^\\d]", "");
        
        return digitsOnly.length() >= 10;
    }
}
