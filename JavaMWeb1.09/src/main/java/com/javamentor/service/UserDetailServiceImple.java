package com.javamentor.service;

import com.javamentor.repositories.UsersRepository;
import com.javamentor.security.detail.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImple implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userCandidate = usersRepository.findOneByLogin(login);
//        if (userCandidate.isPresent()){
//            return new UserDetailImpl(userCandidate.get());
//        } else throw new IllegalArgumentException("User not found");
         return new UserDetailImpl(usersRepository.findOneByEmail(email)
                 .orElseThrow(IllegalArgumentException::new));
           }
}
