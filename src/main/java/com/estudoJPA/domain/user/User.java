package com.estudoJPA.domain.user;

import java.math.BigDecimal;

import com.estudoJPA.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private BigDecimal balance;
    private String password;

    public User(UserDTO data) {
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.balance = data.getBalance();
        this.userType = data.getUserType();
        this.email = data.getEmail();
        this.password = data.getPassword();
    }
}
