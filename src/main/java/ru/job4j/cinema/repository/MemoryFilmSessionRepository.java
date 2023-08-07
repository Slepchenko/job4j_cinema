package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Session;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryFilmSessionRepository implements FilmSessionRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Session> sessions = new HashMap<>();

    public MemoryFilmSessionRepository() {
        LocalDateTime startTime1 = LocalDateTime.of(2023, Month.AUGUST, 1, 14, 0, 0);
        LocalDateTime finishTime1 = LocalDateTime.of(2023, Month.AUGUST, 1, 16, 0, 0);
        LocalDateTime startTime2 = LocalDateTime.of(2023, Month.AUGUST, 3, 12, 55, 0);
        LocalDateTime finishTime2 = LocalDateTime.of(2023, Month.AUGUST, 3, 14, 55, 0);
        LocalDateTime startTime3 = LocalDateTime.of(2023, Month.AUGUST, 25, 18, 0, 0);
        LocalDateTime finishTime3 = LocalDateTime.of(2023, Month.AUGUST, 25, 21, 0, 0);
        FilmRepository filmRepository = new MemoryFilmRepository();
        HallRepository hallRepository = new MemoryHallRepository();
        add(new Session(0, filmRepository.findById(1).get().getId(), hallRepository.findById(1).get().getId(), startTime1, finishTime1, 100));
        add(new Session(0, filmRepository.findById(2).get().getId(), hallRepository.findById(2).get().getId(), startTime2, finishTime2, 55));
        add(new Session(0, filmRepository.findById(3).get().getId(), hallRepository.findById(3).get().getId(), startTime3, finishTime3, 150));
    }

    @Override
    public Optional<Session> findById(int id) {
        return Optional.ofNullable(sessions.get(id));
    }

    @Override
    public Collection<Session> findAll() {
        return sessions.values();
    }

    private void add(Session session) {
        session.setId(nextId.incrementAndGet());
        sessions.put(session.getId(), session);
    }

}
