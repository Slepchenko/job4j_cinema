package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final FilmService filmService;

    private final FilmSessionService filmSessionService;

    private final HallService hallService;

    private final UserService userService;


    public TicketController(TicketService ticketService, FilmService filmService, FilmSessionService filmSessionService, HallService hallService, UserService userService) {
        this.ticketService = ticketService;
        this.filmService = filmService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getCreationPage(Model model, @PathVariable int id, HttpSession session) {
        checkInMenu(model, session);
        var filmSessionOption = filmSessionService.findById(id);
        var filmOption = filmService.findById(filmSessionOption.get().getFilmId());
        var hallOption = hallService.findById(filmSessionOption.get().getHallId());



        if (filmSessionOption.isEmpty()) {
            model.addAttribute("message", "Фильм не найден");
            return "errors/404";
        }
        model.addAttribute("filmSession", filmSessionOption.get());
        model.addAttribute("film", filmOption.get().getName());
        model.addAttribute("hall", hallOption.get().getName());
        model.addAttribute("rows", getRows(hallOption.get().getRowCount()));
        model.addAttribute("places", getPlaces(hallOption.get().getPlaceCount()));

        return "/tickets/create";
    }

    @PostMapping("/create")
    public String saveTicket(Model model, @ModelAttribute Ticket ticket) {
        try {
            var ticketOption = ticketService.save(ticket);
            model.addAttribute("message", "Билет куплен");
            return "/tickets/buySuccessfully";
        } catch (Exception e) {
            model.addAttribute("message", "Не удалось приобрести билет на заданное место."
                    + " Вероятно оно уже занято. Перейдите на страницу бронирования билетов и попробуйте снова.");
            return "errors/404";
        }
    }

    private List<Integer> getRows(int rows) {
        List<Integer> rowsList = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            rowsList.add(i);
        }
        return rowsList;
    }

    private List<Integer> getPlaces(int places) {
        List<Integer> placesList = new ArrayList<>();
        for (int i = 1; i <= places; i++) {
            placesList.add(i);
        }
        return placesList;
    }

    private void checkInMenu(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }

}
