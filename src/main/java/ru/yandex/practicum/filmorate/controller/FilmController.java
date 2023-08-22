package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping //Возвращает список фильмов
    public List<Film> findAll() {
        return filmService.getAllFilms();
    }

    @PostMapping //Добавляет фильм в список
    public Film add(@RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping //Обновляет информацию о фильме
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    @PutMapping("/{id}/like/{userId}") // Добавляет "лайк"
    public void addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}") // Удаляет "лайк"
    public void deleteLike(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular") // Возвращяет список популярных фильмов
    public List<Film> getPopular(@RequestParam(name = "count", defaultValue = "10") @Positive Integer count) {
        return filmService.getPopular(count);
    }

    @DeleteMapping("/{id}") // Удаляет фильм по Id
    public Film delete(@PathVariable @NotNull Integer id) {
        return filmService.delete(id);
    }
}
