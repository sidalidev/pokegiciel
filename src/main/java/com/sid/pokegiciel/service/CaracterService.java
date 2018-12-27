package com.sid.pokegiciel.service;

import com.sid.pokegiciel.model.Caracter;
import com.sid.pokegiciel.repository.CaracterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaracterService {

    @Autowired
    private CaracterRepository caracterRepository;

    public void save(Caracter caracter) {
        caracterRepository.saveAndFlush(caracter);
    }
}
