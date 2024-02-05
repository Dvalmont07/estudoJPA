package com.estudoJPA.services;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudoJPA.domain.user.User;
import com.estudoJPA.domain.user.UserType;
import com.estudoJPA.dtos.UserDTO;
import com.estudoJPA.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amaount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Tipo de usuário incorreto");
        }
        if (sender.getBalance().compareTo(amaount) < 0) {
            throw new Exception("Saldo insuficinete");
        }
    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    @SuppressWarnings("null")
    public User saveUser(User data) {
        return this.repository.save(data);
    }

    public User createUser(UserDTO data) {
        return this.saveUser(new User(data));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
