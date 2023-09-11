package com.Ravi.messageservice.controller;

import com.Ravi.messageservice.model.Message;
import com.Ravi.messageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Ravi.messageservice.service.UserServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.Ravi.messageservice.dto.Notification;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        if (!userServiceClient.validateUser(message.getSender()) || !userServiceClient.validateUser(message.getRecipient())) {
            return new ResponseEntity<>("Invalid sender or recipient.", HttpStatus.BAD_REQUEST);
        }
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        Notification notification = new Notification();
        notification.setUsername(message.getRecipient());
        notification.setMessage("You have a new message from " + message.getSender());
        restTemplate.postForEntity(notificationServiceUrl, notification, Notification.class);
        return new ResponseEntity<>("Message sent successfully.", HttpStatus.CREATED);
    }
}
