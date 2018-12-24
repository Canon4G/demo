package com.example.demo.mapper;

import com.example.demo.model.RechargeDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RechargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeDetail record);

    int insertSelective(RechargeDetail record);

    RechargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RechargeDetail record);

    int updateByPrimaryKey(RechargeDetail record);

    /**
     * 获得充值流水列表(分页)
     * @param record        充值流水信息
     * @return List<RechargeDetail>
     */
    List<RechargeDetail> getInfoList(RechargeDetail record);
}