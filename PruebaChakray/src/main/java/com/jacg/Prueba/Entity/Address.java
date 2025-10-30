package com.jacg.Prueba.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private Long id;
    private String name;
    private String street;
    
    @JsonProperty("country_code")
    private String countryCode;
}