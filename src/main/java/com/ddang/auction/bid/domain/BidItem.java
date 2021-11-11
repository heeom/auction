package com.ddang.auction.bid.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter @Setter
public class BidItem {

    private Long bidId;
    private Long itemId;
    private String sellerId;
    private String buyerId;
    private BigInteger nowBidPrice;
    private BigInteger addBidPrice;
    private BigInteger maxBidPrice;
    private String bidDate;
    private Boolean isSuccess;
}
