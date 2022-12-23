/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : nft

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 23/12/2022 21:06:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_info`;
CREATE TABLE `commodity_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `commodity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_price` decimal(10, 2) NULL DEFAULT NULL,
  `by_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `by_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auction` decimal(10, 0) NULL DEFAULT NULL,
  `auctionid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_info
-- ----------------------------
INSERT INTO `commodity_info` VALUES (30, 'dz', 120.00, NULL, 'admin', 'dz', '1671611592213-1029538113.jpg', NULL, '2022-12-21 :04:33:12', NULL, NULL, NULL);
INSERT INTO `commodity_info` VALUES (31, '12', 12.00, NULL, 'admin', '12', '1671612661783574561774.jpg', NULL, '2022-12-21 :04:51:01', NULL, NULL, NULL);
INSERT INTO `commodity_info` VALUES (32, '123', 123.00, NULL, 'admin', '123', '16716128889731746683608.jpg', NULL, '2022-12-21 :04:54:49', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for conferences
-- ----------------------------
DROP TABLE IF EXISTS `conferences`;
CREATE TABLE `conferences`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `adminId` int(0) NULL DEFAULT NULL COMMENT '管理员Id',
  `isFirst` int(0) NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL COMMENT '用于评价表进行链接',
  `conferenceFullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会议全程',
  `conferenceShortName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议简称',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `conferenceHoldTime` timestamp(0) NOT NULL COMMENT '会议举行时间',
  `conferenceEndTime` timestamp(0) NOT NULL COMMENT '会议结束时间',
  `deadline` timestamp(0) NOT NULL COMMENT '截稿日期',
  `conferenceCity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议举办城市',
  `conferenceType` tinyint(0) NULL DEFAULT NULL COMMENT '会议类型，1位线下，0位线上',
  `conferenceWebsite` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议网站',
  `conferenceLanguage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议语言',
  `isPublisher` tinyint(0) NULL DEFAULT NULL COMMENT '是否涉及到出版论文',
  `publication` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版刊物',
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要出版社',
  `publishIndex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版检索',
  `conferenceIntroduction` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT '会议简介',
  `phaseTime` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT ' 节点日期/时间\n',
  `membership` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议嘉宾',
  `callForPapers` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议征稿',
  `detailedPublishIndex` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细出版检索',
  `conferenceRegister` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议注册',
  `connection` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '联系方式',
  `conferencePoster` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议海报',
  `totalNoSendPaper` int(0) NULL DEFAULT NULL COMMENT '用户未投稿人数',
  `totalUserNum` int(0) NULL DEFAULT NULL COMMENT '用户评论总人数',
  `totalUserScore` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户评论分数总和list',
  `platformScore` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台评分  list',
  `conferenceState` tinyint(0) NULL DEFAULT 2 COMMENT '0不通过，1返回重修，2待审核，3通过',
  `clickTime` int(0) NULL DEFAULT NULL COMMENT '点击次数',
  `isPopular` tinyint(0) NULL DEFAULT NULL COMMENT '是否热门',
  `createTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isReturnUpdate` tinyint(0) NULL DEFAULT NULL,
  `isDelete` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `conferences_conferenceFullName_uindex`(`conferenceFullName`) USING BTREE,
  INDEX `conferences_admin_id_fk`(`adminId`) USING BTREE,
  INDEX `conferences_comment_conferenceNameLink_fk`(`parentId`) USING BTREE,
  INDEX `conferences_user_id_fk`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会议表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `commodity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_id` int(0) NULL DEFAULT NULL,
  `order_hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_price` decimal(10, 2) NULL DEFAULT NULL,
  `commodity_images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seller_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buyer_id` int(0) NULL DEFAULT NULL,
  `buyer_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(0) NULL DEFAULT NULL,
  `cmd_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cmd_price` decimal(10, 2) NULL DEFAULT NULL,
  `cmd_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `cmd_images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tempname
-- ----------------------------
DROP TABLE IF EXISTS `tempname`;
CREATE TABLE `tempname`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `commodity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_price` decimal(10, 2) NULL DEFAULT NULL,
  `commodity_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commodity_images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monery_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance_price` decimal(10, 2) NULL DEFAULT NULL,
  `head_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zs', '$2a$10$F1FK2mxVoB1pHHlJ/b3eDeKFwAoW7BU3VgxBU86DT5w.Cu7zUVuWy', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (22, 'zs1', '123456', 'MoneyAddress', 0.00, NULL, '2022-12-23 20:03:42', NULL);

SET FOREIGN_KEY_CHECKS = 1;
