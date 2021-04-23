package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.inetch.shoppee.util.ColumnMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Data
public class PriceList {
    Long id;
    String title;
    String currencyCode;
    boolean isDefault;

    public final static String TABLE_NAME = "prd_price_list";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("title", "title");
        COLUMN_MAPPINGS.put("currency_code", "currencyCode");
        COLUMN_MAPPINGS.put("is_default", "isDefault");
    }
}
