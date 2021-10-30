package com.ddang.auction.items.controller;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemNo}")
    public String Item(@PathVariable Long itemId){
        itemService.findItem(itemId);
        return "/items/view";
    }

    @GetMapping()
    public String ItemList(){
        itemService.findItemList();
        return "/items/list";
    }

    @GetMapping("/create")
    public String createItemForm(){
        return "items/itemForm";
    }

    @PostMapping("/create")
    public String createItem(Item item){
        itemService.addItem(item);
        return "items/view";
    }

    @GetMapping("/edit")
    public String editItemForm(){
        return "items/modify";
    }

    @PostMapping("/edit")
    public String editItem() {
        return "redirect:/items/";
    }

    @PostMapping("/delete")
    public String deleteItem(){
        return "redirect:/items";
    }
}
