package com.example.demo.configs;

import com.example.demo.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig{

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Value("${users.admin.authority}")
    String adminAuthority;

    @Value("${users.student.authority}")
    String studentAuthority;




    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/student_for_admin/**").hasAuthority(adminAuthority)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers("/student/**", "/transaction/**").hasAuthority(studentAuthority)
                .antMatchers("/book/search/**").hasAnyAuthority(studentAuthority, adminAuthority)
                .antMatchers("/book/**").hasAuthority(adminAuthority)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }


}
