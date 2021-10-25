package com.ddang.auction.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface test {
    List<HashMap<Object, Object>> selectData(HashMap<Object, Object> vo);
}
