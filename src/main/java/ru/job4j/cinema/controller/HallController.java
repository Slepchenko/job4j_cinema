package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SimpleHallService;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService = SimpleHallService.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("halls", hallService.findAll());
        return "halls/list";
    }
}
