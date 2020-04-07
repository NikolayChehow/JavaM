package com.javamentor.service;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String findAllUsers() {
        return restTemplate.getForObject("http://localhost:8080/admin/allUsers", String.class);

    }

    public User findOneByEmail(String email) {
        return restTemplate.getForObject("http://localhost:8080/admin/getUser/" + email, User.class);

    }

//    public String findAllRoles() {
//        return restTemplate.getForObject("http://localhost:8080/getAllRoles", String.class);
//    }

//    public String findUserById(long id) {
//        return restTemplate.getForObject("http://localhost:8080/getUser/" + id, String.class);
//    }

    public Integer deleteUser(String[] items) {
        HttpEntity<String[]> requestUpdate = new HttpEntity<>(items);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/admin/deleteUsers/",
                HttpMethod.POST, requestUpdate, Object.class);
        return responseEntity.getStatusCodeValue();
    }

    public Integer addUser(UserForm userForm) {
        HttpEntity<UserForm> requestUpdate = new HttpEntity<>(userForm);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/admin/addUser",
                HttpMethod.PUT, requestUpdate, Object.class);
        return responseEntity.getStatusCodeValue();
    }

    public Integer updateUsers(UserForm userForm) {
        HttpEntity<UserForm> requestUpdate = new HttpEntity<>(userForm);
        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/admin/updateUsers",
                HttpMethod.POST, requestUpdate, Object.class);
        return responseEntity.getStatusCodeValue();
    }
}
