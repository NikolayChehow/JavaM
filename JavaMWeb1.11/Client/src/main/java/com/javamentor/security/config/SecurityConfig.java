package com.javamentor.security.config;

import com.javamentor.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Qualifier("userDetailServiceImple")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("a").password("a").roles("ADMIN");
     //  auth.inMemoryAuthentication().withUser("a@a").password("a").roles("ADMIN");
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication().withUser("admin@admin").password(passwordEncoder.encode("admin")).roles("ADMIN");

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**", "/hello").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .usernameParameter("email")
//                .defaultSuccessUrl("/")
                .loginPage("/login");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    }


}



