package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryFilmsRepository implements FilmRepository {

    private AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Film> films = new HashMap<>();

    public MemoryFilmsRepository() {
        add(new Film(0, "good, bad, agly", "the best of the wild west", 1965, 3, 16, 145, 1));
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