package com.javamentor.repositories;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import org.hibernate.annotations.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);

    User findUserById(Long id);

    Optional<User> findOneByEmail(String email);
}
