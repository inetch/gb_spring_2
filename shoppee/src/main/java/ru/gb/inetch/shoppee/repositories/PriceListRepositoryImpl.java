package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PriceListRepositoryImpl implements PriceListRepository{
    private final Sql2o sql2o;

    private final static String QUERY_SELECT_DEFAULT_PRICE_LIST =
            "select id from prd_price_list where is_default = true";

    @Autowired
    public PriceListRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public Long getDefaultPriceList() {
        try (Connection connection = sql2o.open()) {
            Long id = connection.createQuery(QUERY_SELECT_DEFAULT_PRICE_LIST, false)
                    .executeScalar(Long.class);
            return id;
        }
    }
}
