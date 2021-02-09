package ru.gb.inetch.shoppee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.Product;
import ru.gb.inetch.shoppee.services.PriceListItemService;
import ru.gb.inetch.shoppee.services.ProductService;

import java.util.List;

@Controller
public class ProductController {
   // private ProductService productService;
    private PriceListItemService itemService;

//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @Autowired
    public void setPriceListItemService(PriceListItemService itemService){
        this.itemService = itemService;
    }

    @RequestMapping("/product-list")
    public String showProductsList(Model model) {
        List<PriceListItem> allStuff = itemService.getAll();
        model.addAttribute("products", allStuff);
        /*List<Product> allProduct = productService.getAllProducts();
        model.addAttribute("productList", allProduct);*/
        return "product-list";
    }

}
