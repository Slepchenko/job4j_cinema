package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryFilmRepository implements FilmRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Film> films = new HashMap<>();

    public MemoryFilmRepository() {
        add(new Film(0, "film1", "desc1", 1965, 3, 16, 145, 1));
        add(new Film(0, "film2", "desc2", 2008, 1, 18, 120, 2));
        add(new Film(0, "film3", "desc3", 2021, 2, 12, 90, 1));
    }

    @Override
    public Optional<Film> findById(int id) {
        return Optional.ofNullable(films.get(id));
    }

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    private void add(Film film) {
        film.setId(nextId.incrementAndGet());
        films.put(film.getId(), film);
    }
}