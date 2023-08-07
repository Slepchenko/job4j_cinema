package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.repository.HallRepository;
import ru.job4j.cinema.repository.MemoryHallRepository;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallRepository hallRepository = new MemoryHallRepository();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("halls", hallRepository.findAll());
        return "halls/list";
    }
}
