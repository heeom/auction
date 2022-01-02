package com.ddang.auction.items.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @Column(name = "it_itemName")
    private String itemName;

    @Column(name = "it_memberId")
    private String username;

    @Column(name = "it_addItemDate")
    private String addItemDate;

    @Column(name = "it_endItemDate")
    private String endItemDate;

    @Column(name = "it_firstBidPrice")
    private BigInteger firstBidPrice;

    @Column(name = "it_maxBidPrice")
    private BigInteger maxBidPrice;

    @Column(name = "it_nowBidPrice")
    private BigInteger nowBidPrice;

    @Column(name = "it_winningBidPrice")
    private BigInteger winningBidPrice;

    @Column(name = "it_deliveryPrice")
    private Integer deliveryPrice;

    @Column(name = "it_itemContent")
    private String itemContent;

    @Column(name = "it_itemCategory")
    private String itemCategory;

    @Lob
    @Column(name = "it_itemThumbnail", columnDefinition = "BLOB")
    private byte[] itemThumbnail;

    @Column(name = "it_success")
    private Boolean isSuccess;
}
