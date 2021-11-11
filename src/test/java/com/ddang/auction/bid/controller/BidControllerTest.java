package com.ddang.auction.bid.controller;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.service.BidService;
import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.Bidi;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BidControllerTest {

    @Autowired BidService bidService;
    @Autowired ItemService itemService;

    @Test
    void bidPrice() {
        BidItem bidItem = new BidItem();
        bidItem.setBidDate(LocalDateTime.now().toString());
        bidItem.setBuyerId("testBuyer");
        bidItem.setSellerId("testSeller");
        bidItem.setNowBidPrice(new BigInteger("100"));
        bidItem.setMaxBidPrice(new BigInteger("2000"));
//        bidItem.setAddBidPrice(new BigInteger("2000")); //낙찰
        bidItem.setAddBidPrice(new BigInteger("200"));
        bidItem.setItemId(14L);

        Item resultItem = bidService.bid(bidItem);

        assertThat(resultItem.getIsSuccess()).isEqualTo(false);
    }

    @Test
    void bidEnd() {
        BidItem bidItem = new BidItem();
        bidItem.setItemId(11L);

        int updateCount = bidService.bidEnd(bidItem.getItemId());

        assertThat(updateCount).isEqualTo(1);
    }
}