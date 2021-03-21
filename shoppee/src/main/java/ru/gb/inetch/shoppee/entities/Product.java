package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

import javax.persistence.*;

@Data
public class Product {
    private Long id;
    private String title;
    private Long totalCount;

    public final static String TABLE_NAME = "prd_product";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("title", "title");
        COLUMN_MAPPINGS.put("total_cnt", "totalCount", "count(1) over()");
    }
}
