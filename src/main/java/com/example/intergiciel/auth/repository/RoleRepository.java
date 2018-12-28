package com.example.intergiciel.auth.repository;

import com.example.intergiciel.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
