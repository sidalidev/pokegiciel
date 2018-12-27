package com.sid.pokegiciel.repository;

import com.sid.pokegiciel.model.Caracter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracterRepository extends JpaRepository<Caracter, Long> {

    <S extends Caracter> S saveAndFlush(S s);
    
    List<Caracter> findAll();
}
