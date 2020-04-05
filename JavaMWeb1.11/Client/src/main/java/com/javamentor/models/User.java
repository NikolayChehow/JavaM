package com.javamentor.models;

import com.javamentor.forms.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

        private Long id;

        private String firstName;

        private String lastName;

        private String email;

        private String password;

        private Role role;

        private State state;


        public static User from(UserForm form) {
                return User.builder()
                        .firstName(form.getFirstName())
                        .lastName(form.getLastName())
                        .email(form.getEmail())
                        .password(form.getPassword())
                        .role(form.getRole())
                        .state(form.getState())
                        .build();
        }
        public static User fromUpdate(UserForm form , Long id) {


                return User.builder()
                        .firstName(form.getFirstName())
                        .lastName(form.getLastName())
                        .email(form.getEmail())
                        .password(form.getPassword())
                        .role(form.getRole())
                        .state(form.getState())
                        .id(id)
                        .build();
        }

}
