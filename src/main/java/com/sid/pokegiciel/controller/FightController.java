package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.repository.CaracterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sid.pokegiciel.controller.AuthenticationController.getCurrentUsername;

@Controller
public class FightController {
    @Autowired
    CaracterRepository caracterRepository;

    @RequestMapping(value = "/fight", method = RequestMethod.GET)
    public String fight(Model model, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("opponent", caracterRepository.findById(opponentId));
        model.addAttribute("caracters", caracterRepository.findAllByUser_Username(getCurrentUsername()));
        return "fight";
    }

    @RequestMapping(value = "/fight/simulation", method = RequestMethod.GET)
    public String fightSumulation(Model model, @RequestParam("caracterId") Long caracterId, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("opponent", caracterRepository.findById(opponentId));
        model.addAttribute("caracter", caracterRepository.findById(caracterId));
        model.addAttribute("winner", caracterRepository.findById(caracterId));
        return "fight-simulation";
    }
}
