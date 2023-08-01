package ru.job4j.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

}
