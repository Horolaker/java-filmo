package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

public interface DbUserStorage {
    /**
     * Добавить юзера
     */
    User addUser(User user);

    /**
     * Обновить юзера
     */
    User updateUser(User user);

    /**
     * Получить всех юзеров
     */
    List<User> findAllUsers();

    /**
     *  Добавить друга
     */
    void addFriend(Integer userId, Integer friendId);

    /**
     * Удалить друга
     */
    void removeFriend(User user, Integer friendId);

    /**
     * Получить список общих друзей
     */
    ArrayList<User> getMutualFriends(Integer userId, Integer otherUserId);

    /**
     * Получить пользователя по id
     */
    User getUserById(Integer id);

    /**
     * Получить список всех друзей
     */
    List<User> findAllUserFriends(Integer userId);

    /**
     * Удалить пользователя по id
     */
    void removeUser(Integer id);

    /**
     * Удалить всех польхователей
     */
    void removeAllUsers();
}