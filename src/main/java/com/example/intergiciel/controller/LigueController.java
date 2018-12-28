package com.example.intergiciel.controller;

import com.example.intergiciel.auth.controller.AuthenticationController;
import com.example.intergiciel.auth.entity.User;
import com.example.intergiciel.auth.repository.UserRepository;
import com.example.intergiciel.entity.LigueEntity;
import com.example.intergiciel.entity.PersonageEntity;
import com.example.intergiciel.repository.LigueRepository;
import com.example.intergiciel.repository.PersonageRepository;
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
public class LigueController {
    @Autowired
    LigueRepository ligueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonageRepository personageRepository;

    @PostConstruct
    private void postConstruct() {
        LigueEntity league1 = new LigueEntity();
        league1.setName("Arene Eau");
        ligueRepository.save(league1);

        LigueEntity league2 = new LigueEntity();
        league2.setName("Arene Feu");
        ligueRepository.save(league2);
    }

    @RequestMapping(value = "/ligues")
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
            List<PersonageEntity> leagueCaracters = personageRepository.findAllByUser_Username(user.getUsername());
            for (PersonageEntity leagueCaracter : leagueCaracters) {
                caracters.add(leagueCaracter);
            }
        }
        model.addAttribute("ligues", ligueRepository.findAll());
        model.addAttribute("userLeague", userLeague);
        model.addAttribute("leagueUsers", leagueUsersWithoutMe);
        model.addAttribute("caracters", caracters);

        return "ligues";
    }


    @RequestMapping(value = "/ajouter_une_ligue", method = RequestMethod.GET)
    public String ajouterUneLigue() {
        return "/ajouter_une_ligue";
    }


    @RequestMapping(value = "/ligues/post", method = RequestMethod.POST)
    public String postLeague(@RequestParam("name") String name) {
        LigueEntity newLeague = new LigueEntity();
        newLeague.setName(name);
        ligueRepository.save(newLeague);
        return "redirect:/ligues";
    }

    @RequestMapping(value = "/ligues/put", method = RequestMethod.POST)
    public String putLeague(@RequestParam("id") Long id) {
        final User user = userRepository.findByUsername(AuthenticationController.getCurrentUsername());
        final LigueEntity league = ligueRepository.findById(id);
        user.setLeague(league);
        userRepository.save(user);
        return "redirect:/ligues";
    }
}
