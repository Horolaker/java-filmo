package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserStorage {
    User createUser(@Valid User user);

    User updateUser(@Valid User user);

    List<User> getAllUsers();

    User getUser(Integer userId);

    User delete(Integer userId);
}