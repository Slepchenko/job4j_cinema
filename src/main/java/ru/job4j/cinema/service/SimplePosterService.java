package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.MemoryPosterRepository;
import ru.job4j.cinema.repository.PosterRepository;

import java.util.Optional;

@Service
public class SimplePosterService implements PosterService {

    PosterRepository posterRepository = new MemoryPosterRepository();

    @Override
    public Optional<File> getFileById(int id) {
        return posterRepository.findById(id);
    }

}
