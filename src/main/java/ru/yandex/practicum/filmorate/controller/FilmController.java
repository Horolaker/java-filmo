package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import static ru.yandex.practicum.filmorate.validation.Validation.validationFilm;

import java.util.*;

@RestController
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private Integer generatedFilmId = 1;

    @GetMapping("/films") //Возвращает список фильмов
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping(value = "/films") //Добавляет фильм в список
    public Film add(@RequestBody Film film) {
        validationFilm(film);
        film.setId(generatedFilmId++);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping("/films") //Обновляет информацию о фильме
    public Film update(@RequestBody Film film) {
        validationFilm(film);
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            return film;
        } else {
            throw new NotFoundException("Не найдено.");
        }
    }
}
