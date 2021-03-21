package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.User;

import java.util.Collection;

public interface UserRepository {
    User getUser(String userName);
    User getUser(Long id);
    Collection<User> getAll();
    void save(User user);
    void update(User user);
    Long create(User user);

    boolean isOnUser(User user, String rolesLine);
}
