package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryFileRepository implements FileRepository {

    private final Map<Integer, File> files = new HashMap<>() {
        {
            put(1, new File("film1", "/film1.png"));
            put(2, new File("film2", "/film2.png"));
            put(3, new File("film3", "/film3.png"));
        }
    };

    @Override
    public Optional<File> findById(int id) {
        return Optional.ofNullable(files.get(id));
    }

}
