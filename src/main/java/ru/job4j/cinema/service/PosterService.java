package ru.job4j.cinema.service;

import ru.job4j.cinema.model.File;

import java.util.Optional;

public interface PosterService {

    Optional<File> getFileById(int id);

}
