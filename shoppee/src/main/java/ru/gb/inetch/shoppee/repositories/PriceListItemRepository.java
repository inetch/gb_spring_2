package ru.gb.inetch.shoppee.repositories;

import ru.gb.inetch.shoppee.entities.PriceListItem;

import java.util.Collection;
import java.util.Optional;

public interface PriceListItemRepository {
    Collection<PriceListItem> getAll();
    Collection<PriceListItem> getAll(Optional<Double> minPrice,
                                     Optional<Double> maxPrice,
                                     Optional<String> titleWildcard,
                                     Optional<Integer> pageNumber,
                                     Optional<Integer> pageSize
                                     );
    PriceListItem getByProductId(Long productId);
}
