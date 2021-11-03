package com.ddang.auction.bid.service;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.repository.BidRepository;
import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BidServiceTest {

    @Autowired BidRepository bidRepository;
    @Autowired ItemRepository itemRepository;

    @Test
    void addBidPrice() {
        BidItem bidItem = new BidItem();
        bidItem.setItemId(4L);
        bidItem.setBidDate(LocalDateTime.now().toString());
        bidItem.setBuyerId("test3");
        bidItem.setAddBidPrice(new BigInteger("1000"));


        Item item = new Item();
        item.setItemId(4L);
        item.setNowBidPrice(bidItem.getNowBidPrice());
        item.setNowBidPrice(new BigInteger("1200"));

        int updateCount = itemRepository.update(item);
        if (updateCount>0){
            System.out.println("update complete : " + updateCount);
        }
    }
}