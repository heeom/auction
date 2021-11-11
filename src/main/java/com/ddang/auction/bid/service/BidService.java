package com.ddang.auction.bid.service;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.bid.repository.BidRepository;
import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class BidService {

    private final BidRepository bidRepository;
    private final ItemRepository itemRepository;

    public BidService(BidRepository bidRepository, ItemRepository itemRepository) {
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
    }
/**
    public Item addBidPrice(BidItem bidItem) {
        Item item = itemRepository.findByItemId(bidItem.getItemId()).get();
        //현재가 계산해서 저장
        setNowBidPrice(bidItem, item);
        //낙찰 성공
        BidItem savedItem = saveSuccessfulBid(bidItem, item);
        if(savedItem.getItemId() != null){
            item.setIsSuccess(true);
            item.setEndItemDate(savedItem.getBidDate());
        }
        //입찰정보 저장, 변경된 상품내역 update
        Item updateItem = saveBidInfoUpdateItem(bidItem, item);
        return updateItem;
    }

    private BidItem saveSuccessfulBid(BidItem bidItem, Item item) {
        BidItem savedBiditem = new BidItem();
        if(isSuccessfulBid(item)){ //주문내역 저장
            savedBiditem = bidRepository.saveWinningBid(bidItem);
        }
        return savedBiditem;
    }

    private void setNowBidPrice(BidItem bidItem, Item item) {
        BigInteger nowBidPrice = item.getNowBidPrice().add(bidItem.getAddBidPrice());
        bidItem.setNowBidPrice(nowBidPrice);
        item.setNowBidPrice(nowBidPrice);
    }


    private boolean isSuccessfulBid(Item item) {
        return !(item.getNowBidPrice().compareTo(item.getMaxBidPrice()) == -1);
    }

    private Item saveBidInfoUpdateItem(BidItem bidItem, Item item) {
        //입찰내역 저장
        bidRepository.saveBidInfo(bidItem);
        //변경된 아이템 내역 update

        Item updateItem = updateItem(item);
        return updateItem;
    }

    private Item updateItem(Item item) {
        int updateCount = itemRepository.updateNowBidPrice(item);
        if(updateCount>0){
            return item;
        }
        return null;
    }

    private boolean isBidEnd(Long itemId){
         Optional<Item> findItem = itemRepository.findByItemId(itemId);
         Item item = findItem.stream().findAny().get();
         return item.getIsSuccess();
     }

 **/

    public int bidEnd(Long itemId) {
        Optional<Item> findItem = itemRepository.findByItemId(itemId);
        Item item = findItem.stream().findAny().get();
        if (!item.getIsSuccess()){
            item.setIsSuccess(true);
            return itemRepository.updateBidState(item);
        }
        return 0;
    }
    //종료된 경매여부 조회

    public Item bid(BidItem bidItem){
        Item findItem = itemRepository.findByItemId(bidItem.getItemId()).stream().findAny().get();
        if(!findItem.getIsSuccess()){
            calculateNowBidPrice(bidItem);
            Boolean isBidEnd = isSuccessfulBidThenSave(bidItem);
            //입찰내역 저장
            BidItem saveBidInfo = bidRepository.saveBidInfo(bidItem);
            saveBidInfo.setIsSuccess(isBidEnd);
            //현재가, 입찰내역 업데이트
            int updateCount = itemRepository.updateNowBidPriceAndBidState(saveBidInfo);
            if (updateCount > 0){
                return itemRepository.findByItemId(bidItem.getItemId()).stream().findAny().get();
            }
        }
        return findItem;
    }

    //현재입찰가 >= 최대입찰가 => 낙찰
    private Boolean isSuccessfulBidThenSave(BidItem bidItem) {
        if(nowPriceHigherThanMaxPrice(bidItem)){
            //낙찰/주문내역에 insert
            return saveOrderHistory(bidItem);
        }
        return false;
    }

    private void calculateNowBidPrice(BidItem bidItem){
        BigInteger newNowBidPrice = bidItem.getNowBidPrice().add(bidItem.getAddBidPrice());
        bidItem.setNowBidPrice(newNowBidPrice);
    }

    private boolean nowPriceHigherThanMaxPrice (BidItem bidItem){
        if (bidItem.getNowBidPrice().compareTo(bidItem.getMaxBidPrice())>=0){
            return true;
        }
        return false;
    }

    private Boolean saveOrderHistory(BidItem bidItem){
        BidItem saveWinningBid = bidRepository.saveWinningBid(bidItem);
        if(saveWinningBid.getBidId()!=null){
            return true;
        }
        return false;
    }

}
