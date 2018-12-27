package com.sid.pokegiciel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeagueController {
    @RequestMapping(value = "/leagues")
    public String leagues() {
        return "leagues";
    }
}
