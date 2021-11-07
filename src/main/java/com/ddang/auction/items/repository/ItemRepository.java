package com.ddang.auction.items.repository;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.domain.PageCriteria;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);

    int updateNowBidPrice(Item item);

    void delete(int itemNo);

    Optional<Item> findByItemId(Long itemId);

    List<Item> findAllItems(PageCriteria pageCriteria);

    PageCriteria findPages(PageCriteria pageCriteria);

    int updateBidState(Item item);
}
