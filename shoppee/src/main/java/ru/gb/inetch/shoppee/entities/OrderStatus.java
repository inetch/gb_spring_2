package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

@Data
public class OrderStatus {
    private Long id;
    private Long name;

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("status_name", "name");
    }
}
