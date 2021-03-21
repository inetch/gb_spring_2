package ru.gb.inetch.shoppee.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.Product;
import ru.gb.inetch.shoppee.entities.User;
import ru.gb.inetch.shoppee.services.PriceListItemService;
import ru.gb.inetch.shoppee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private PriceListItemService itemService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPriceListItemService(PriceListItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("user-list")
    public String showUserList(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("userList", allUsers);
        return "user-list";
    }

    @GetMapping("user/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-form";
    }

    @PostMapping("user-form")
    public String updateUser(User user) {
        userService.save(user);
        return "redirect:user-list";
    }

    @GetMapping("product/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        PriceListItem item;
        if(id == 0){
            item = new PriceListItem();
            item.setId(0L);
            item.setProductId(0L);
        }else{
            item = itemService.getById(id);
        }
        model.addAttribute("item", item);
        return "edit-product";
    }

    @PostMapping("product-edit")
    public String updateProduct(PriceListItem item) {
        System.out.println(item);
        itemService.save(item);
        return "redirect:/product-list";
    }

}
