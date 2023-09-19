package com.kimuohs.buyit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimuohs.buyit.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByEmail(String email);
}
