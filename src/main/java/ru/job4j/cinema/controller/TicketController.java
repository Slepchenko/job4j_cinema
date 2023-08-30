package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final FilmSessionService filmSessionService;

    public TicketController(TicketService ticketService, FilmSessionService filmSessionService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        checkInMenu(model, session);
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("filmSessions", filmSessionService.findAll());
        return "tickets/buyTicket";
    }

    @GetMapping("/")
    public String getById(Model model, @PathVariable int id, HttpSession session) {
        checkInMenu(model, session);
        var ticketOptional = ticketService.findById(id);
        if (ticketOptional.isEmpty()) {
            model.addAttribute("message", "Билет не найден");
            return "errors/404";
        }
        model.addAttribute("tickets", ticketOptional.get());
        return "tickets/buyTicket";
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
