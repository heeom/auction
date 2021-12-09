package com.ddang.auction.bid.service;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.repository.BidRepository;
import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
public class BidService {

    private final BidRepository bidRepository;
    private final ItemRepository itemRepository;

    public BidService(BidRepository bidRepository, ItemRepository itemRepository) {
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
    }

    public int bidEnd(Long itemId) {
        Item findItem = itemRepository.findByItemId(itemId).orElse(new Item());

        if (!findItem.getIsSuccess()){
            return itemRepository.updateBidState(findItem);
        }
        return 0;
    }

    public Item bid(BidItem bidItem){
        Item findItem = itemRepository.findByItemId(bidItem.getItemId()).orElse(new Item());

        if(!findItem.getIsSuccess()){
            calculateNowBidPrice(bidItem);
            Boolean isBidEnd = isSuccessfulBidThenSave(bidItem);
            //입찰내역 저장
            BidItem savedBidItem = bidRepository.saveBidInfo(bidItem);
            savedBidItem.setIsSuccess(isBidEnd);
            //현재가, 입찰내역 업데이트
            int updateCount = itemRepository.updateNowBidPriceAndBidState(savedBidItem);
            if (updateCount > 0){
                return itemRepository.findByItemId(bidItem.getItemId()).orElse(findItem);
            }
        }
        return findItem;
    }

    //현재입찰가 >= 최대입찰가 => 낙찰
    private Boolean isSuccessfulBidThenSave(BidItem bidItem) {
        if(nowPriceHigherThanMaxPrice(bidItem)){
            return bidRepository.saveWinningBid(bidItem).isPresent();
        }
        return false;
    }

    private void calculateNowBidPrice(BidItem bidItem){
        BigInteger newNowBidPrice = bidItem.getNowBidPrice().add(bidItem.getAddBidPrice());
        bidItem.setNowBidPrice(newNowBidPrice);
    }

    private boolean nowPriceHigherThanMaxPrice (BidItem bidItem){
        return bidItem.getNowBidPrice().compareTo(bidItem.getMaxBidPrice()) >= 0;
    }

}
