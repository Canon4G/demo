package com.example.demo.manager.imp;

import com.example.demo.manager.UserAccountManager;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户
 * create by Canon4G 2018-12-18
 **/
@Service(value = "UserAccountManager")
public class UserAccountManagerImpl implements UserAccountManager {

    private static Logger logger = LoggerFactory.getLogger(UserAccountManagerImpl.class);

    @Autowired
    UserAccountMapper userAccountMapper;

    /**
     * 根据条件获得用户账户信息
     * @author Canon4G
     * @param userAccount   账户信息
     * @return UserAccount
     */
    @Override
    public UserAccount getUserAccountInfo(UserAccount userAccount) {
        return userAccountMapper.getInfo(userAccount);
    }
}
