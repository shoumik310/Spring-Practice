package com.classpath.ordermgmt.repository;

import com.classpath.ordermgmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //load the user using the username
    Optional<User> findByUsername(String username);
}