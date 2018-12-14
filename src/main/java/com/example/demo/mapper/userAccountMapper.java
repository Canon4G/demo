package com.example.demo.mapper;

import com.example.demo.model.userAccount;

public interface userAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(userAccount record);

    int insertSelective(userAccount record);

    userAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(userAccount record);

    int updateByPrimaryKey(userAccount record);
}