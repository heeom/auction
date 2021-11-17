package com.ddang.auction.items.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

/**
 * firstBidPrice : 경매 시작가
 * maxBidPrice : 최고가(판매자 지정 낙찰가)
 * nowBidPrice : 현재 입찰가
 * winningBidPrice : 낙찰가
 *
 */
@Getter @Setter
@ToString
public class Item {
    private long itemId;
    private String itemName;
    private String username;
    private String addItemDate;
    private String endItemDate;
    private BigInteger firstBidPrice;
    private BigInteger maxBidPrice;
    private BigInteger nowBidPrice;
    private BigInteger winningBidPrice;
    private Integer deliveryPrice;
    private String itemContent;
    private String itemCategory;
    private MultipartFile itemThumbnail;
    private Boolean isSuccess;
}
