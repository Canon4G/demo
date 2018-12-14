package com.example.demo.manager;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

/**
 * create by Canon4G 2018-12-14
 **/
@Service
public interface UserManager {

    /**
     * 根据条件获得User对象
     * @author Canon4G
     * @param user 用户信息
     * @return User
     */
    User getUserInfo(User user);

    /**
     * 添加用户信息,账户信息
     * @author Canon4G
     * @param user 用户信息
     */
    void addUserAndAccount(User user);
}
