package com.classpath.ordermgmt.config;

import com.classpath.ordermgmt.service.DomainUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DomainUserDetailsService userDetailsService;

    // setup users and permissions
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // configure authorization rules here
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers(GET,"/v1/orders/**")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers(POST,"/v1/orders/**")
                .hasRole("ADMIN")
                .antMatchers(PUT,"/v1/orders/**")
                .hasRole("ADMIN")
                .antMatchers(DELETE,"/v1/orders/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //configure users
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(bcryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Primary
    @Bean
    public PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}