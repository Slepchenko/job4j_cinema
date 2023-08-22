package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.HallService;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping ("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var filmSessionOption = hallService.findById(id);
        model.addAttribute("halls", filmSessionOption.get());
        return "halls/hallPresent";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("halls", hallService.findAll());
        return "halls/list";
    }
}
