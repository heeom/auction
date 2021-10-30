package com.ddang.auction.items.repository;

import com.ddang.auction.items.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);

    Item update(Item item);

    void delete(int itemNo);

    Optional<Item> findByItemId(Long itemId);

    List<Item> findAllItems();
}
