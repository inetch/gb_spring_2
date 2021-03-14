package ru.gb.inetch.shoppee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.repositories.PriceListItemRepository;

import java.util.List;
import java.util.Optional;

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
}
