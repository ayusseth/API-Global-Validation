package main.service;

import main.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUser();

    Optional<User> getUser(int id);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
