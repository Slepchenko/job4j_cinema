package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Session;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {

    Optional<Session> findById(int id);

    Collection<Session> findAll();

}
