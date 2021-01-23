package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String theRoleName);
}
