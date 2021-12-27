package com.example.tomcatconfig.repository;

import com.example.tomcatconfig.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
