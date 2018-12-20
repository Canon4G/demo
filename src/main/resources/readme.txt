业务以及数据库表

-- ----------------------------
-- user_user  用户表
-- ----------------------------
CREATE TABLE user_user
(
   id                   bigint(20)        NOT NULL AUTO_INCREMENT,
   user_code            varchar(40)       NOT NULL COMMENT '用户编码',
   user_name            varchar(20)       NOT NULL COMMENT '用户名',
   pass_word            varchar(40)       NOT NULL COMMENT '密码',
   is_admin             varchar(1)        NOT NULL COMMENT '用户权限，0、超级管理员，1、管理员，2、普通用户',
   gmt_create           DATETIME          COMMENT '创建时间',
   gmt_modified         DATETIME          COMMENT '修改时间',
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- user_account 账户表
-- ----------------------------
CREATE TABLE user_account
(
    id                  bigint(20)         NOT NULL AUTO_INCREMENT,
    account_code        varchar(40)        NOT NULL COMMENT '账户编码',
    user_code           varchar(40)        NOT NULL COMMENT '用户编码',
    account_money       decimal(16, 2)     DEFAULT NULL COMMENT '账户余额，保留两位小数',
    gmt_create          DATETIME           COMMENT '创建时间',
    gmt_modified        DATETIME           COMMENT '修改时间',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------------
-- finance_recharge_detail 充值流水表
-- ----------------------------------
CREATE TABLE finance_recharge_detail
(
    id                  bigint(20)          NOT NULL AUTO_INCREMENT,
    recharge_code       varchar(60)         NOT NULL COMMENT '充值流水编码',
    user_code           varchar(60)         NOT NULL COMMENT '用户编码',
    account_code        varchar(60)         NOT NULL COMMENT '账户编码',
    recharge_money      decimal(16, 2)      DEFAULT NULL COMMENT '充值金额，保留两位小数',
    recharge_mode       varchar(1)          NOT NULL COMMENT '充值方式：0、微信，1、支付宝，3、银行卡',
    gmt_create          DATETIME            COMMENT '创建时间',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------------
-- finance_consume_detail 消费流水表
-- ----------------------------------
CREATE TABLE finance_consume_detail
(
    id                  bigint(20)          NOT NULL AUTO_INCREMENT,
    consume_code        varchar(60)         NOT NULL COMMENT '消耗流水编码',
    user_code           varchar(60)         NOT NULL COMMENT '用户编码',
    account_code        varchar(60)         NOT NULL COMMENT '账户编码',
    consume_money       decimal(16, 2)      DEFAULT NULL COMMENT '消耗金额，保留两位小数',
    product_code        varchar(60)         NOT NULL COMMENT '商品编码',
    product_count       varchar(40)         NOT NULL COMMENT '商品数量',
    gmt_create          DATETIME            COMMENT '创建时间',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------------
-- commodity_comic         商品_漫画表
-- ----------------------------------
CREATE TABLE commodity_comic
(
    id                  bigint(20)              NOT NULL AUTO_INCREMENT,
    comic_code          varchar(40)             NOT NULL COMMENT '漫画编码',
    comic_name          varchar(40)             NOT NULL COMMENT '漫画名称',
    comic_type          varchar(1)              NOT NULL COMMENT '漫画分类: 0、港慢，1、日漫，2、欧美漫画',
    comic_price         decimal(16,2)           NOT NULL COMMENT '价格, 保留两位小数',
    comic_inventory     varchar(40)             NOT NULL COMMENT '库存',
    gmt_create          DATETIME                COMMENT '创建时间',
    gmt_modified        DATETIME                COMMENT '修改时间',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
