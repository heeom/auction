package com.ddang.auction.bid.controller;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.service.BidService;
import com.ddang.auction.items.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BidControllerTest {

    @Autowired BidService bidService;
    @Autowired ItemService itemService;

    @Test
    void bidPrice() {
    }

    @Test
    void bidEnd() {
        BidItem bidItem = new BidItem();
        bidItem.setItemId(11L);

        int updateCount = bidService.bidEnd(bidItem.getItemId());

        Assertions.assertThat(updateCount).isEqualTo(1);
    }
}