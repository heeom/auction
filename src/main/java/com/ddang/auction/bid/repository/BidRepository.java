package com.ddang.auction.bid.repository;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.items.domain.Item;

import java.util.List;

public interface BidRepository {
    BidItem saveBidInfo(BidItem bidItem);
    List<BidItem> findByItemId(Long itemId);
    List<BidItem> findByMember(String memberId);

    BidItem saveWinningBid(BidItem bidItem);

}
