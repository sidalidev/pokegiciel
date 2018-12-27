package com.sid.pokegiciel.controller;

import com.sid.pokegiciel.model.Caracter;
import com.sid.pokegiciel.repository.CaracterRepository;
import com.sid.pokegiciel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaracterController {


    @Autowired
    private CaracterRepository caracterRepository;

    @Autowired
    private UserService userService;

    private static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("caracters", caracterRepository.findAllByUser_Username(getCurrentUsername()));
        return "home";
    }

//    @RequestMapping(value = "/caracters", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Caracter> getCaracters() {
//        return caracterRepository.findAllByUser_Username(getCurrentUsername());
//    }

    @RequestMapping(value = "/caracters/post", method = RequestMethod.POST)
    public String addCaracter(@RequestParam("name") String name) {
        Caracter caracter = new Caracter();
        caracter.setName(name);
        caracter.setUser(userService.findByUsername(getCurrentUsername()));
        caracterRepository.save(caracter);
        return "redirect:/home";
    }
}
