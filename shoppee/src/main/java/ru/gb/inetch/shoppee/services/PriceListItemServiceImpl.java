package ru.gb.inetch.shoppee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.Product;
import ru.gb.inetch.shoppee.entities.User;
import ru.gb.inetch.shoppee.repositories.PriceListItemRepository;
import ru.gb.inetch.shoppee.repositories.PriceListRepository;
import ru.gb.inetch.shoppee.repositories.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PriceListItemServiceImpl implements PriceListItemService{
    private PriceListItemRepository priceListItemRepo;
    private PriceListRepository priceListRepo;
    private ProductRepository productRepo;

    @Autowired
    public void setPriceListItemRepository(PriceListItemRepository repo) {
        this.priceListItemRepo = repo;
    }

    @Autowired
    public void setPriceListRepository(PriceListRepository repo) {
        this.priceListRepo = repo;
    }

    @Autowired
    public void setProductRepository(ProductRepository repo) {
        this.productRepo = repo;
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

    @Override
    public List<PriceListItem> get(Integer pageSize, Integer pageNumber, Optional<String> wordLike, Optional<Double> minPrice, Optional<Double> maxPrice){

        System.out.println("pageSize: " + pageSize + "; pageNumber: " + pageNumber);

        return (List<PriceListItem>) priceListItemRepo.getAll(minPrice, maxPrice, wordLike, Optional.of(pageNumber), Optional.of(pageSize), Optional.of(1));
    }

    @Override
    public Long getLastTotalCount(){
        return priceListItemRepo.getLastQueryTotalCount();
    }

    @Override
    public Integer getLastPageCount(Integer pageSize){
        return priceListItemRepo.getLastQueryTotalCount().intValue() / pageSize
                + ((priceListItemRepo.getLastQueryTotalCount().intValue() % pageSize) > 0 ? 1 : 0);
    }

    @Override
    @Transactional
    public void save(PriceListItem item) {
        System.out.println("Price List Item service save");

        if(item.getPriceListId() == null){
            item.setPriceListId(priceListRepo.getDefaultPriceList());
        }
        Product product = new Product();
        product.setTitle(item.getTitle());
        product.setId(item.getProductId());
        productRepo.save(product);
        item.setProductId(product.getId());

        priceListItemRepo.save(item);
    }
}
