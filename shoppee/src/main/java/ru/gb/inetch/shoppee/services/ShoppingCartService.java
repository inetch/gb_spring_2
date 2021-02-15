package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.util.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {
    private PriceListItemService itemService;

    @Autowired
    public void setItemService(PriceListItemService itemService){
        this.itemService = itemService;
    }

    public ShoppingCart getCurrentCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void resetCart(HttpSession session) {
        session.removeAttribute("cart");
    }

    public void addToCart(HttpSession session, Long productId) {
        PriceListItem item = itemService.getByProduct_id(productId);
        addToCart(session, item);
    }

    public void addToCart(HttpSession session, PriceListItem item) {
        ShoppingCart cart = getCurrentCart(session);
        cart.add(item);
    }

    public void removeFromCart(HttpSession session, Long productId) {
        PriceListItem item = itemService.getByProduct_id(productId);
        removeFromCart(session, item);
    }

    public void removeFromCart(HttpSession session, PriceListItem item) {
        ShoppingCart cart = getCurrentCart(session);
        cart.remove(item);
    }

    public void setProductCount(HttpSession session, Long productId, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        PriceListItem item = itemService.getByProduct_id(productId);
        cart.setQuantity(item, quantity);
    }

    public void setProductCount(HttpSession session, PriceListItem item, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        cart.setQuantity(item, quantity);
    }

    public double getTotalCost(HttpSession session) {
        return getCurrentCart(session).getTotalCost();
    }
}
