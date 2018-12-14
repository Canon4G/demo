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
     * @param user user对象
     * @return User
     */
    User getUserInfo(User user);
}
