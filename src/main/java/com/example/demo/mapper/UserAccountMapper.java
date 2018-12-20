package com.example.demo.mapper;

import com.example.demo.model.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    /**
     * 根据条件获得用户账户信息
     * @author Canon4G
     * @param userAccount       账户信息
     * @return UserAccount
     */
    UserAccount getInfo(UserAccount userAccount);
}