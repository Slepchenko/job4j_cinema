package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmsRepository implements FilmRepository {
    @Override
    public Optional<Film> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Film> findAll() {
        return null;
    }
}
