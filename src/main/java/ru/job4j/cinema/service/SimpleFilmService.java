package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.MemoryFilmRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleFilmService implements FilmService {

    private static final SimpleFilmService INSTANCE = new SimpleFilmService();

    private final FilmRepository filmRepository = new MemoryFilmRepository();

    private SimpleFilmService() {
    }

    public static SimpleFilmService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Film> findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> findAll() {
        return filmRepository.findAll();
    }

}
