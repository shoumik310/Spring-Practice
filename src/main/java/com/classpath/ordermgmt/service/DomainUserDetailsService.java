package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.model.DomainUserDetails;
import com.classpath.ordermgmt.model.User;
import com.classpath.ordermgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static UsernameNotFoundException invalidUser() {
        return new UsernameNotFoundException("Invalid User");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(DomainUserDetailsService::invalidUser);
        //convert into UserDetails object which Spring Security can understand
        return new DomainUserDetails(user);
    }
}