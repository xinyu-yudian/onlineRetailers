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

 Date: 07/05/2023 20:57:58
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
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `time` datetime(0) NOT NULL,
  `finish_time` datetime(0) NOT NULL,
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES (1, '123456abd', '2023-05-06 19:53:16', '2023-05-07 19:53:20', '已签收，感谢使用顺丰，期待再次为你服务', NULL);
INSERT INTO `express` VALUES (2, '123456abd', '2023-05-03 19:56:18', '2023-05-10 19:56:23', '[北京市]背景海淀区育新小区营业点派件员 顺丰速递95338正在为你派件', NULL);
INSERT INTO `express` VALUES (3, '123456abd', '2023-05-02 19:58:07', '2023-05-17 19:58:15', '快递到达[北京海淀区育新小区营业点]', NULL);
INSERT INTO `express` VALUES (4, '123456abd', '2023-05-01 19:59:25', '2023-05-16 19:59:29', '[湖南长沙岳麓区]商家已发货 顺丰速递873445正在为你派件', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '南极人女士三角内裤 中腰可爱无痕女士内裤 均码 k102P1027', 34.50, 94, 100, '2023-04-20 21:44:57', 'g01.png', 8, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (2, '邦诺姿 情趣内衣无痕蕾丝提臀诱惑丁字裤中腰档纯棉女士内裤低腰三角裤', 66.60, 43, 12, '2023-04-19 21:46:05', 'g06.png', 8, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (3, '一次性口罩 盒装50只三层加厚无纺布防尘/带铝条', 23.00, 231, 4, '2023-04-22 21:46:56', 'g03.png', 10, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (4, '莱克吸尘器家用M7 大吸力无线手持吸尘器 无耗材除螨吸尘器', 458.00, 58, 23, '2023-03-30 21:48:05', 'g04.png', 10, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (5, '新森宝 兼容乐高积木幻影忍者双头龙益智拼装未来骑士团塑料200块以上男女孩玩具6-14岁 空术神庙村送拆件器', 600.00, 245, 120, '2023-04-23 00:00:00', 'g05.png', 3, '[]', '[]', '<p>哈哈</p>');
INSERT INTO `goods` VALUES (7, '奥迪双钻（AULDEY）超级飞侠-立体造型背包（乐迪款） 710061', 999.99, 8, 0.6, '2023-04-30 09:09:36', 'g08.png', 3, NULL, NULL, NULL);

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
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_category
-- ----------------------------
INSERT INTO `goods_category` VALUES (1, '大家电', 1, 1, NULL, '家电.png');
INSERT INTO `goods_category` VALUES (2, '电视', 1, 2, 1, NULL);
INSERT INTO `goods_category` VALUES (3, '曲面电视', 1, 3, 2, NULL);
INSERT INTO `goods_category` VALUES (4, '海信', 1, 3, 2, NULL);
INSERT INTO `goods_category` VALUES (5, 'TCL', 1, 3, 2, NULL);
INSERT INTO `goods_category` VALUES (6, '服装', 1, 1, NULL, '服装.png');
INSERT INTO `goods_category` VALUES (7, '衣服', 1, 2, 6, NULL);
INSERT INTO `goods_category` VALUES (8, '南极人', 1, 3, 7, NULL);
INSERT INTO `goods_category` VALUES (9, '厨房电器', 1, 2, 1, NULL);
INSERT INTO `goods_category` VALUES (10, '加尔电饭煲', 1, 3, 9, NULL);
INSERT INTO `goods_category` VALUES (11, '书籍', 1, 1, NULL, '书籍.png');
INSERT INTO `goods_category` VALUES (12, '文学', 1, 2, 11, NULL);
INSERT INTO `goods_category` VALUES (13, '网文', 1, 2, 11, NULL);
INSERT INTO `goods_category` VALUES (14, '仙侠', 1, 3, 13, NULL);
INSERT INTO `goods_category` VALUES (16, '历史', 1, 3, 13, NULL);
INSERT INTO `goods_category` VALUES (17, '电器', 1, 1, NULL, '电器.png');
INSERT INTO `goods_category` VALUES (18, '美食', 1, 1, NULL, '美食.png');
INSERT INTO `goods_category` VALUES (19, '经济', 1, 1, 12, '经济.png');

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
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '123456abd', 1, 0, '否', '个人', NULL, NULL, '[\"北京\",\"东城区\"]-天安门', '2023-04-23 19:18:41', 344.09);
INSERT INTO `order` VALUES (60, '34556343454', 2, 1, '是', '公司', '华为游侠公司', NULL, '湖南省长沙市岳麓区中电软件园', '2023-04-26 12:25:18', 866.70);
INSERT INTO `order` VALUES (63, '450878E6CA594C1CAD32A643A86DFCE7', 3, 1, NULL, NULL, NULL, NULL, NULL, '2023-05-02 00:00:00', 69.00);
INSERT INTO `order` VALUES (64, 'C09BEE80185F4B8E8334AC68000E8372', 3, 1, NULL, NULL, NULL, NULL, NULL, '2023-05-02 00:00:00', 202.20);
INSERT INTO `order` VALUES (65, '0E230B5EB38E4E06BD865E1EC2CF96E4', 3, 1, NULL, NULL, NULL, NULL, NULL, '2023-05-05 00:00:00', 103.50);

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` int(0) UNSIGNED NOT NULL COMMENT '订单id',
  `goods_id` mediumint(0) UNSIGNED NOT NULL COMMENT '商品id',
  `goods_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品单价',
  `goods_number` tinyint(0) NOT NULL DEFAULT 1 COMMENT '购买单个商品数量',
  `goods_total_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品小计价格',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品订单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_goods
-- ----------------------------
INSERT INTO `order_goods` VALUES (50, 1, 2, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (51, 1, 1, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (52, 1, 3, 111.00, 3, 333.00);
INSERT INTO `order_goods` VALUES (53, 1, 4, 111.00, 2, 222.00);
INSERT INTO `order_goods` VALUES (54, 1, 5, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (55, 1, 2, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (57, 1, 4, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (58, 1, 1, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (60, 1, 3, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (61, 1, 5, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (63, 1, 2, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (64, 1, 5, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (65, 1, 2, 111.00, 3, 333.00);
INSERT INTO `order_goods` VALUES (66, 1, 3, 111.00, 1, 111.00);
INSERT INTO `order_goods` VALUES (68, 60, 2, 333.00, 2, 999.00);
INSERT INTO `order_goods` VALUES (69, 60, 7, 666.00, 5, 999.00);
INSERT INTO `order_goods` VALUES (70, 61, 96, 333.00, 2, 999.00);
INSERT INTO `order_goods` VALUES (71, 61, 95, 666.00, 5, 999.00);
INSERT INTO `order_goods` VALUES (72, 62, 96, 333.00, 2, 999.00);
INSERT INTO `order_goods` VALUES (73, 62, 95, 666.00, 5, 999.00);
INSERT INTO `order_goods` VALUES (86, 63, 1, 34.50, 2, 69.00);
INSERT INTO `order_goods` VALUES (87, 64, 3, 23.00, 3, 69.00);
INSERT INTO `order_goods` VALUES (88, 64, 2, 66.60, 2, 133.20);
INSERT INTO `order_goods` VALUES (89, 65, 1, 34.50, 3, 103.50);

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
INSERT INTO `user` VALUES (2, '李四', '123456', '3403@qq.com', '119', '软件开发工程师', 0);
INSERT INTO `user` VALUES (3, 'admin', '123456', '456@qq.com', '109', '管理员', 1);
INSERT INTO `user` VALUES (4, 'miko87', '12456', '3456', '123466', '运维', 0);

SET FOREIGN_KEY_CHECKS = 1;
