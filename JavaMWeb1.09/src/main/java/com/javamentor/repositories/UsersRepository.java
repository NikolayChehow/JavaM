package com.javamentor.repositories;

import com.javamentor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);

    User findUserById(Long id);

    Optional<User> findOneByEmail(String email);
}
