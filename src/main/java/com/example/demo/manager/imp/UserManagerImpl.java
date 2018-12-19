package com.example.demo.manager.imp;

import com.example.demo.manager.UserManager;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.MyPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * create by Canon4G 2018-12-14
 **/
@Service(value = "UserManager")
public class UserManagerImpl implements UserManager {

    private static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAccountMapper userAccountMapper;

    /**
     * 根据条件获得User对象
     * @author Canon4G
     * @param user 用户信息
     * @return User
     */
    @Override
    public User getUserInfo(User user) {
        return userMapper.getInfo(user);
    }

    /**
     * 用户信息展示(分页)
     * @author Canon4G
     * @param user              用户信息
     * @param pageNum           当前页码
     * @param pageSize          总页数
     * @return MyPage<CommodityComic>
     */
    @Override
    public MyPage<User> getUserInfoListPage(User user, int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.getInfoList(user);
        MyPage<User> myPage = new MyPage<>();
        myPage.setList(users);
        myPage.setCount(page.getTotal());
        return myPage;
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

    /**
     * 修改用户信息
     * @author Canon4G
     * @param user 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public void updateUser(User user) {
        // 更新用户信息
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户信息
     * @author Canon4G
     * @param userCode  用户编号
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public void deleteUser(String userCode) {
        // 删除用户信息
        userMapper.deleteByPrimaryKey(userCode);
    }
}
