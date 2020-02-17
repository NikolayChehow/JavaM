package com.javamentor.repositories;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import org.hibernate.annotations.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);


    @Modifying
//    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    void setUserInfoById(String firstname, String lastname, Integer userId);
    @Query("update User set firstName=?1, lastName=?2, email=?3, password=?4 , nameRole=?5 where id =?6")
    void setUserInfoById(String fn, String ln, String em , String ps, String nr, Long id);


}
