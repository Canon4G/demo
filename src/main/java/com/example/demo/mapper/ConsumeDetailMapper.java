package com.example.demo.mapper;

import com.example.demo.model.ConsumeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsumeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumeDetail record);

    int insertSelective(ConsumeDetail record);

    ConsumeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConsumeDetail record);

    int updateByPrimaryKey(ConsumeDetail record);

    /**
     * 获得消耗流水列表(分页)
     * @param record        消耗流水信息
     * @return  List<ConsumeDetail>
     */
    List<ConsumeDetail> getInfoList(ConsumeDetail record);
}