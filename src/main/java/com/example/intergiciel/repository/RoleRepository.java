package com.example.intergiciel.repository;

import com.example.intergiciel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}