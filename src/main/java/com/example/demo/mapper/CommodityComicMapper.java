package com.example.demo.mapper;

import com.example.demo.model.CommodityComic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityComicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommodityComic record);

    int insertSelective(CommodityComic record);

    CommodityComic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommodityComic record);

    int updateByPrimaryKey(CommodityComic record);
}