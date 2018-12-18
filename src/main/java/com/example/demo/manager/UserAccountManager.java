package com.example.demo.manager;

import com.example.demo.model.UserAccount;
import org.springframework.stereotype.Service;

/**
 * create by Canon4G 2018-12-18
 **/
@Service
public interface UserAccountManager {

    /**
     * 根据条件获得用户账户信息
     * @author Canon4G
     * @param userAccount   账户信息
     * @return UserAccount
     */
    UserAccount getUserAccountInfo(UserAccount userAccount);
}
