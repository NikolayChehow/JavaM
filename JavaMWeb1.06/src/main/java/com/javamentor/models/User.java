package com.javamentor.models;

import com.javamentor.forms.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email", unique = true)
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "name_role")
        private String nameRole;


        public static User from(UserForm form) {
                return User.builder()
                        .firstName(form.getFirstName())
                        .lastName(form.getLastName())
                        .email(form.getEmail())
                        .password(form.getPassword())
                        .nameRole(form.getNameRole())
                        .build();
        }
        public static User fromUpdate(UserForm form , Long id) {


                return User.builder()
                        .firstName(form.getFirstName())
                        .lastName(form.getLastName())
                        .email(form.getEmail())
                        .password(form.getPassword())
                        .nameRole(form.getNameRole())
                        .id(id)
                        .build();
        }

}
