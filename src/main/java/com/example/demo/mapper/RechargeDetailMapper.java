package com.example.demo.mapper;

import com.example.demo.model.RechargeDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeDetail record);

    int insertSelective(RechargeDetail record);

    RechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RechargeDetail record);

    int updateByPrimaryKey(RechargeDetail record);
}