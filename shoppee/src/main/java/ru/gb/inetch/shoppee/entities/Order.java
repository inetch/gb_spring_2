package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Integer statusId;

    public final static String TABLE_NAME = "ord_order";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("user_id", "userId");
        COLUMN_MAPPINGS.put("status_id", "statusId");
    }
}
