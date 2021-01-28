package ru.gb.inetch.shoppee.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "prd_default_price_vw")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_list_item_id")
    private Long id;

  /*  @ManyToOne
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;*/

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "currency_symbol")
    private Character currencySymbol;

  /*  @Column(name = "code")
    private String currencyCode;

    @Column(name = "original_price")
    private Integer originalPrice;*/
}
