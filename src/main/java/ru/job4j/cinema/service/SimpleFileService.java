package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.FileRepository;

import java.util.Optional;

@Service
public class SimpleFileService implements FileService {

    FileRepository fileRepository;

    public SimpleFileService(FileRepository sql2oFileRepository) {
        this.fileRepository = sql2oFileRepository;
    }

    @Override
    public Optional<File> getFileById(int id) {
        return fileRepository.findById(id);
    }

}
