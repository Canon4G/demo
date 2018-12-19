package com.example.demo.manager;

import com.example.demo.model.User;
import com.example.demo.util.MyPage;
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
     * 用户信息展示(分页)
     * @author Canon4G
     * @param user              用户信息
     * @param pageNum           当前页码
     * @param pageSize          总页数
     * @return MyPage<CommodityComic>
     */
    MyPage<User> getUserInfoListPage(User user, int pageNum, int pageSize);

    /**
     * 添加用户信息,账户信息
     * @author Canon4G
     * @param user 用户信息
     */
    void addUserAndAccount(User user);

    /**
     * 修改用户信息
     * @author Canon4G
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 删除用户信息
     * @author Canon4G
     * @param userCode  用户编号
     */
    void deleteUser(String userCode);
}
