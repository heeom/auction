package com.ddang.auction.bid.repository;

import com.ddang.auction.bid.domain.BidItem;

import java.util.List;
import java.util.Optional;

public interface BidRepository {
    BidItem saveBidInfo(BidItem bidItem);

    List<BidItem> findByItemId(Long itemId);
    List<BidItem> findByMember(String memberId);

    Optional<BidItem> saveWinningBid(BidItem bidItem);

}
