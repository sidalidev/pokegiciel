package com.example.intergiciel.repository;

import com.example.intergiciel.entity.CombatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombatRepository extends JpaRepository<CombatEntity, Long> {
}
