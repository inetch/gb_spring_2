package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final Sql2o sql2o;

    @Autowired
    public ProductRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Product product) {
        Long cnt;
        try (Connection connection = sql2o.open()) {
            cnt = connection.createQuery("select count(id) from prd_product where id = :u_id")
                    .addParameter("u_id", product.getId())
                    .executeScalar(Long.class);
        }
        if(cnt == 0){
            Long id = create(product);
            product.setId(id);
        }else{
            update(product);
        }

    }

    @Override
    public void update(Product product){
        System.out.println("update product: " + Product.COLUMN_MAPPINGS.updateByIdQuery("id"));
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery(Product.COLUMN_MAPPINGS.updateByIdQuery("id"))
                    .addParameter("title", product.getTitle())
                    .addParameter("id", product.getId())
                    .executeUpdate();
            connection.commit();
        }
    }

    @Override
    @Transactional
    public Long create(Product product){
        System.out.println("create product: " + Product.COLUMN_MAPPINGS.insertQuery());
        try (Connection connection = sql2o.beginTransaction()) {
            Long id = connection.createQuery(Product.COLUMN_MAPPINGS.insertQuery(), true)
                    .bind(product)
                    .executeUpdate()
                    .getKey(Long.class);
            connection.commit();
            return id;
        }
    }
}
