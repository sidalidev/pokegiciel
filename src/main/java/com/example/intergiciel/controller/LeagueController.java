package com.example.intergiciel.controller;

import com.example.intergiciel.model.Caracter;
import com.example.intergiciel.model.League;
import com.example.intergiciel.model.User;
import com.example.intergiciel.repository.CaracterRepository;
import com.example.intergiciel.repository.LeagueRepository;
import com.example.intergiciel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeagueController {
    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CaracterRepository caracterRepository;

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
        final League userLeague = userRepository.findByUsername(AuthenticationController.getCurrentUsername()).getLeague();
        final List<User> leagueUsers = userRepository.findAllByLeague_Id(userLeague.getId());

        List<Caracter> caracters = new ArrayList<>();
        List<User> leagueUsersWithoutMe = new ArrayList<>();
        for (User user : leagueUsers) {
            if (user.getUsername() != AuthenticationController.getCurrentUsername()) {
                leagueUsersWithoutMe.add(user);
            }
        }

        for (User user : leagueUsersWithoutMe) {
            List<Caracter> leagueCaracters = caracterRepository.findAllByUser_Username(user.getUsername());
            for (Caracter leagueCaracter : leagueCaracters) {
                caracters.add(leagueCaracter);
            }
        }
        model.addAttribute("leagues", leagueRepository.findAll());
        model.addAttribute("userLeague", userLeague);
        model.addAttribute("leagueUsers", leagueUsersWithoutMe);
        model.addAttribute("caracters", caracters);

        return "leagues";
    }

    @RequestMapping(value = "/leagues/post", method = RequestMethod.POST)
    public String postLeague(@RequestParam("name") String name) {
        League newLeague = new League();
        newLeague.setName(name);
        leagueRepository.save(newLeague);
        return "redirect:/leagues";
    }

    @RequestMapping(value = "/leagues/put", method = RequestMethod.POST)
    public String putLeague(@RequestParam("id") Long id) {
        final User user = userRepository.findByUsername(AuthenticationController.getCurrentUsername());
        final League league = leagueRepository.findById(id);
        user.setLeague(league);
        userRepository.save(user);
        return "redirect:/leagues";
    }
}
