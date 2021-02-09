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
        System.out.println("PriceListItemServiceImpl.getAll");
        List<PriceListItem> all = (List<PriceListItem>)priceListItemRepo.getAll();
        System.out.println("PriceListItemServiceImpl.getAll done");
        return all;
    }
}


/*
@Service
public class ProductServiceImpl implements ProductService {

    private PriceListItemRepository priceListItemRepo;

    @Autowired
    public void setProductRepository(PriceListItemRepository repo) {
        this.priceListItemRepo = repo;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) priceListItemRepo.getAll();
    }
}*/