package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;
import ru.job4j.cinema.repository.MemoryHallRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleHallService implements HallService {

    private static final SimpleHallService INSTANCE = new SimpleHallService();

    private final HallRepository hallRepository = new MemoryHallRepository();

    private SimpleHallService() {
    }

    public static SimpleHallService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Hall> findById(int id) {
        return hallRepository.findById(id);
    }

    @Override
    public Collection<Hall> findAll() {
        return hallRepository.findAll();
    }

}
