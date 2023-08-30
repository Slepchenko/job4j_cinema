package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.HallService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping ({"/", "/{id}"})
    public String getById(Model model, @PathVariable int id,  HttpSession session) {
        checkInMenu(model, session);
        var filmSessionOption = hallService.findById(id);
        model.addAttribute("halls", filmSessionOption.get());
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
