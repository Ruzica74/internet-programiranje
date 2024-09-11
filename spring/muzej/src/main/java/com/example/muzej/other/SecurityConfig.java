package com.example.muzej.other;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.csrf().disable().antMatcher("/**").authorizeRequests();
        httpSecurity.cors().and().csrf().disable();
        httpSecurity.authorizeRequests().anyRequest().permitAll();
    }
}
