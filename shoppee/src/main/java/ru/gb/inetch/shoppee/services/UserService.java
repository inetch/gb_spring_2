package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.entities.SystemUser;
import ru.gb.inetch.shoppee.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    User findById(Long id);
    void save(SystemUser systemUser);
    void save(User systemUser);
    List<User> getAllUsers();
    boolean isGranted(User user, Collection<String> roles);
}
