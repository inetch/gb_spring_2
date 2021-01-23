package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.entities.SystemUser;
import ru.gb.inetch.shoppee.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}
