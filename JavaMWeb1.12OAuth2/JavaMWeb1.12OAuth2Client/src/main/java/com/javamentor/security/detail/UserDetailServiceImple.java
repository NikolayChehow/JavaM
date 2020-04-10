package com.javamentor.security.detail;

import com.javamentor.models.User;
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

        User userFindOneByEmail = restTemplateService.findOneByEmail(email);

        if (userFindOneByEmail != null) {
            return new UserDetailImpl(userFindOneByEmail);
        }
        User userFindOneByGoogleEmail = restTemplateService.findOneByGoogleEmail(email);

        if (userFindOneByGoogleEmail != null) {
            return new UserDetailImpl(userFindOneByGoogleEmail);
        }
        return null;


//                 .orElseThrow(IllegalArgumentException::new));


    }
}
