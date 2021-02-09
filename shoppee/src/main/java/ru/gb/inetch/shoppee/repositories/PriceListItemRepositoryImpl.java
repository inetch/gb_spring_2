package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.User;

import java.util.Collection;
import java.util.Optional;

@Repository
public class PriceListItemRepositoryImpl implements PriceListItemRepository{
    private final Sql2o sql2o;

    @Autowired
    public PriceListItemRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    private static final String QUERY_SELECT_ITEM =
            "select " + PriceListItem.COLUMN_MAPPINGS.getAllTableColumns() + " from prd_default_price_vw where id = :id";

    private static final String QUERY_SELECT_ALL =
            "select " + PriceListItem.COLUMN_MAPPINGS.getAllTableColumns() + " from prd_default_price_vw";

    @Override
    public Collection<PriceListItem> getAll(){
        System.out.println("PriceListItemRepositoryImpl.getAll: " + QUERY_SELECT_ALL);
        try (Connection connection = sql2o.open()) {
            Collection<PriceListItem> all = connection.createQuery(QUERY_SELECT_ALL, false)
                    .setColumnMappings(PriceListItem.COLUMN_MAPPINGS)
                    .executeAndFetch(PriceListItem.class);

            System.out.println("PriceListItemRepositoryImpl.getAll done");

            return all;
        }
    }

    @Override
    public Collection<PriceListItem> getAll(Optional<Double> minPrice,
                                     Optional<Double> maxPrice,
                                     Optional<String> titleWildcard,
                                     Optional<Integer> pageNumber,
                                     Optional<Integer> pageSize
    ){
        return getAll();
    }
}
