package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.inetch.shoppee.util.ColumnMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*@Entity
@Table(name = "prd_price_list_item")*/
@Data
//@NoArgsConstructor
public class PriceListItem {
    private Long id;
    private Long priceListId;
    private Long productId;
    private String title;
    private Double price;
    private String currencySymbol;
    private Long originalPrice;
    private Long totalCount;

    public final static String TABLE_NAME = "prd_default_price_vw";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "price_list_item_id");

    static {
        COLUMN_MAPPINGS.put("price_list_item_id", "id");
        COLUMN_MAPPINGS.put("price_list_id", "priceListId");
        COLUMN_MAPPINGS.put("product_id", "productId");
        COLUMN_MAPPINGS.put("title", "title");
        COLUMN_MAPPINGS.put("price", "price");
        COLUMN_MAPPINGS.put("currency_symbol", "currencySymbol");
        COLUMN_MAPPINGS.put("original_price", "originalPrice");
        COLUMN_MAPPINGS.put("total_cnt", "totalCount", "count(1) over()");
    }
}
