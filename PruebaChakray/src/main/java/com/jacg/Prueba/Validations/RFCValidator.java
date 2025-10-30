package com.jacg.Prueba.Validations;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class RFCValidator {
    
    private static final Pattern RFC_PATTERN = Pattern.compile("^[A-ZÑ&]{4}\\d{6}[A-Z0-9]{3}$");
    
    public boolean isValid(String rfc) {
        if (rfc == null || rfc.isEmpty()) {
            return false;
        }
        return RFC_PATTERN.matcher(rfc.toUpperCase()).matches();
    }
}