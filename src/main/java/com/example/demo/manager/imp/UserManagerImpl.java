package com.example.demo.manager.imp;

import com.example.demo.manager.UserManager;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by Canon4G 2018-12-14
 **/
@Service("UserManager")
public class UserManagerImpl  implements UserManager {

    private static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfo(User user) {
        return userMapper.getInfo(user);
    }
}
