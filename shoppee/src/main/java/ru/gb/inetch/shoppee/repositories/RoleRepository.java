package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.Role;

public interface RoleRepository {
	Role getRole(String roleName);
	Role getRole(Long id);
	String getDefaultRoleName();
	Role getDefaultRole();
}
