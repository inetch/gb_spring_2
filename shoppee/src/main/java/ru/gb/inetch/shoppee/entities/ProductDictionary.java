package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/*@Entity
@Table(name = "prd_product")
@Data
@NoArgsConstructor*/
public class ProductDictionary {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Title not null")
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceListItem> priceListItems;*/
}
