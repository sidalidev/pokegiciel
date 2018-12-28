package com.example.intergiciel.controller;

import com.example.intergiciel.auth.controller.AuthenticationController;
import com.example.intergiciel.entity.CombatEntity;
import com.example.intergiciel.entity.PersonageEntity;
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
    FightRepository combatRepository;

    @RequestMapping(value = "/combat", method = RequestMethod.GET)
    public String combat(Model model, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("opponent", caracterRepository.findById(opponentId));
        model.addAttribute("caracters", caracterRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        return "combat";
    }

    @RequestMapping(value = "/combat/simulation", method = RequestMethod.GET)
    public String combatSumulation(Model model, @RequestParam("caracterId") Long caracterId, @RequestParam("opponentId") Long opponentId) {
        CombatEntity combat = new CombatEntity();
        final PersonageEntity opponentOne = caracterRepository.findById(opponentId);
        final PersonageEntity opponentTwo = caracterRepository.findById(caracterId);
        final PersonageEntity winner;
        if (opponentOne.getPoints() > opponentTwo.getPoints()) {
            winner = opponentOne;
        } else {
            winner = opponentTwo;
        }

        combat.setOpponentOne(opponentOne.getName());
        combat.setOpponentTwo(opponentTwo.getName());
        combat.setWinner(winner.getName());

        model.addAttribute("opponent", combat.getOpponentOne());
        model.addAttribute("caracter", combat.getOpponentTwo());
        model.addAttribute("winner", combat.getWinner());

        combatRepository.save(combat);
        return "simuler_un_combat";
    }

    @RequestMapping(value = "/combat/history", method = RequestMethod.GET)
    public String combatHistory(Model model) {
        model.addAttribute("combats", combatRepository.findAll());
        return "historique_des_combats";
    }
}
