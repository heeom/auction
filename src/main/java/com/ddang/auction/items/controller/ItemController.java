package com.ddang.auction.items.controller;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public String Item(@PathVariable Long itemId, Model model){
        Item item = itemService.findItem(itemId).get();
        model.addAttribute("item", item);
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
    public String createItem(Item item, Model model){
        Item saveItem = itemService.addItem(item);
        model.addAttribute("item", saveItem);
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
