package ru.gb.inetch.shoppee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.services.PriceListItemService;
import ru.gb.inetch.shoppee.services.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Controller
//public class ProductController {
//   // private ProductService productService;
//    private PriceListItemService itemService;
//    private UserService userService;
//
//    private final int INITIAL_PAGE = 0;
//    private final int PAGE_SIZE = 5;
//
//    @Autowired
//    public void setPriceListItemService(PriceListItemService itemService){
//        this.itemService = itemService;
//    }
//
//    /*@RequestMapping("/product-list")
//    public String showProductsList(Model model) {
//        List<PriceListItem> allStuff = itemService.getAll();
//        model.addAttribute("products", allStuff);
//        *//*List<Product> allProduct = productService.getAllProducts();
//        model.addAttribute("productList", allProduct);*//*
//        return "product-list";
//    }*/
//
//    @GetMapping("/product-list")
//    public String shopPage(Model model,
//                           @RequestParam(value = "page") Optional<Integer> page,
//                           @RequestParam(value = "word") Optional<String> word,
//                           @RequestParam(value = "min")  Optional<Double> min,
//                           @RequestParam(value = "max")  Optional<Double> max
//    ) {
//        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//
//        StringBuilder filters = new StringBuilder();
//        word.ifPresent(s -> filters.append("&word=").append(s));
//        min.ifPresent(aDouble -> filters.append("&min=").append(aDouble));
//        max.ifPresent(aDouble -> filters.append("&max=").append(aDouble));
//
//        List<PriceListItem> items = itemService.get(PAGE_SIZE, currentPage, word, min, max);
//
//        System.out.println("Last total count: " + itemService.getLastTotalCount());
//        System.out.println("Last page count: " + itemService.getLastPageCount(PAGE_SIZE));
//
//        model.addAttribute("products", items);
//        model.addAttribute("page", currentPage);
//        model.addAttribute("totalPage", itemService.getLastPageCount(PAGE_SIZE));
//
//        model.addAttribute("filters", filters.toString());
//
//        model.addAttribute("min", min);
//        model.addAttribute("max", max);
//        model.addAttribute("word", word);
//        return "product-list";
//    }

}
