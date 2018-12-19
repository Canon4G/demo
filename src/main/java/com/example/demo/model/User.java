package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 */
public class User implements Serializable {

    private static final long serialVersionUID = -630880340434153119L;

    private Long id;                    // id

    private String userCode;            // 会员编码

    private String userName;            // 用户名

    private String passWord;            // 密码

    private Date gmtCreate;             // 创建时间

    private Date gmtModified;           // 修改时间

    private String isAdmin;             // 是否是管理员权限

    public User() {
    }

    public User(Long id, String userCode, String userName, String passWord, Date gmtCreate, Date gmtModified, String isAdmin) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.passWord = passWord;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.isAdmin = isAdmin;
    }

    private User(Builder builder) {
        setId(builder.id);
        setUserCode(builder.userCode);
        setUserName(builder.userName);
        setPassWord(builder.passWord);
        setGmtCreate(builder.gmtCreate);
        setGmtModified(builder.gmtModified);
        setIsAdmin(builder.isAdmin);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public User setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public static final class Builder {
        private Long id;
        private String userCode;
        private String userName;
        private String passWord;
        private Date gmtCreate;
        private Date gmtModified;
        private String isAdmin;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder userCode(String val) {
            userCode = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder passWord(String val) {
            passWord = val;
            return this;
        }

        public Builder gmtCreate(Date val) {
            gmtCreate = val;
            return this;
        }

        public Builder gmtModified(Date val) {
            gmtModified = val;
            return this;
        }

        public Builder isAdmin(String val) {
            isAdmin = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}