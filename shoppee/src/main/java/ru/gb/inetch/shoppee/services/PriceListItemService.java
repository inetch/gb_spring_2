package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.entities.PriceListItem;

import java.util.List;
import java.util.Optional;

public interface PriceListItemService {
    List<PriceListItem> getAll();
    PriceListItem getByProductId(Long productId);
    PriceListItem getById(Long id);
    List<PriceListItem> get(Integer pageSize, Integer pageNumber, Optional<String> wordLike, Optional<Double> minPrice, Optional<Double> maxPrice);
    Long getLastTotalCount();
    Integer getLastPageCount(Integer pageSize);
}
