package com.ddang.auction.items.service;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.repository.ItemRepository;
import com.ddang.auction.items.domain.PageCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    void addItem() {
        //given
        Item item = new Item();
        item.setItemName("testItem");
        item.setMemberId("testId");
        item.setAddItemDate(LocalDateTime.now().toString());
        item.setEndItemDate(item.getAddItemDate());
        item.setFirstBidPrice(new BigInteger("10000"));
        item.setNowBidPrice(item.getFirstBidPrice());
        item.setMaxBidPrice(item.getFirstBidPrice());
        item.setWinningBidPrice(item.getFirstBidPrice());

        //when
        Item savedItem = itemService.addItem(item);

        //then
        Item findItem = itemService.findItem(savedItem.getItemId()).get();
        assertThat(item.getItemName()).isEqualTo(findItem.getItemName());
    }

    @Test
    void findItem() {
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("testItem");
        item.setMemberId("testId");

        Optional<Item> findItemOptional = itemRepository.findByItemId(item.getItemId());

        findItemOptional.ifPresent(findItem -> {
            assertThat(item.getItemName()).isEqualTo(findItem.getItemName());
            assertThat(item.getMemberId()).isEqualTo(findItem.getMemberId());
        });
    }

    @Test
    void findItemList() {
        PageCriteria pageCriteria = new PageCriteria();

        PageCriteria findPageCriteria = itemService.getPageInfo(pageCriteria);
        List<Item> itemList = itemService.findItemList(pageCriteria);

        System.out.println(findPageCriteria.toString());
        itemList.stream().map(Item::getItemId).forEach(System.out::println);
    }

    @Test
    void getPageInfo(){
        PageCriteria pageCriteria = new PageCriteria();
        pageCriteria.setPageSize(10);
        pageCriteria.setCurrentPageNum(1);
        pageCriteria.setTotalRecords(1);
        pageCriteria.setRecordsPerPage(10);

        PageCriteria findPageCriteria = itemService.getPageInfo(pageCriteria);


        assertThat(findPageCriteria.getStartRecord()).isEqualTo(1);
        assertThat(findPageCriteria.getStartPage()).isEqualTo(1);
        assertThat(findPageCriteria.getEndPage()).isEqualTo(1);

    }
}