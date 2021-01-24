package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.inetch.shoppee.entities.ids.PriceListItemId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(PriceListItemId.class)
@Table(name = "prd_price_list_item")
@Data
@NoArgsConstructor
public class PriceListItem {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "price_list_id")
    private Long priceListId;

    @ManyToOne
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Price must not be null")
    @Column(name = "price")
    private Long price;
}
