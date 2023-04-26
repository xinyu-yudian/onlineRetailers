/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : onlineretailers

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 24/04/2023 15:49:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attr
-- ----------------------------
DROP TABLE IF EXISTS `attr`;
CREATE TABLE `attr`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `cid` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attr
-- ----------------------------
INSERT INTO `attr` VALUES (1, '面板', 3);
INSERT INTO `attr` VALUES (2, '尺寸', 3);

-- ----------------------------
-- Table structure for attr_child
-- ----------------------------
DROP TABLE IF EXISTS `attr_child`;
CREATE TABLE `attr_child`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `child_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `aid` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attr_child
-- ----------------------------
INSERT INTO `attr_child` VALUES (1, '49时20k超薄曲面', 1);
INSERT INTO `attr_child` VALUES (2, '人工智能', 1);
INSERT INTO `attr_child` VALUES (3, '12亿像素', 1);
INSERT INTO `attr_child` VALUES (4, '110英寸', 2);
INSERT INTO `attr_child` VALUES (6, '100英寸', 2);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `price` double(10, 2) NOT NULL,
  `number` int(0) NULL DEFAULT 0,
  `weight` double NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `cat_id` int(0) NULL DEFAULT NULL,
  `attrs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '南极人女士三角内裤 中腰可爱无痕女士内裤 均码 k102P1027', 34.50, 100, 100, '2023-04-20 21:44:57', NULL, 8, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (2, '邦诺姿 情趣内衣无痕蕾丝提臀诱惑丁字裤中腰档纯棉女士内裤低腰三角裤', 66.60, 45, 12, '2023-04-19 21:46:05', NULL, 8, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (3, '一次性口罩 盒装50只三层加厚无纺布防尘/带铝条', 23.00, 234, 4, '2023-04-22 21:46:56', NULL, 10, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (4, '莱克吸尘器家用M7 大吸力无线手持吸尘器 无耗材除螨吸尘器', 458.00, 58, 23, '2023-03-30 21:48:05', NULL, 10, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (5, '海尔', 600.00, 245, 120, '2023-04-23 00:00:00', '[2A81E9217445422FB985F87CDE714A50.png]', 3, '[]', '[]', '<p>哈哈</p>');

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `effective` int(0) NULL DEFAULT 1,
  `level` int(0) NULL DEFAULT 1,
  `pid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_category
-- ----------------------------
INSERT INTO `goods_category` VALUES (1, '大家电', 1, 1, NULL);
INSERT INTO `goods_category` VALUES (2, '电视', 1, 2, 1);
INSERT INTO `goods_category` VALUES (3, '曲面电视', 1, 3, 2);
INSERT INTO `goods_category` VALUES (4, '海信', 1, 3, 2);
INSERT INTO `goods_category` VALUES (5, 'TCL', 1, 3, 2);
INSERT INTO `goods_category` VALUES (6, '服装', 1, 1, NULL);
INSERT INTO `goods_category` VALUES (7, '衣服', 1, 2, 6);
INSERT INTO `goods_category` VALUES (8, '南极人', 1, 3, 7);
INSERT INTO `goods_category` VALUES (9, '厨房电器', 1, 2, 1);
INSERT INTO `goods_category` VALUES (10, '加尔电饭煲', 1, 3, 9);
INSERT INTO `goods_category` VALUES (11, 'test', 1, 1, NULL);
INSERT INTO `goods_category` VALUES (12, 'test', 1, 2, 11);
INSERT INTO `goods_category` VALUES (13, 'test1', 1, 2, 11);
INSERT INTO `goods_category` VALUES (14, 'test3', 1, 3, 13);
INSERT INTO `goods_category` VALUES (16, 'test33', 1, 3, 13);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_id` int(0) NOT NULL,
  `pay_status` int(0) NOT NULL DEFAULT 0 COMMENT '0，1',
  `is_send` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是，否',
  `organ` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个人，公司',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `invoice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '发票内容',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `place_time` datetime(0) NULL DEFAULT NULL,
  `order_price` double(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '123456abd', 1, 0, '否', '个人', NULL, NULL, '[\"北京\",\"东城区\"]-天安门', '2023-04-23 19:18:41', 344.09);

-- ----------------------------
-- Table structure for param
-- ----------------------------
DROP TABLE IF EXISTS `param`;
CREATE TABLE `param`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `cid` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of param
-- ----------------------------
INSERT INTO `param` VALUES (1, '主体-商品名称', 3);
INSERT INTO `param` VALUES (2, '显示-曲面', 3);
INSERT INTO `param` VALUES (3, '屏幕', 3);

-- ----------------------------
-- Table structure for param_child
-- ----------------------------
DROP TABLE IF EXISTS `param_child`;
CREATE TABLE `param_child`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `child_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `pid` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of param_child
-- ----------------------------
INSERT INTO `param_child` VALUES (1, '智能电视', 1);
INSERT INTO `param_child` VALUES (2, '是', 2);
INSERT INTO `param_child` VALUES (3, 'ai语音', 1);
INSERT INTO `param_child` VALUES (4, 'aa', 1);

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `power_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `level` int(0) NOT NULL,
  `pid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES (1, '商品管理', 'goods', 1, NULL);
INSERT INTO `power` VALUES (2, '订单管理', 'rders', 1, NULL);
INSERT INTO `power` VALUES (3, '权限管理', 'rights', 1, NULL);
INSERT INTO `power` VALUES (4, '商品列表', 'goods', 2, 1);
INSERT INTO `power` VALUES (5, '订单列表', 'orders', 2, 2);
INSERT INTO `power` VALUES (6, '用户列表', 'users', 2, 9);
INSERT INTO `power` VALUES (7, '添加商品', 'goods', 3, 4);
INSERT INTO `power` VALUES (8, '添加订单', 'orders', 3, 5);
INSERT INTO `power` VALUES (9, '用户管理', 'users', 1, NULL);
INSERT INTO `power` VALUES (10, '删除商品', 'goods', 3, 4);
INSERT INTO `power` VALUES (11, '修改商品', 'goods', 3, 4);
INSERT INTO `power` VALUES (12, '商品类别', 'goods', 2, 1);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_count` int(0) NULL DEFAULT NULL COMMENT '用户数',
  `area` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地区',
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, 2999, '华东', '2017-12-27');
INSERT INTO `report` VALUES (2, 5090, '华南', '2017-12-27');
INSERT INTO `report` VALUES (3, 6888, '华北', '2017-12-27');
INSERT INTO `report` VALUES (4, 9991, '西部', '2017-12-27');
INSERT INTO `report` VALUES (5, 15212, '其他', '2017-12-27');
INSERT INTO `report` VALUES (6, 3111, '华东', '2017-12-28');
INSERT INTO `report` VALUES (8, 2500, '华南', '2017-12-28');
INSERT INTO `report` VALUES (9, 4000, '华北', '2017-12-28');
INSERT INTO `report` VALUES (10, 4130, '西部', '2017-12-28');
INSERT INTO `report` VALUES (11, 5800, '其他', '2017-12-28');
INSERT INTO `report` VALUES (12, 4100, '华东', '2017-12-29');
INSERT INTO `report` VALUES (13, 3400, '华南', '2017-12-29');
INSERT INTO `report` VALUES (14, 8010, '华北', '2017-12-29');
INSERT INTO `report` VALUES (15, 7777, '西部', '2017-12-29');
INSERT INTO `report` VALUES (16, 10241, '其他', '2017-12-29');
INSERT INTO `report` VALUES (17, 3565, '华东', '2017-12-30');
INSERT INTO `report` VALUES (18, 6000, '华南', '2017-12-30');
INSERT INTO `report` VALUES (19, 12321, '华北', '2017-12-30');
INSERT INTO `report` VALUES (20, 12903, '西部', '2017-12-30');
INSERT INTO `report` VALUES (21, 14821, '其他', '2017-12-30');
INSERT INTO `report` VALUES (22, 3528, '华东', '2017-12-31');
INSERT INTO `report` VALUES (23, 6400, '华南', '2017-12-31');
INSERT INTO `report` VALUES (24, 13928, '华北', '2017-12-31');
INSERT INTO `report` VALUES (25, 13098, '西部', '2017-12-31');
INSERT INTO `report` VALUES (26, 15982, '其他', '2017-12-31');
INSERT INTO `report` VALUES (27, 6000, '华东', '2018-01-01');
INSERT INTO `report` VALUES (28, 7800, '华南', '2018-01-01');
INSERT INTO `report` VALUES (29, 12984, '华北', '2018-01-01');
INSERT INTO `report` VALUES (30, 14028, '西部', '2018-01-01');
INSERT INTO `report` VALUES (31, 14091, '其他', '2018-01-01');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `pid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '主管', '技术负责人', 1);
INSERT INTO `role` VALUES (2, '软件开发工程师', '项目开发', 2);
INSERT INTO `role` VALUES (3, '软件测试人员', '软件测试', 3);
INSERT INTO `role` VALUES (4, '运维', '软件后期维护1', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ms_state` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456', '3330@qq.com', '110', '管理员', 1);
INSERT INTO `user` VALUES (2, '李四', '123456', '3403@qq.com', '119', '用户', 0);
INSERT INTO `user` VALUES (3, 'admin', '123456', '456@qq.com', '109', '管理员', 1);
INSERT INTO `user` VALUES (4, 'miko87', '12456', '3456', '123466', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
