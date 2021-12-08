package com.ddang.auction.bid.controller;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.service.BidService;
import com.ddang.auction.items.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public String bidPrice(BidItem bidItem, Model model){
        bidItem.setBidDate(LocalDateTime.now().toString());
        Item item = bidService.bid(bidItem);
        return "redirect:/items/" + item.getItemId();
    }

    @PostMapping("/end")
    public void bidEnd(Long itemId){
        bidService.bidEnd(itemId);
    }
}
