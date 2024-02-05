package com.estudoJPA.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudoJPA.domain.transaction.Transaction;
import com.estudoJPA.domain.user.User;
import com.estudoJPA.dtos.TransactionDTO;
import com.estudoJPA.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction create(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.getSenderId());
        User receiver = userService.findUserById(transaction.getReceiverId());

        userService.validateTransaction(sender, transaction.getValue());

        if (!authorizeTransaction(sender, transaction.getValue())) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getValue());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.getValue()));
        receiver.setBalance(receiver.getBalance().add(transaction.getValue()));

        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotification(sender, "Transação realizada com sucesso");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso");
        return repository.save(newTransaction);

    }

    public boolean authorizeTransaction(User sendUser, BigDecimal value) throws NullPointerException {

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
                "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc",
                Map.class);
        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {

            @SuppressWarnings("null")
            String messageString = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equals(messageString);
        }
        return false;
    }

    public List<Transaction> getAll() {
        return repository.findAll();
    }
}
