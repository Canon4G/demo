package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String userCode);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据条件获得User对象
     * @param user user对象
     * @return User
     */
    User getInfo(User user);

    /**
     * 根据条件获得用户信息列表
     * @param user user对象
     * @return List<User>
     */
    List<User> getInfoList(User user);
}