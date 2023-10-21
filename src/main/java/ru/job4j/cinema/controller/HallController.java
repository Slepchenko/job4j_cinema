package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.SimpleHallService;

import javax.servlet.http.HttpSession;
import java.util.List;

@ThreadSafe
@Controller
@RequestMapping("/halls")
public class HallController {

    private final SimpleHallService hallService;

    public HallController(SimpleHallService hallService, FilmService filmService) {
        this.hallService = hallService;
    }

    @GetMapping ({"/", "/{id}"})
    public String getById(Model model, @PathVariable int id,  HttpSession session) {
        checkInMenu(model, session);
        var hallSessionOption = hallService.findById(id);
        model.addAttribute("hall", hallSessionOption.get().getName());

        return "halls/hallPresent";
    }

    @GetMapping
    public String getAll(Model model,  HttpSession session) {
        checkInMenu(model, session);
        model.addAttribute("halls", hallService.findAll());
        return "halls/list";
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
