package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import ru.gb.inetch.shoppee.util.ColumnMap;

@Data
public class OrderItem {
    private Long orderId;
    private Long productId;
    private Double price;
    private Long quantity;
    private Double totalPrice;

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap();

    static {
        COLUMN_MAPPINGS.put("order_id", "orderId");
        COLUMN_MAPPINGS.put("product_id", "productId");
        COLUMN_MAPPINGS.put("price", "price");
        COLUMN_MAPPINGS.put("quantity", "quantity");
        COLUMN_MAPPINGS.put("total_price", "totalPrice");
    }
}
