package com.sid.pokegiciel.service;

import com.sid.pokegiciel.model.Caracter;
import com.sid.pokegiciel.repository.CaracterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracterService {

    @Autowired
    private CaracterRepository caracterRepository;

    public void addCaracter(Caracter caracter) {
        caracterRepository.saveAndFlush(caracter);
    }

    public List<Caracter> getCaracters() {
        return caracterRepository.findAll();
    }
}
