package com.example.intergiciel.controller;

import com.example.intergiciel.entity.Caracter;
import com.example.intergiciel.entity.Fight;
import com.example.intergiciel.repository.CaracterRepository;
import com.example.intergiciel.repository.FightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FightController {
    @Autowired
    CaracterRepository caracterRepository;

    @Autowired
    FightRepository fightRepository;

    @RequestMapping(value = "/fight", method = RequestMethod.GET)
    public String fight(Model model, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("opponent", caracterRepository.findById(opponentId));
        model.addAttribute("caracters", caracterRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        return "fight";
    }

    @RequestMapping(value = "/fight/simulation", method = RequestMethod.GET)
    public String fightSumulation(Model model, @RequestParam("caracterId") Long caracterId, @RequestParam("opponentId") Long opponentId) {
        Fight fight = new Fight();
        final Caracter opponentOne = caracterRepository.findById(opponentId);
        final Caracter opponentTwo = caracterRepository.findById(caracterId);
        final Caracter winner;
        if (opponentOne.getPoints() > opponentTwo.getPoints()) {
            winner = opponentOne;
        } else {
            winner = opponentTwo;
        }

        fight.setOpponentOne(opponentOne.getName());
        fight.setOpponentTwo(opponentTwo.getName());
        fight.setWinner(winner.getName());

        model.addAttribute("opponent", fight.getOpponentOne());
        model.addAttribute("caracter", fight.getOpponentTwo());
        model.addAttribute("winner", fight.getWinner());

        fightRepository.save(fight);
        return "fight-simulation";
    }

    @RequestMapping(value = "/fight/history", method = RequestMethod.GET)
    public String fightHistory(Model model) {
        model.addAttribute("fights", fightRepository.findAll());
        return "fight-history";
    }
}
