package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.inetch.shoppee.util.ColumnMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDictionary {
    private Long id;
    private String title;

    public final static String TABLE_NAME = "prd_product";

    public static final ColumnMap COLUMN_MAPPINGS = new ColumnMap(TABLE_NAME, "id");

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
