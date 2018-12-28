package com.example.intergiciel.repository;

import com.example.intergiciel.entity.LigueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<LigueEntity, Long> {
    LigueEntity findById(Long id);
}
