package com.ddang.auction.items.service;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import com.ddang.auction.items.domain.PageCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item){
        setDefaultItemInfo(item);
        return itemRepository.save(item);
    }

    public Optional<Item> findItem(Long itemId){
        return itemRepository.findByItemId(itemId);
    }

    public List<Item> findItemList(PageCriteria pageCriteria){
        return itemRepository.findAllItems(pageCriteria);
    }

    public PageCriteria getPageInfo(PageCriteria pageCriteria) {
        setDefaultPageCriteria(pageCriteria);
        return itemRepository.findPages(pageCriteria);
    }

    public Item editItem(Item item){
        return null;
    }

    public void delete(int itemNo){
    }

    private void setDefaultItemInfo(Item item) {
        item.setNowBidPrice(item.getFirstBidPrice());
        item.setWinningBidPrice(item.getFirstBidPrice());
        item.setAddItemDate(LocalDateTime.now().toString());
    }

    /**
     * @param pageCriteria
     * 상품목록 첫페이지 정보 세팅
     */

    private void setDefaultPageCriteria(PageCriteria pageCriteria) {
        if(pageCriteria.getCurrentPageNum() == null){
            pageCriteria.setCurrentPageNum(1);
        }
        pageCriteria.setRecordsPerPage(10);
        pageCriteria.setPageSize(10);
    }
}
