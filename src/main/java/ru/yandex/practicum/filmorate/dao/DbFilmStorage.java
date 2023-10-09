package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

public interface DbFilmStorage {
    /**
     * Поставить лайк
     */
    void addLike(Integer filmId, Integer userId);

    /**
     * Убрать лайк у фильма
     */
    void removeLike(Film film, Integer userId);

    /**
     * Получить 10 лучших фильмов
     */
    List<Film> getTopTenFilms(Integer count);

    /**
     * Добавить фильм
     */
    Film addFilm(Film film);

    /**
     * Обновить фильм
     */
    Film updateFilm(Film film);

    /**
     * Удалить жанры у фильма
     */
    boolean deleteAllGenresFromFilm(long filmId);

    /**
     * Добавить жанр фильму
     */
    boolean addGenreToFilm(long filmId, int genreId);

    /**
     * Найти все фильмы
     */
    List<Film> findAllFilms();

    /**
     * Получить фильм по id
     */
    Film getFilmById(Integer id);

    /**
     * Получить жанр по id
     */
    Genre getGenreById(int id);

    /**
     * Получить жанр фильма по id
     */
    List<Genre> getGenreByFilmId(int id);

    /**
     * Получить все жанры из списка
     */
    List<Genre> getAllGenres();

    Mpa getMpaById(int mpaId);

    /**
     * Получить список mpa
     */
    List<Mpa> getAllMpa();

    /**
     * Удалить фильм по id
     */
    void removeFilm(Integer id);

    /**
     * Удалить все фильмы
     */
    void removeAllFilms();

    /**
     * Добавить жанр
     */
    Film addFilmGenres(Film film);

    /**
     * Удалить жанр
     */
    void removeFilmGenres(int filmId);

    /**
     * Добавить фильму лайк
     */
    Film addFilmLikes(Film film);

}
