package com.javamentor.forms;

import com.javamentor.models.Role;
import com.javamentor.models.State;
import lombok.Data;

@Data
public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private State state;
    private Long id;

}
