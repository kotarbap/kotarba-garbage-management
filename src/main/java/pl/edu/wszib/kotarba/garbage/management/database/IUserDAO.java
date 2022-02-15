package pl.edu.wszib.kotarba.garbage.management.database;

import pl.edu.wszib.kotarba.garbage.management.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
