package com.example.intergiciel.controller;

import com.example.intergiciel.auth.controller.AuthenticationController;
import com.example.intergiciel.auth.entity.User;
import com.example.intergiciel.auth.service.UserService;
import com.example.intergiciel.entity.PersonageEntity;
import com.example.intergiciel.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonageController {


    @Autowired
    private PersonageRepository personageRepository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/", "/accueil"}, method = RequestMethod.GET)
    public String accueil(Model model) {
        model.addAttribute("caracters", personageRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        model.addAttribute("points", userService.findByUsername(AuthenticationController.getCurrentUsername()).getPoints());

        return "accueil";
    }

    @RequestMapping(value = "/ajouter_un_personage", method = RequestMethod.GET)
    public String ajouterUnPersonage(Model model) {
        model.addAttribute("caracters", personageRepository.findAllByUser_Username(AuthenticationController.getCurrentUsername()));
        model.addAttribute("points", userService.findByUsername(AuthenticationController.getCurrentUsername()).getPoints());

        return "ajouter_un_personage";
    }


    @RequestMapping(value = "/caracters/post", method = RequestMethod.POST)
    public String addCaracter(@RequestParam("name") String name, @RequestParam("points") int points) {
        User currentUser = userService.findByUsername(AuthenticationController.getCurrentUsername());
        int currentUserPoints = currentUser.getPoints();
        if (currentUserPoints > points) {
            currentUser.setPoints(currentUserPoints - points);
            userService.save(currentUser);
            PersonageEntity caracter = new PersonageEntity();
            caracter.setName(name);
            caracter.setPoints(points);
            caracter.setUser(userService.findByUsername(AuthenticationController.getCurrentUsername()));
            personageRepository.save(caracter);
        }
        return "redirect:/accueil";
    }

    @RequestMapping(value = "/caracters/edit", method = RequestMethod.GET)
    public String editCaracter(Model model, @RequestParam("caracterId") Long id) {
        PersonageEntity caracter = personageRepository.findById(id);
        model.addAttribute("caracter", caracter);
        return "modifier_un_personage";
    }

    @RequestMapping(value = "/caracter/put", method = RequestMethod.POST)
    public String putCaracter(@ModelAttribute("caracterForm") PersonageEntity editedCaracter) {
        User currentUser = userService.findByUsername(AuthenticationController.getCurrentUsername());
        int currentUserPoints = currentUser.getPoints();
        int caracterPoints = editedCaracter.getPoints();
        if (currentUserPoints > caracterPoints) {
            int oldCaracterPoints = personageRepository.findById(editedCaracter.getId()).getPoints();
            if (oldCaracterPoints > caracterPoints) {
                currentUser.setPoints(currentUserPoints + (oldCaracterPoints - caracterPoints));
            } else {
                currentUser.setPoints(currentUserPoints - (caracterPoints - oldCaracterPoints));
            }
            userService.save(currentUser);
            PersonageEntity caracterToEdit = personageRepository.findById(editedCaracter.getId());
            caracterToEdit.setName(editedCaracter.getName());
            caracterToEdit.setPoints(editedCaracter.getPoints());
            personageRepository.save(caracterToEdit);
            return "redirect:/accueil";
        }
        return "redirect:/modifier_un_personage";
    }

    @RequestMapping(value = "/caracter/delete", method = RequestMethod.POST)
    public String deleteCaracter(@ModelAttribute("caracterForm") PersonageEntity caracter) {
        User currentUser = userService.findByUsername(AuthenticationController.getCurrentUsername());
        int currentUserPoints = currentUser.getPoints();
        int caracterPoints = personageRepository.findById(caracter.getId()).getPoints();
        currentUser.setPoints(currentUserPoints + caracterPoints);
        userService.save(currentUser);
        personageRepository.delete(caracter.getId());
        return "redirect:/accueil";
    }
}
