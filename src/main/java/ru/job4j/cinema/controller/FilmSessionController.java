package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    private final FilmService filmService;

    private final HallService hallService;

    public FilmSessionController(FilmSessionService filmSessionService, FilmService filmService, HallService hallService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping
    public String getAll(Model model,  HttpSession session) {
        checkInMenu(model, session);
        model.addAttribute("filmSessions", filmSessionService.findAll());
        model.addAttribute("halls", hallService.findAll());
        return "sessions/list";
    }

    @GetMapping({"/", "/{id}"})
    public String getById(Model model, @PathVariable int id,  HttpSession session) {
        checkInMenu(model, session);
        var filmSessionOption = filmSessionService.findById(id);
        var filmOption = filmService.findById(filmSessionOption.get().getFilmId());
        var hallOption = hallService.findById(filmSessionOption.get().getHallId());
        if (filmSessionOption.isEmpty()) {
            model.addAttribute("message", "Фильм не найден");
            return "errors/404";
        }
        model.addAttribute("filmSessions", filmSessionOption.get());
        model.addAttribute("film", filmOption.get().getName());
        model.addAttribute("hall", hallOption.get().getName());
        return "sessions/sessionPage";
    }

    private void checkInMenu(Model model,  HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }

}
