package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int generateId = 1;

    @Override
    public User createUser(User user) {
        log.debug("создаем пользователя: {}", user);
        user.setId(generateId++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        log.debug("обновляем пользователя: {}", user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new RuntimeException("id не найден");
        }
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("получаем всех пользователей");
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUser(Integer userId) {
        log.debug("получаем пользователя по id: {}", userId);
        if (!users.containsKey(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с id=" + userId + " не найден!");
        }
        return users.get(userId);
    }

    @Override
    public User delete(Integer userId) {
        log.debug("удаляем пользователя по id: {}", userId);
        if (!users.containsKey(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с id=" + userId + " не найден!");
        }
        return users.remove(userId);
    }
}