package com.bajajfinserve.orders.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bajajfinserve.orders.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{

}
