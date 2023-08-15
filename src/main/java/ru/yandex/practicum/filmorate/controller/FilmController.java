package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.Validation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class FilmController {
    Validation validation = new Validation();
    private LinkedHashMap<Integer, Film> films = new LinkedHashMap<>();
    private Integer generatedFilmId = 1;

    @GetMapping("/films") //Возвращает список фильмов
    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    @PostMapping(value = "/films") //Добавляет фильм в список
    public Film add(@RequestBody Film film) {
        validation.validationFilm(film);
        film.setId(generatedFilmId++);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping("/films") //Обновляет информацию о фильме
    public Film update(@RequestBody Film film) {
        validation.validationFilm(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            return film;
        } else {
            throw new NotFoundException("Не найдено.");
        }
    }
}
