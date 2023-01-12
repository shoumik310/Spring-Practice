package com.bajajfinserve.orders.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bajajfinserve.orders.model.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer>{

}
