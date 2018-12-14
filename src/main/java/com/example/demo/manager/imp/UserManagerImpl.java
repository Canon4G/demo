package com.example.demo.manager.imp;

import com.example.demo.manager.UserManager;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * create by Canon4G 2018-12-14
 **/
@Service("UserManager")
public class UserManagerImpl  implements UserManager {

    private static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public User getUserInfo(User user) {
        return userMapper.getInfo(user);
    }

    /**
     * 添加用户信息,账户信息
     * @author Canon4G
     * @param user 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public void addUserAndAccount(User user) {
        // 添加用户表信息
        userMapper.insertSelective(user);
        // 添加账户表信息
        String accountCode = "ACCOUNT" + CodeUtil.getDateUUID();
        userAccountMapper.insertSelective(new UserAccount.Builder()
                .userCode(user.getUserCode())
                .accountCode(accountCode)
                .gmtCreate(new Date())
                .build());
    }
}
