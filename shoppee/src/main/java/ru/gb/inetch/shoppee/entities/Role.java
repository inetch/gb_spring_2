package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

public @Data class Role {
	private Long id;
	private String name;

	public final static String TABLE_NAME = "com_role";

	public Role() {
	}

	public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

	static {
		COLUMN_MAPPINGS.put("id", "id");
		COLUMN_MAPPINGS.put("role_name", "name");
	}

	public Role(String name) {
		this.name = name;
	}
}
