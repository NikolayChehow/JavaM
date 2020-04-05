package com.javamentor.security.detail;

import com.javamentor.security.detail.UserDetailImpl;
import com.javamentor.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImple implements UserDetailsService {

    @Autowired
    private RestTemplateService restTemplateService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userCandidate = usersRepository.findOneByLogin(login);
//        if (userCandidate.isPresent()){
//            return new UserDetailImpl(userCandidate.get());
//        } else throw new IllegalArgumentException("User not found");
         return new UserDetailImpl(restTemplateService.findOneByEmail(email));

//                 .orElseThrow(IllegalArgumentException::new));

           }
}
