package com.sid.pokegiciel.repository;

import com.sid.pokegiciel.model.Caracter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaracterRepository extends JpaRepository<Caracter, Long> {

    @Override
    <S extends Caracter> S saveAndFlush(S s);
}
