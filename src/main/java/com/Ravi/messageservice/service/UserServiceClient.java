package com.Ravi.messageservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://userservice/validateUser/";

    public boolean validateUser(String username) {
        try {
            Boolean userExists = restTemplate.getForObject(USER_SERVICE_URL + username, Boolean.class);
            return userExists != null && userExists;
        } catch (Exception e) {
            return false;
        }
    }
}
