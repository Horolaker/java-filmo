package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

public interface FilmStorage {

    List<Film> getAllFilms();

    Film create(@Valid Film film);

    Film update(@Valid Film film);

    Film getFilm(Integer filmId);

    Film delete(Integer filmId);
}