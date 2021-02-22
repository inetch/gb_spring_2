package ru.gb.inetch.shoppee.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gb.inetch.shoppee.entities.OrderItem;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ShoppingCart {
    private final List<OrderItem> items;
    private Double totalCost;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalCost = 0.0;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public List<OrderItem> getItems() {
        return items;
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
    }

    public void setQuantity(PriceListItem item, Long quantity) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        orderItem.setQuantity(quantity);
    }

    public void remove(PriceListItem item) {
        OrderItem orderItem = findOrderItem(item.getProductId());
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
    }

    public void recalculate() {
        totalCost = 0.0;
        long total = 0L;
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
