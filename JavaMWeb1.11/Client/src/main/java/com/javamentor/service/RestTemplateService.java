package com.javamentor.service;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestTemplateService {

    @Autowired
    RestTemplate restTemplate;


    public String findAllUsers() {
        return restTemplate.getForObject("http://localhost:8080/admin/allUsers", String.class);

    }
    public User findOneByEmail(String email) {
        return restTemplate.getForObject("http://localhost:8080/admin/getUser/" + email , User.class);

    }

//    public String findAllRoles() {
//        return restTemplate.getForObject("http://localhost:8080/getAllRoles", String.class);
//    }

//    public String findUserById(long id) {
//        return restTemplate.getForObject("http://localhost:8080/getUser/" + id, String.class);
//    }

    public Integer deleteUser(String [] items) {
        HttpEntity<String []> requestUpdate = new HttpEntity<>(items);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("http://localhost:8080/admin/deleteUsers/" ,
                HttpMethod.POST, requestUpdate, Integer.class);
        return responseEntity.getBody();
    }

    public Integer addUser(UserForm userForm) {
        HttpEntity<UserForm> requestUpdate = new HttpEntity<>(userForm);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("http://localhost:8080/admin/addUser",
                HttpMethod.PUT, requestUpdate, Integer.class);
        return responseEntity.getBody();
    }

    public int updateUsers(UserForm userForm) {
        return restTemplate.postForObject("http://localhost:8080/admin/updateUsers", userForm, Integer.class);
    }
}
