package com.example.demo.mapper;

import com.example.demo.model.rechargeDetail;

public interface rechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(rechargeDetail record);

    int insertSelective(rechargeDetail record);

    rechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(rechargeDetail record);

    int updateByPrimaryKey(rechargeDetail record);
}