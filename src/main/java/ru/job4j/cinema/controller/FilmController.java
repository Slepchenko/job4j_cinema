package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.*;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    private final GenreService genreService;

    private final PosterService posterService;

    public FilmController(FilmService filmService, GenreService genreService, PosterService posterService) {
        this.filmService = filmService;
        this.genreService = genreService;
        this.posterService = posterService;
    }

    @GetMapping
    public String getAll(Model model,  HttpSession session) {
        checkInMenu(model, session);
        model.addAttribute("films", filmService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "films/list";
    }

    @GetMapping({"/", "/{id}"})
    public String getById(Model model, @PathVariable int id,  HttpSession session) {
       checkInMenu(model, session);
        var filmOptional = filmService.findById(id);
        if (filmOptional.isEmpty()) {
            model.addAttribute("message", "Фильм не найден");
            return "errors/404";
        }
        var genreOptional = genreService.findById(filmOptional.get().getGenreId());
        model.addAttribute("film", filmOptional.get());
        model.addAttribute("genre", genreOptional.get().getName());
        model.addAttribute("poster", posterService.getFileById(filmOptional.get().getFileId()).get().getPath());
        return "films/filmPage";
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
