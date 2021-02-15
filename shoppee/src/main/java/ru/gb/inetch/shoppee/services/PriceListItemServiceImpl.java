package ru.gb.inetch.shoppee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.repositories.PriceListItemRepository;

import java.util.List;

@Service
public class PriceListItemServiceImpl implements PriceListItemService{
    private PriceListItemRepository priceListItemRepo;

    @Autowired
    public void setProductRepository(PriceListItemRepository repo) {
        this.priceListItemRepo = repo;
    }

    @Override
    public List<PriceListItem> getAll() {
        return (List<PriceListItem>)priceListItemRepo.getAll();
    }

    @Override
    public PriceListItem getByProductId(Long productId) {
        return priceListItemRepo.getByProductId(productId);
    }

    @Override
    public PriceListItem getById(Long id){
        return priceListItemRepo.getById(id);
    }
}
