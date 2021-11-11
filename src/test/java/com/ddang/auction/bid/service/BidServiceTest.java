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
import java.util.Optional;

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

        int updateCount = itemRepository.updateNowBidPrice(item);
        if (updateCount>0){
            System.out.println("update complete : " + updateCount);
        }
    }


    @Test
    void bid() {
        BidItem bidItem = new BidItem();
        bidItem.setItemId(11L);
        bidItem.setMaxBidPrice(new BigInteger("3000"));
        bidItem.setNowBidPrice(new BigInteger("2000"));
        bidItem.setAddBidPrice(new BigInteger("200"));
        bidItem.setBuyerId("admin");
        bidItem.setSellerId("test3");
        bidItem.setBidDate(LocalDateTime.now().toString());

        Item findItem = itemRepository.findByItemId(bidItem.getItemId())
                .stream().findAny().get();

        if(!findItem.getIsSuccess()){
            calculateNowBidPrice(bidItem);
            System.out.println(bidItem.getNowBidPrice());

            Boolean isBidEnd = isSuccessfulBidThenSave(bidItem);
            BidItem saveBidInfo = bidRepository.saveBidInfo(bidItem);
            saveBidInfo.setIsSuccess(isBidEnd);

            int updateCount = itemRepository.updateNowBidPriceAndBidState(saveBidInfo);
            assertThat(updateCount).isEqualTo(1);
//            assertThat(updateItem.getNowBidPrice()).isEqualTo(bidItem.getNowBidPrice());
        }

    }
    boolean isSuccessfulBid(BidItem bidItem){
        if (bidItem.getNowBidPrice().compareTo(bidItem.getMaxBidPrice())>=1){
            return true;
        }
        return false;
    }
    Boolean isSuccessfulBidThenSave(BidItem bidItem) {
        if(isSuccessfulBid(bidItem)){
            //낙찰/주문내역에 insert
            return saveOrderHistory(bidItem);
        }
        return false;
    }
    Boolean saveOrderHistory(BidItem bidItem){
        BidItem saveWinningBid = bidRepository.saveWinningBid(bidItem);
        if(saveWinningBid.getBidId()!=null){
            return true;
        }
        return false;
    }

    void calculateNowBidPrice(BidItem bidItem){
        BigInteger newNowBidPrice = bidItem.getNowBidPrice().add(bidItem.getAddBidPrice());
        bidItem.setNowBidPrice(newNowBidPrice);
    }


}