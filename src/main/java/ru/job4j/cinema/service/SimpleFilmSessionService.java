package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.MemoryFilmSessionRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository memoryFilmSessionRepository;

    private SimpleFilmSessionService(MemoryFilmSessionRepository memoryFilmSessionRepository) {
        this.memoryFilmSessionRepository = memoryFilmSessionRepository;
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
