package com.ddang.auction.items.repository;

import com.ddang.auction.items.domain.Item;
import com.ddang.auction.items.domain.PageCriteria;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateItemRepository implements ItemRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("item").usingGeneratedKeyColumns("it_itemId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("it_itemName", item.getItemName());
        parameters.put("it_memberId", item.getMemberId());
        parameters.put("it_itemName", item.getItemName());
        parameters.put("it_addItemDate", item.getAddItemDate());
        parameters.put("it_endItemDate", item.getEndItemDate());
        parameters.put("it_firstBidPrice", item.getFirstBidPrice());
        parameters.put("it_maxBidPrice", item.getMaxBidPrice());
        parameters.put("it_nowBidPrice", item.getNowBidPrice());
        parameters.put("it_winningBidPrice", item.getWinningBidPrice());
        parameters.put("it_deliveryPrice", item.getDeliveryPrice());
        parameters.put("it_itemContent", item.getItemContent());
        parameters.put("it_itemCategory", item.getItemCategory());
        parameters.put("it_itemThumbnail", item.getItemThumbnail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        item.setItemId(key.longValue());

        return item;
    }

    @Override
    public Optional<Item> findByItemId(Long itemId) {
        List<Item> result = jdbcTemplate.query("select * from item where it_itemId = ?", itemRowMapper(), itemId);
        return result.stream().findAny();
    }

    @Override
    public List<Item> findAllItems(PageCriteria pageCriteria) {
        return jdbcTemplate.query("select * from item order by it_itemId limit ?, ?", itemRowMapper(), pageCriteria.getStartRecord(), pageCriteria.getRecordsPerPage());
    }

    @Override
    public PageCriteria findPages(PageCriteria pageCriteria) {
        Integer totalRecords = jdbcTemplate.queryForObject("select count(it_itemId) from item", Integer.class);
        pageCriteria.setTotalRecords(totalRecords);
        return pageCriteria;
    }

    @Override
    public int updateNowBidPrice(Item item) {
        return jdbcTemplate.update("update item set it_nowBidPrice = ? where it_itemId = ?", item.getNowBidPrice(), item.getItemId());
    }

    @Override
    public int updateBidState(Item item) {
        return jdbcTemplate.update("update item set it_success=true where it_itemId = ?", item.getItemId());
    }

    @Override
    public void delete(int itemNo) {

    }

    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setItemId(rs.getLong("it_itemId"));
            item.setItemName(rs.getString("it_itemName"));
            item.setMemberId(rs.getString("it_memberId"));
            item.setAddItemDate(rs.getString("it_addItemDate"));
            item.setEndItemDate(rs.getString("it_endItemDate"));
            item.setFirstBidPrice(new BigInteger(rs.getString("it_firstBidPrice")));
            item.setMaxBidPrice(new BigInteger(rs.getString("it_maxBidPrice")));
            item.setNowBidPrice(new BigInteger(rs.getString("it_nowBidPrice")));
            item.setWinningBidPrice(new BigInteger(rs.getString("it_winningBidPrice")));
            item.setDeliveryPrice(rs.getInt("it_deliveryPrice"));
            item.setItemContent(rs.getString("it_itemContent"));
            item.setItemCategory(rs.getString("it_itemCategory"));
            item.setIsSuccess(rs.getBoolean("it_success"));
            return item;
        };
    }
}
