package com.example.demo.mapper;

import com.example.demo.model.consumeDetail;

public interface consumeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(consumeDetail record);

    int insertSelective(consumeDetail record);

    consumeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(consumeDetail record);

    int updateByPrimaryKey(consumeDetail record);
}