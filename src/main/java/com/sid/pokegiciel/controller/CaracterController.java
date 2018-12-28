package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.model.Caracter;
import com.sid.pokegiciel.repository.CaracterRepository;
import com.sid.pokegiciel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sid.pokegiciel.controller.AuthenticationController.getCurrentUsername;

@Controller
public class CaracterController {


    @Autowired
    private CaracterRepository caracterRepository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("caracters", caracterRepository.findAllByUser_Username(getCurrentUsername()));
        model.addAttribute("points", userService.findByUsername(getCurrentUsername()).getPoints());

        return "home";
    }


    @RequestMapping(value = "/caracters/post", method = RequestMethod.POST)
    public String addCaracter(@RequestParam("name") String name) {
        Caracter caracter = new Caracter();
        caracter.setName(name);
        caracter.setUser(userService.findByUsername(getCurrentUsername()));
        caracterRepository.save(caracter);
        return "redirect:/home";
    }

    @RequestMapping(value = "/caracters/edit", method = RequestMethod.GET)
    public String editCaracter(Model model, @RequestParam("caracterId") Long id) {
        Caracter caracter = caracterRepository.findById(id);
        model.addAttribute("caracter", caracter);
        return "caracters-edit";
    }

    @RequestMapping(value = "/caracter/put", method = RequestMethod.POST)
    public String putCaracter(@ModelAttribute("caracterForm") Caracter editedCaracter) {
        Caracter caracterToEdit = caracterRepository.findById(editedCaracter.getId());
        caracterToEdit.setName(editedCaracter.getName());
        caracterRepository.save(caracterToEdit);
        return "redirect:/home";
    }

    @RequestMapping(value = "/caracter/delete", method = RequestMethod.POST)
    public String deleteCaracter(@ModelAttribute("caracterForm") Caracter caracter) {
        caracterRepository.delete(caracter.getId());
        return "redirect:/home";
    }
}
