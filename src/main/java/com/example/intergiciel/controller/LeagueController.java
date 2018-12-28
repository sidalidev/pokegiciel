package com.example.intergiciel.controller;

import com.example.intergiciel.entity.LigueEntity;
import com.example.intergiciel.entity.PersonageEntity;
import com.example.intergiciel.entity.User;
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
        LigueEntity newLeague = new LigueEntity();
        newLeague.setName("LigueEntity 1");
        leagueRepository.save(newLeague);

        LigueEntity newLeague2 = new LigueEntity();
        newLeague2.setName("LigueEntity 2");
        leagueRepository.save(newLeague2);

        LigueEntity newLeague3 = new LigueEntity();
        newLeague3.setName("LigueEntity 3");
        leagueRepository.save(newLeague3);
    }

    @RequestMapping(value = "/leagues")
    public String getLeaguesPage(Model model) {
        final LigueEntity userLeague = userRepository.findByUsername(AuthenticationController.getCurrentUsername()).getLeague();
        final List<User> leagueUsers = userRepository.findAllByLeague_Id(userLeague.getId());

        List<PersonageEntity> caracters = new ArrayList<>();
        List<User> leagueUsersWithoutMe = new ArrayList<>();
        for (User user : leagueUsers) {
            if (user.getUsername() != AuthenticationController.getCurrentUsername()) {
                leagueUsersWithoutMe.add(user);
            }
        }

        for (User user : leagueUsersWithoutMe) {
            List<PersonageEntity> leagueCaracters = caracterRepository.findAllByUser_Username(user.getUsername());
            for (PersonageEntity leagueCaracter : leagueCaracters) {
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
        LigueEntity newLeague = new LigueEntity();
        newLeague.setName(name);
        leagueRepository.save(newLeague);
        return "redirect:/leagues";
    }

    @RequestMapping(value = "/leagues/put", method = RequestMethod.POST)
    public String putLeague(@RequestParam("id") Long id) {
        final User user = userRepository.findByUsername(AuthenticationController.getCurrentUsername());
        final LigueEntity league = leagueRepository.findById(id);
        user.setLeague(league);
        userRepository.save(user);
        return "redirect:/leagues";
    }
}
