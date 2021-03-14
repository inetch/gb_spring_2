package ru.gb.inetch.shoppee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.util.ShoppingCart;

public class CartSimpleTest {
    ShoppingCart cart;
    long testId = 666L;
    PriceListItem item;

    @Before
    public void init(){
        System.out.println("init");
        cart = new ShoppingCart();

        item = new PriceListItem();
        item.setId(testId);
    }

    @Test
    public void addItem(){
        cart.add(item);

        Assert.assertEquals(1, cart.getItems().size());
        Assert.assertEquals(Long.valueOf(testId), cart.getItems().get(0).getItem().getId());
    }

    @Test(expected = RuntimeException.class)
    public void removeNotExistent(){
        cart.remove(item);
    }

    @Test
    public void removeExistent(){
        cart.add(item);
        cart.remove(item);
        //Assert.assertTrue(cart.getItems().isEmpty());
        //Assert.assertEquals(0, cart.getItems().size());
    }

}
