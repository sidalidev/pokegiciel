package com.example.intergiciel.controller;

import com.example.intergiciel.auth.controller.AuthenticationController;
import com.example.intergiciel.entity.CombatEntity;
import com.example.intergiciel.entity.PersonageEntity;
import com.example.intergiciel.repository.CombatRepository;
import com.example.intergiciel.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CombatController {
    @Autowired
    PersonageRepository personageRepository;

    @Autowired
    CombatRepository combatRepository;


    @RequestMapping(value = "/combat/simulation", method = RequestMethod.GET)
    public String combatSumulation(Model model, @RequestParam("caracterId") Long caracterId, @RequestParam("opponentId") Long opponentId) {
        CombatEntity combat = new CombatEntity();
        final PersonageEntity opponentOne = personageRepository.findById(opponentId);
        final PersonageEntity opponentTwo = personageRepository.findById(caracterId);
        final PersonageEntity winner;
        if (opponentOne.getPoints() > opponentTwo.getPoints()) {
            winner = opponentOne;
        } else {
            winner = opponentTwo;
        }

        combat.setAdversaire1(opponentOne.getName());
        combat.setAdversaire2(opponentTwo.getName());
        combat.setVainqueur(winner.getName());

        model.addAttribute("opponent", combat.getAdversaire1());
        model.addAttribute("caracter", combat.getAdversaire2());
        model.addAttribute("winner", combat.getVainqueur());

        combatRepository.save(combat);
        return "simuler_un_combat";
    }

    @RequestMapping(value = "/combat", method = RequestMethod.GET)
    public String combat(Model model, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("opponent", personageRepository.findById(opponentId));
        model.addAttribute("caracters", personageRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        return "combat";
    }

    @RequestMapping(value = "/combat/history", method = RequestMethod.GET)
    public String combatHistory(Model model) {
        model.addAttribute("combats", combatRepository.findAll());
        return "historique_des_combats";
    }
}
