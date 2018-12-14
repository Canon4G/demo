业务以及数据库表

-- ----------------------------
-- user_user  用户表
-- ----------------------------
CREATE TABLE user_user(
   id bigint unsigned NOT NULL AUTO_INCREMENT,
   user_code varchar(40) DEFAULT NULL COMMENT '用户编码',
   user_name varchar(20) NOT NULL COMMENT '用户名',
   pass_word varchar(20) NOT NULL COMMENT '密码',
   gmt_create DATE COMMENT '创建时间',
   gmt_modified DATE COMMENT '修改时间',
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;