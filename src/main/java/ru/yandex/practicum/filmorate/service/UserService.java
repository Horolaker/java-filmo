package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUser(Integer id) {
        return userStorage.getUser(id);
    }

    public User delete(Integer id) {
        return userStorage.delete(id);
    }

    public void addFriend(Integer userId, Integer friendId) {
        log.debug("добавляем друга по id: {}", friendId);
        if (userId.equals(friendId)) {
            log.debug("Попытка добавить себя в друзья.");
            return;
        }
        User user = userStorage.getUser(userId);
        User friend = userStorage.getUser(friendId);
        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    public void deleteFriend(Integer userId, Integer friendId) {
        log.debug("удаляем друга по id: {}", friendId);
        User user = userStorage.getUser(userId);
        User friend = userStorage.getUser(friendId);
        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
    }

    public List<User> getFriends(Integer userId) {
        log.debug("получаем список друзей пользователя по id: {}", userId);
        Set<Integer> friendIds = userStorage.getUser(userId).getFriends();
        return friendIds.stream().map(userStorage::getUser).collect(Collectors.toList());
    }

    public List<User> getCommonFriends(Integer firstUserId, Integer secondUserId) {
        log.debug("получаем список общих друзей по id {} и {}", firstUserId, secondUserId);
        User firstUser = userStorage.getUser(firstUserId);
        User secondUser = userStorage.getUser(secondUserId);
        Set<Integer> firstUserFriends = new HashSet<>(firstUser.getFriends());
        firstUserFriends.retainAll(secondUser.getFriends());
        return firstUserFriends.stream()
                .map(userStorage::getUser)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}