package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

import java.util.Collection;

public @Data class User {
	private Long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;

	private Collection<Role> roles;

	public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap();

	static {
		COLUMN_MAPPINGS.put("id", "id");
		COLUMN_MAPPINGS.put("username", "userName");
		COLUMN_MAPPINGS.put("password", "password");
		COLUMN_MAPPINGS.put("first_name", "firstName");
		COLUMN_MAPPINGS.put("last_name", "lastName");
		COLUMN_MAPPINGS.put("email", "email");
	}

	public User() {
	}

	public User(String userName, String password, String firstName, String lastName, String email) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String userName, String password, String firstName, String lastName, String email,
			Collection<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}
}
