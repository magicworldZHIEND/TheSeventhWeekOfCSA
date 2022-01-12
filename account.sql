/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : theseventhweek

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2022-01-12 18:25:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` varchar(255) NOT NULL COMMENT 'id号码',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `money` int(255) DEFAULT NULL COMMENT '账户余额',
  `createtime` date DEFAULT NULL COMMENT '开户时间',
  `updatetime` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'A', '14000', '2019-07-14', '2019-07-16');
INSERT INTO `account` VALUES ('2', 'B', '2200', '2020-08-15', '2020-08-19');
INSERT INTO `account` VALUES ('3', 'C', '7000', '2020-08-15', '2020-08-19');
INSERT INTO `account` VALUES ('4', 'ZHIEND', '18000', '2021-12-27', '2021-12-27');
INSERT INTO `account` VALUES ('5', 'E', '10000', '2020-08-15', '2020-08-19');
