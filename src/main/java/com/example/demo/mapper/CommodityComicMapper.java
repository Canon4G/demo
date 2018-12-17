package com.example.demo.mapper;

import com.example.demo.model.CommodityComic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityComicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommodityComic record);

    int insertSelective(CommodityComic record);

    CommodityComic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommodityComic record);

    int updateByPrimaryKey(CommodityComic record);

    /**
     * 根据条件获得漫画信息
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @return CommodityComic
     */
    CommodityComic getInfo(CommodityComic commodityComic);

    /**
     * 根据条件获得漫画信息列表
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @return List<CommodityComic>
     */
    List<CommodityComic> getInfoList(CommodityComic commodityComic);
}