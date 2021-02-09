package ru.gb.inetch.shoppee.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class Role {
	private Long id;
	private String name;

	public Role() {
	}

	public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

	static {
		COLUMN_MAPPINGS.put("id", "id");
		COLUMN_MAPPINGS.put("role_name", "name");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
