package com.estudoJPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudoJPA.domain.user.User;
import com.estudoJPA.dtos.NotificationDTO;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        NotificationDTO notificationRequest = new NotificationDTO(user.getEmail(), message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
                "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6",
                notificationRequest,
                String.class);
    
        if (notificationResponse.getStatusCode() != HttpStatus.OK) {
            System.out.println("Erro ao enviar noificação");
            throw new Exception("Servico fora do ar");
        }

        System.out.println("Transação OK");
     
    }

}
