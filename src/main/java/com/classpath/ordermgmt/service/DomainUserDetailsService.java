package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.exception.ResourceNotFoundException;
import com.classpath.ordermgmt.model.DomainUserDetails;
import com.classpath.ordermgmt.model.User;
import com.classpath.ordermgmt.repository.UserRepository;
import com.classpath.ordermgmt.util.BcryptPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Invalid User"));
        System.out.println("User from the repository ");
        System.out.println(user);
        //convert into UserDetails object which Spring Security can understand
        return new DomainUserDetails(user);
    }
}