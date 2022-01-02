package com.ddang.auction.home.controller;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.domain.PageCriteria;
import com.ddang.auction.items.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@RequestMapping("/home")
@Controller
public class HomeController {

    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String home(Model model){
        PageCriteria pageCriteria = itemService.getPageInfo(new PageCriteria());
        List<Item> itemList = itemService.findItemList(pageCriteria);
        model.addAttribute("itemList", itemList);
        return "home/index";
    }
}

