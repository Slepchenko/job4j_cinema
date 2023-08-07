package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryHallRepository implements HallRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Hall> halls = new HashMap<>();

    public MemoryHallRepository() {
        add(new Hall(0, "Красный", 3, 12, "Бюджетные места"));
        add(new Hall(0, "Зелёный", 5, 25, "Бюджетные места"));
        add(new Hall(0, "VIP", 2, 4, "VIP места"));
    }

    @Override
    public Optional<Hall> findById(int id) {
        return Optional.ofNullable(halls.get(id));
    }

    @Override
    public Collection<Hall> findAll() {
        return halls.values();
    }

    private void add(Hall hall) {
        hall.setId(nextId.incrementAndGet());
        halls.put(hall.getId(), hall);
    }

}
