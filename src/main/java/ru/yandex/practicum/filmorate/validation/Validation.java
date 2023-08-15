package ru.yandex.practicum.filmorate.validation;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Validation {
    public void validationFilm(Film film) { //Проверка валидности фильмов
        if (film.getName() == null || film.getName().isBlank()) {
            throw new ValidationException("Неверное название");
        } else if (film.getDescription() == null || film.getDescription().length() > 200) {
            throw new ValidationException("Неверное Описание");
        } else if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(LocalDate.parse("1895-12-28"))) {
            throw new ValidationException("Неверная дата релиза.");
        } else if (film.getDuration() == null || film.getDuration() <= 0) {
            throw new ValidationException("Указана неверная продолжительность фильма.");
        }
    }

    public void validationUser(User user) { //Проверка валидности user
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }
        if (user.getLogin() == null || user.getLogin().isBlank()) {
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("дата рождения не может быть в будущем");
        }
    }
}
