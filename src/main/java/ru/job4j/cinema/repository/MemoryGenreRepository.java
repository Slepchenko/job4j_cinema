package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Genre;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryGenreRepository implements GenreRepository {

    private final Map<Integer, Genre> genres = new HashMap<>() {
        {
            put(1, new Genre(1, "Боевик"));
            put(2, new Genre(2, "Драмма"));
            put(3, new Genre(3, "Вестерн"));
            put(4, new Genre(4, "Комедия"));
        }
    };

    @Override
    public Optional<Genre> findById(int id) {
        return Optional.ofNullable(genres.get(id));
    }

    @Override
    public Collection<Genre> findAll() {
        return genres.values();
    }

}
