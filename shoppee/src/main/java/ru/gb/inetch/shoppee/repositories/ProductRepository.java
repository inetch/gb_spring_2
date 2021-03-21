package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.Product;

public interface ProductRepository {
    void save(Product product);
    void update(Product product);
    Long create(Product product);
}
