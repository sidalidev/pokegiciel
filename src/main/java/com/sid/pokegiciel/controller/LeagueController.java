package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.model.League;
import com.sid.pokegiciel.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeagueController {

    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(value = "/leagues")
    public String getLeaguesPage(Model model) {
        model.addAttribute("leagues", leagueRepository.findAll());
        return "leagues";
    }

    @RequestMapping(value = "/leagues/post", method = RequestMethod.POST)
    public String postLeague(@RequestParam("name") String name) {
        League newLeague = new League();
        newLeague.setName(name);
        leagueRepository.save(newLeague);
        return "redirect:/leagues";
    }
}
