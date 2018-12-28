package com.example.intergiciel.repository;

import com.example.intergiciel.entity.PersonageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonageRepository extends JpaRepository<PersonageEntity, Long> {
    List<PersonageEntity> findAllByUser_Username(String username);

    PersonageEntity findById(Long id);
}
