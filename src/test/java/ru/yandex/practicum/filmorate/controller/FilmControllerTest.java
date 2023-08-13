package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private FilmController filmController;

    @BeforeEach
    public void setUp() {
        filmController = new FilmController();
    }

    @AfterEach
    public void tearDown() {
        filmController = null;
    }

    @Test
    void findAll() { // Тест вывода списка Films
        List<Film> allFilmsCreated = filmController.findAll();
        assertNotNull(allFilmsCreated);
    }

    @Test
    void add() { //Тест добавления Film
        Film createdFilm = filmController.add(new Film(1, "FilmName", "Description", LocalDate.of(1990, 11, 11), 2));
        assertNotNull(createdFilm.getId());
        assertNotNull(createdFilm.getName());
        assertNotNull(createdFilm.getDescription());
        assertNotNull(createdFilm.getDuration());
    }

    @Test
    void update() { // Тест обновления Film
        Film createdFilm = filmController.add(new Film(1, "FilmName", "Description", LocalDate.of(1990, 11, 11), 2));
        Film updatedFilm = new Film(1, "FilmNameUpdated", "Description", LocalDate.of(1990, 11, 11), 2);
        filmController.update(updatedFilm);
        assertNotNull(createdFilm.getId());
        assertNotNull(createdFilm.getName());
        assertNotNull(createdFilm.getDescription());
        assertNotNull(createdFilm.getDuration());
    }
}