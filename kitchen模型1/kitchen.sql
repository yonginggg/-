/*
Navicat MySQL Data Transfer

Source Server         : booklib
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : kitchen

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2019-08-23 14:00:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_administrator_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_administrator_information`;
CREATE TABLE `tbl_administrator_information` (
  `administrator_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `administrator_name` int(11) NOT NULL COMMENT '员工姓名',
  `administrator_password` int(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`administrator_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_ingredients_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ingredients_category`;
CREATE TABLE `tbl_ingredients_category` (
  `category_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `category_name` varchar(11) NOT NULL COMMENT '类别名称',
  `category_description` varchar(255) NOT NULL COMMENT '类别描述',
  PRIMARY KEY (`category_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_ingredients_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ingredients_information`;
CREATE TABLE `tbl_ingredients_information` (
  `ingredients_number` int(50) NOT NULL AUTO_INCREMENT COMMENT '食材编号',
  `ingredients_name` varchar(255) NOT NULL COMMENT '食材名称',
  `ingredients_price` double NOT NULL COMMENT '价格',
  `ingredients_quantity` int(50) NOT NULL COMMENT '数量',
  `ingredients_description` varchar(50) NOT NULL COMMENT '描述',
  `ingredients_specification` varchar(50) NOT NULL COMMENT '规格',
  `category_number` int(11) NOT NULL COMMENT '类别编号',
  PRIMARY KEY (`ingredients_number`),
  KEY `category_number` (`category_number`),
  CONSTRAINT `tbl_ingredients_information_ibfk_1` FOREIGN KEY (`category_number`) REFERENCES `tbl_ingredients_category` (`category_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_ingredients_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ingredients_order`;
CREATE TABLE `tbl_ingredients_order` (
  `order_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_number` int(11) NOT NULL COMMENT '用户编号',
  `order_required_time` datetime NOT NULL COMMENT '要求送达时间',
  `order_delivery_address` varchar(50) NOT NULL COMMENT '配送地址',
  `user_phone_number` int(11) NOT NULL COMMENT '联系电话',
  `order_status` varchar(50) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_number`),
  KEY `user_number` (`user_number`),
  CONSTRAINT `tbl_ingredients_order_ibfk_1` FOREIGN KEY (`user_number`) REFERENCES `tbl_user_information` (`user_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_ingredients_procurement
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ingredients_procurement`;
CREATE TABLE `tbl_ingredients_procurement` (
  `procurement_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购单编号',
  `ingredients_number` int(11) NOT NULL COMMENT '食材编号',
  `quantity` int(50) NOT NULL COMMENT '数量',
  `procurement_status` varchar(50) NOT NULL COMMENT '状态',
  `administrator_number` int(11) NOT NULL COMMENT '员工编号',
  PRIMARY KEY (`procurement_number`),
  KEY `administrator_number` (`administrator_number`),
  KEY `ingredients_number` (`ingredients_number`),
  CONSTRAINT `tbl_ingredients_procurement_ibfk_1` FOREIGN KEY (`administrator_number`) REFERENCES `tbl_administrator_information` (`administrator_number`),
  CONSTRAINT `tbl_ingredients_procurement_ibfk_2` FOREIGN KEY (`ingredients_number`) REFERENCES `tbl_ingredients_information` (`ingredients_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_detail`;
CREATE TABLE `tbl_order_detail` (
  `order_number` int(11) NOT NULL COMMENT '订单编号',
  `ingredients_number` int(11) NOT NULL COMMENT '食材编号',
  `quantity` int(50) NOT NULL COMMENT '数量',
  `price` double NOT NULL COMMENT '价格',
  `order_discount` double NOT NULL COMMENT '折扣',
  PRIMARY KEY (`order_number`,`ingredients_number`),
  CONSTRAINT `tbl_order_detail_ibfk_1` FOREIGN KEY (`order_number`) REFERENCES `tbl_ingredients_order` (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_recipe_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tbl_recipe_evaluation`;
CREATE TABLE `tbl_recipe_evaluation` (
  `recipe_number` int(11) NOT NULL COMMENT '菜谱编号',
  `user_number` int(11) NOT NULL COMMENT '用户编号',
  `evaluation_conten` varchar(50) NOT NULL COMMENT '评价内容',
  `evaluation_browse_sign` int(11) NOT NULL COMMENT '浏览标志',
  `evaluation_collection_sign` int(11) NOT NULL COMMENT '收藏标志',
  `evaluation_grade` double NOT NULL COMMENT '评分',
  PRIMARY KEY (`recipe_number`,`user_number`),
  CONSTRAINT `tbl_recipe_evaluation_ibfk_1` FOREIGN KEY (`recipe_number`) REFERENCES `tbl_recipe_information` (`recipe_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_recipe_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_recipe_information`;
CREATE TABLE `tbl_recipe_information` (
  `recipe_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜谱编号',
  `recipe_name` varchar(50) NOT NULL COMMENT '菜谱名称',
  `user_number` int(11) NOT NULL COMMENT '贡献用户',
  `recipe_description` varchar(255) NOT NULL COMMENT '菜谱描述',
  `recipe_overall_rating` double NOT NULL COMMENT '综合评分',
  `recipe_collection_number` int(11) NOT NULL COMMENT '收藏数量',
  `recipe_views_number` int(11) NOT NULL COMMENT '浏览次数',
  PRIMARY KEY (`recipe_number`),
  KEY `user_number` (`user_number`),
  CONSTRAINT `tbl_recipe_information_ibfk_1` FOREIGN KEY (`user_number`) REFERENCES `tbl_user_information` (`user_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_recipe_material
-- ----------------------------
DROP TABLE IF EXISTS `tbl_recipe_material`;
CREATE TABLE `tbl_recipe_material` (
  `recipe_number` int(11) NOT NULL COMMENT '菜谱编号',
  `ingredients_number` int(50) NOT NULL COMMENT '食材编号',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `unit` varchar(50) NOT NULL COMMENT '单位',
  PRIMARY KEY (`recipe_number`,`ingredients_number`),
  KEY `ingredients_number` (`ingredients_number`),
  CONSTRAINT `tbl_recipe_material_ibfk_1` FOREIGN KEY (`recipe_number`) REFERENCES `tbl_recipe_information` (`recipe_number`),
  CONSTRAINT `tbl_recipe_material_ibfk_2` FOREIGN KEY (`ingredients_number`) REFERENCES `tbl_ingredients_information` (`ingredients_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_recipe_step
-- ----------------------------
DROP TABLE IF EXISTS `tbl_recipe_step`;
CREATE TABLE `tbl_recipe_step` (
  `recipe_number` int(11) NOT NULL COMMENT '菜谱编号',
  `step_number` int(11) NOT NULL COMMENT '步骤序号',
  `step_description` varchar(50) NOT NULL COMMENT '步骤描述',
  PRIMARY KEY (`recipe_number`,`step_number`),
  CONSTRAINT `tbl_recipe_step_ibfk_1` FOREIGN KEY (`recipe_number`) REFERENCES `tbl_recipe_information` (`recipe_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_user_information
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_information`;
CREATE TABLE `tbl_user_information` (
  `user_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(50) NOT NULL COMMENT '姓名',
  `user_sex` varchar(50) NOT NULL COMMENT '性别',
  `user_password` varchar(50) NOT NULL COMMENT '密码',
  `user_phone_number` int(11) NOT NULL COMMENT '手机号码',
  `user_email` varchar(50) NOT NULL COMMENT '邮箱',
  `user_city` varchar(50) NOT NULL COMMENT '所在城市',
  `user_register` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
