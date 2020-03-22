package com.javamentor.security.config;

import com.javamentor.handler.LoginSuccessHandler;
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


    @Qualifier("userDetailServiceImple")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**", "/hello").authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/signUp/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/static/css/**", "/js/**", "/images/**", "/static/css/**", "/static/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .usernameParameter("email")
//                .defaultSuccessUrl("/")
                .loginPage("/login");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
