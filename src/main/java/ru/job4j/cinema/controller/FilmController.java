package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.*;

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
    public String getAll(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/list";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var filmOptional = filmService.findById(id);
        var genreOptional = genreService.findById(filmOptional.get().getGenreId());
        if (filmOptional.isEmpty()) {
            model.addAttribute("message", "Фильм не найден");
            return "errors/404";
        }
        model.addAttribute("film", filmOptional.get());
        model.addAttribute("genre", genreOptional.get().getName());
        model.addAttribute("poster", posterService.getFileById(filmOptional.get().getFileId()).get().getPath());
        return "films/filmPage";
    }

}
