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
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(0L);
            orderItem.setTotalPrice(0.0);
            orderItem.setItem(item);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
//        recalculate();
    }

    public void setQuantity(PriceListItem item, Long quantity) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        orderItem.setQuantity(quantity);
//        recalculate();
    }

    public void remove(PriceListItem item) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
//        recalculate();
    }

    private void recalculate() {
        totalCost = 0.0;
        Long total = 0L;
        for (OrderItem o : items) {
            o.setTotalPrice((double)(o.getQuantity() * o.getItem().getOriginalPrice()) / 100);
            total += o.getQuantity() * o.getItem().getOriginalPrice();
        }

        totalCost += (double)total / 100;
    }

    private OrderItem findOrderItem(Long productId) {
        return items.stream().filter(o -> o.getProductId().equals(productId)).findFirst().orElse(null);
    }
}
