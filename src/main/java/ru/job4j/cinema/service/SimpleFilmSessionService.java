package ru.job4j.cinema.service;

import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.MemoryFilmSessionRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleFilmSessionService implements FilmSessionService {

    private static final SimpleFilmSessionService INSTANCE = new SimpleFilmSessionService();

    private final FilmSessionRepository memoryFilmSessionRepository = new MemoryFilmSessionRepository();

    private SimpleFilmSessionService() {
    }

    public static SimpleFilmSessionService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        return memoryFilmSessionRepository.findById(id);
    }

    @Override
    public Collection<FilmSession> findAll() {
        return memoryFilmSessionRepository.findAll();
    }

}
