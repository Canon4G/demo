package com.example.demo.mapper;

import com.example.demo.model.ConsumeDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumeDetail record);

    int insertSelective(ConsumeDetail record);

    ConsumeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConsumeDetail record);

    int updateByPrimaryKey(ConsumeDetail record);
}