package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.repository.MemoryFilmSessionRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionRepository sessionRepository = new MemoryFilmSessionRepository();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmSessions", sessionRepository.findAll());
        return "sessions/list";
    }

}
