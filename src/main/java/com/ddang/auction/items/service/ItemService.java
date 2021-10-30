package com.ddang.auction.items.service;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return itemRepository.save(item);
    }

    public Optional<Item> findItem(Long itemId){
        return itemRepository.findByItemId(itemId);
    }

    public List<Item> findItemList(){
        return itemRepository.findAllItems();
    }

    public Item editItem(Item item){
        return null;
    }

    public void delete(int itemNo){
    }

}
