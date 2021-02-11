package ru.gb.inetch.shoppee.services;

import ru.gb.inetch.shoppee.entities.PriceListItem;

import java.util.List;

public interface PriceListItemService {
    List<PriceListItem> getAll();
    PriceListItem getByProduct_id(Long productId);
}