package ru.gb.inetch.shoppee.entities;

import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class User {
	private Long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;

	private Collection<Role> roles;

	public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();
	public static final String allTableColumns;
	public static final String updatableTableColumns;
	public static final String updatableEntityFields;
	public static final String updatePairs;

	static {
		COLUMN_MAPPINGS.put("id", "id");
		COLUMN_MAPPINGS.put("username", "userName");
		COLUMN_MAPPINGS.put("password", "password");
		COLUMN_MAPPINGS.put("first_name", "firstName");
		COLUMN_MAPPINGS.put("last_name", "lastName");
		COLUMN_MAPPINGS.put("email", "email");

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for (Map.Entry<String, String> entry : COLUMN_MAPPINGS.entrySet()) {
			sb1.append(entry.getKey()).append(",");
			if(!entry.getKey().equals("id")){
				sb2.append(entry.getKey()).append(",");
				sb3.append(":").append(entry.getValue()).append(",");
				sb4.append(entry.getKey()).append(" = :").append(entry.getValue()).append(",");
			}
		}
		allTableColumns 		= sb1.substring(0, sb1.length() - 2);
		updatableTableColumns 	= sb2.substring(0, sb2.length() - 2);
		updatableEntityFields	= sb3.substring(0, sb3.length() - 2);
		updatePairs				= sb4.substring(0, sb4.length() - 2);
	}

	public Long getId(){
		return this.id;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Collection<Role> getRoles() {
		return roles;
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

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + "*********" + '\''
				+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
				+ ", roles=" + roles + '}';
	}
}
