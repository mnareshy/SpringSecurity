package com.springsecurity.auth.dao.repository;

import com.springsecurity.auth.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
