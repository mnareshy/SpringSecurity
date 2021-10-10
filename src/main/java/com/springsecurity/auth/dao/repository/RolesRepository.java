package com.springsecurity.auth.dao.repository;

import com.springsecurity.auth.dao.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Integer> {
}
