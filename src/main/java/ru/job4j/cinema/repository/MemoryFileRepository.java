package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.File;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemoryFileRepository implements FileRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, File> files = new ConcurrentHashMap<>();

    public MemoryFileRepository() {
        File file1 = new File();
        file1.setId(1);
        file1.setName("file1");
        file1.setPath("/files/film_1.png");
        File file2 = new File();
        file1.setId(2);
        file1.setName("file1");
        file1.setPath("/files/film_2.png");
        File file3 = new File();
        file1.setId(3);
        file1.setName("file3");
        file1.setPath("/files/film_3.png");
        files.put(1, file1);
        files.put(2, file2);
        files.put(3, file3);
    }

    @Override
    public File save(File file) {
        file.setId(nextId.incrementAndGet());
        files.put(file.getId(), file);
        return file;
    }

    @Override
    public Optional<File> findById(int id) {
        return Optional.ofNullable(files.get(id));
    }

    @Override
    public boolean deleteById(int id) {
        return files.remove(id) != null;
    }

}
