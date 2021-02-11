package ru.gb.inetch.shoppee.util;

import ru.gb.inetch.shoppee.entities.OrderItem;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<OrderItem> items;
    private Double totalCost;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalCost = 0.0;
    }

    public void add(PriceListItem item) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(0L);
            orderItem.setTotalPrice(0.0);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        recalculate();
    }

    public void setQuantity(PriceListItem item, Long quantity) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        orderItem.setQuantity(quantity);
        recalculate();
    }

    public void remove(PriceListItem item) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
        recalculate();
    }

    private void recalculate() {
        totalCost = 0.0;
        for (OrderItem o : items) {
            o.setTotalPrice(o.getQuantity() * o.getPrice());
            totalCost += o.getTotalPrice();
        }
    }

    private OrderItem findOrderItem(Long productId) {
        return items.stream().filter(o -> o.getProductId().equals(productId)).findFirst().orElse(null);
    }
}
