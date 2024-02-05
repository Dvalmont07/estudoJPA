package com.estudoJPA.dtos;

import java.math.BigDecimal;

import com.estudoJPA.domain.user.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private String email;
    private String password;
    private UserType userType;    
}
