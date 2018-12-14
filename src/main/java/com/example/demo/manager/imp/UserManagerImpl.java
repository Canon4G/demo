package com.example.demo.manager.imp;

import com.example.demo.manager.UserManager;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by guanhao 2018-12-14
 **/
@Service("UserManager")
public class UserManagerImpl  implements UserManager {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfo(User user) {
        return userMapper.getInfo(user);
    }
}
