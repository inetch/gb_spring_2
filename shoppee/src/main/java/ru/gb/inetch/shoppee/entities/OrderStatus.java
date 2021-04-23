package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

@Data
public class OrderStatus {
    private Long id;
    private Long name;

    public final static String TABLE_NAME = "ord_order_status";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("status_name", "name");
    }
}
