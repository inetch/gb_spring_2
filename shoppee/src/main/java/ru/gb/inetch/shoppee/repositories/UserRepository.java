package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}
