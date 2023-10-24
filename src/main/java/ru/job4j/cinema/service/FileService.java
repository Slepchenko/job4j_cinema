package ru.job4j.cinema.service;

import ru.job4j.cinema.model.File;

import java.util.Optional;

public interface FileService {

    Optional<File> getFileById(int id);

}
