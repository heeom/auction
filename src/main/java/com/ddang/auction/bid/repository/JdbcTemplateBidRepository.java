package com.ddang.auction.bid.repository;

import com.ddang.auction.bid.domain.BidItem;
import com.ddang.auction.items.domain.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateBidRepository implements BidRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateBidRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BidItem saveBidInfo(BidItem bidItem) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("biddinghistory").usingGeneratedKeyColumns("bh_historyId");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("bh_itemId", bidItem.getItemId());
        parameters.put("bh_sellerId", bidItem.getSellerId());
        parameters.put("bh_buyerId", bidItem.getBuyerId());
        parameters.put("bh_nowBidPrice", bidItem.getNowBidPrice());
        parameters.put("bh_bidDate", bidItem.getBidDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        bidItem.setBidId(key.longValue());
        return bidItem;
    }

    @Override
    public void saveWinningBid(BidItem bidItem) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("order").usingGeneratedKeyColumns("or_orderId");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("or_itemId", bidItem.getItemId());
        parameters.put("or_sellerId", bidItem.getSellerId());
        parameters.put("or_buyerId", bidItem.getBuyerId());
        parameters.put("or_winningBidPrice", bidItem.getNowBidPrice());
        parameters.put("or_orderDate", bidItem.getBidDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        bidItem.setBidId(key.longValue());
    }

    @Override
    public List<BidItem> findByItemId(Long itemId) {
        return null;
    }

    @Override
    public List<BidItem> findByMember(String memberId) {
        return null;
    }

    private RowMapper<BidItem> bidItemRowMapper(){
        return (rs, rowNum) -> {
            BidItem bidItem = new BidItem();
            bidItem.setBidId(rs.getLong("bh_historyId"));
            bidItem.setItemId(rs.getLong("bh_itemId"));
            bidItem.setSellerId(rs.getString("bh_sellerId"));
            bidItem.setBuyerId(rs.getString("bh_buyerId"));
            bidItem.setNowBidPrice(new BigInteger(rs.getString("bh_nowBidPrice")));
            bidItem.setBidDate(rs.getTimestamp("bh_bidDate").toString());
            return bidItem;
        };
    }

}
