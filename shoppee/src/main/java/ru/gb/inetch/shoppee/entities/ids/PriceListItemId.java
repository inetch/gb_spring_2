package ru.gb.inetch.shoppee.entities.ids;

import java.io.Serializable;
import java.util.Objects;

public class PriceListItemId implements Serializable {

    private Long productId;
    private Integer priceListId;

    public PriceListItemId(Long productId, Integer priceListId) {
        this.productId = productId;
        this.priceListId = priceListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListItemId that = (PriceListItemId) o;
        return productId.equals(that.productId) && priceListId.equals(that.priceListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, priceListId);
    }
}
