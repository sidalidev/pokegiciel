package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.model.League;
import com.sid.pokegiciel.repository.LeagueRepository;
import com.sid.pokegiciel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

import static com.sid.pokegiciel.controller.AuthenticationController.getCurrentUsername;

@Controller
public class LeagueController {
    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    UserRepository userRepository;


    @PostConstruct
    private void postConstruct() {
        League newLeague = new League();
        newLeague.setName("Ligue 1");
        leagueRepository.save(newLeague);

        League newLeague2 = new League();
        newLeague2.setName("Ligue 2");
        leagueRepository.save(newLeague2);

        League newLeague3 = new League();
        newLeague3.setName("Ligue 3");
        leagueRepository.save(newLeague3);
    }

    @RequestMapping(value = "/leagues")
    public String getLeaguesPage(Model model) {
        model.addAttribute("leagues", leagueRepository.findAll());
        model.addAttribute("userLeague", userRepository.findByUsername(getCurrentUsername()).getLeague().getName());
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
