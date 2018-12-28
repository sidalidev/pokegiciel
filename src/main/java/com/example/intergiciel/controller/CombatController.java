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


    @RequestMapping(value = "/combat/history", method = RequestMethod.GET)
    public String combatHistory(Model model) {
        model.addAttribute("combats", combatRepository.findAll());
        return "historique_des_combats";
    }

    @RequestMapping(value = "/combat", method = RequestMethod.GET)
    public String combat(Model model, @RequestParam("opponentId") Long opponentId) {
        model.addAttribute("adversaire", personageRepository.findById(opponentId));
        model.addAttribute("personages", personageRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        return "combat";
    }

    @RequestMapping(value = "/combat/simulation", method = RequestMethod.GET)
    public String combatSimulation(Model model, @RequestParam("caracterId") Long caracterId, @RequestParam("opponentId") Long opponentId) {
        CombatEntity combat = new CombatEntity();
        final PersonageEntity adversaire1 = personageRepository.findById(opponentId);
        final PersonageEntity adversaire2 = personageRepository.findById(caracterId);
        final PersonageEntity vainqueur;
        if (adversaire1.getPoints() > adversaire2.getPoints()) {
            vainqueur = adversaire1;
        } else {
            vainqueur = adversaire2;
        }

        combat.setAdversaire1(adversaire1.getName());
        combat.setAdversaire2(adversaire2.getName());
        combat.setVainqueur(vainqueur.getName());

        model.addAttribute("adversaire", combat.getAdversaire1());
        model.addAttribute("personage", combat.getAdversaire2());
        model.addAttribute("vainqueur", combat.getVainqueur());

        combatRepository.save(combat);
        return "simuler_un_combat";
    }

}
