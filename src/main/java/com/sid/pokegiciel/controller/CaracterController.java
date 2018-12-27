package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.model.Caracter;
import com.sid.pokegiciel.service.CaracterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CaracterController {
    @Autowired
    private CaracterService caracterService;

    @RequestMapping(value = "/caracters", method = RequestMethod.GET)
    @ResponseBody
    public List<Caracter> getCaracters() {
        return caracterService.getCaracters();
    }

    @RequestMapping(value = "/add-caracter", method = RequestMethod.POST)
    public String addCaracter(@RequestParam("name") String name) {
        Caracter caracter = new Caracter();
        caracter.setName(name);
        caracterService.addCaracter(caracter);
        return "redirect:/home";
    }
}
