package com.example.intergiciel.repository;

import com.example.intergiciel.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    League findById(Long id);
}
