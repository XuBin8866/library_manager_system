/*
 Navicat MySQL Data Transfer

 Source Server         : AlwaysXu
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : db_library

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/02/2020 02:39:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_bookborrow
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookborrow`;
CREATE TABLE `tb_bookborrow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `reader_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `book_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `borrow_time` date NULL DEFAULT NULL,
  `return_time` date NULL DEFAULT NULL,
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `if_back` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_id`(`book_id`) USING BTREE,
  INDEX `fk_reader_id`(`reader_id`) USING BTREE,
  CONSTRAINT `fk_book_id` FOREIGN KEY (`book_id`) REFERENCES `tb_bookinfo` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_reader_id` FOREIGN KEY (`reader_id`) REFERENCES `tb_reader` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_bookborrow
-- ----------------------------
INSERT INTO `tb_bookborrow` VALUES (1, 6, 10, '2020-01-30', '2020-03-03', 'xxbb', 1);
INSERT INTO `tb_bookborrow` VALUES (2, 6, 9, '2020-01-31', '2021-01-10', 'root', 1);
INSERT INTO `tb_bookborrow` VALUES (3, 6, 9, '2020-01-31', '2020-02-15', 'root', 1);
INSERT INTO `tb_bookborrow` VALUES (4, 6, 9, '2020-01-31', '2020-02-15', 'root', 1);
INSERT INTO `tb_bookborrow` VALUES (5, 6, 9, '2020-01-31', '2020-02-15', 'root', 1);
INSERT INTO `tb_bookborrow` VALUES (7, 6, 9, '2020-02-04', '2020-02-19', 'root', 1);
INSERT INTO `tb_bookborrow` VALUES (8, 6, 13, '2020-02-08', '2020-02-28', 'root', 0);

-- ----------------------------
-- Table structure for tb_bookcase
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookcase`;
CREATE TABLE `tb_bookcase`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_bookcase
-- ----------------------------
INSERT INTO `tb_bookcase` VALUES (4, '左A-1');
INSERT INTO `tb_bookcase` VALUES (5, '左A-2');
INSERT INTO `tb_bookcase` VALUES (6, '右A-1');
INSERT INTO `tb_bookcase` VALUES (8, '右A-2');

-- ----------------------------
-- Table structure for tb_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookinfo`;
CREATE TABLE `tb_bookinfo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `barcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_name` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `translator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ISBN` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(8, 2) NULL DEFAULT NULL,
  `page` int(10) UNSIGNED NULL DEFAULT NULL,
  `bookcase` int(10) UNSIGNED NULL DEFAULT NULL,
  `intime` date NULL DEFAULT NULL,
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `if_delete` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_type_id`(`type_id`) USING BTREE,
  INDEX `fk_isbn`(`ISBN`) USING BTREE,
  INDEX `fk_bookcase_id`(`bookcase`) USING BTREE,
  CONSTRAINT `fk_book_type_id` FOREIGN KEY (`type_id`) REFERENCES `tb_booktype` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_bookcase_id` FOREIGN KEY (`bookcase`) REFERENCES `tb_bookcase` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_isbn` FOREIGN KEY (`ISBN`) REFERENCES `tb_publishing` (`isbn`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_bookinfo
-- ----------------------------
INSERT INTO `tb_bookinfo` VALUES (1, '123456', '计算机网络', 4, 'wxy', 'wxy', '302', 128.00, 333, 6, '2020-02-03', 'root', 0);
INSERT INTO `tb_bookinfo` VALUES (7, '9787302210337', 'Java Web开发实战宝典', 4, '王国辉', 'wgh', '302', 89.00, 834, 4, '2011-02-24', 'root', 0);
INSERT INTO `tb_bookinfo` VALUES (8, '9787115195975', 'Java Web开发典型模块大全', 4, '王殊', 'ws', '115', 89.00, 752, 5, '2011-02-24', 'root', 0);
INSERT INTO `tb_bookinfo` VALUES (9, '990821333', 'mysql从入门到精通', 5, 'aabb', 'rc', '115', 11.00, 0, 4, '2011-02-24', 'mr', 0);
INSERT INTO `tb_bookinfo` VALUES (10, '990823444', 'SpringMVC入门', 4, 'xxbb', 'rc', '111', 26.00, 0, 4, '2011-02-24', 'mr', 0);
INSERT INTO `tb_bookinfo` VALUES (13, '990823445', 'SpringMVC入门', 4, 'xxbb', 'rc', '111', 26.00, 0, 4, '2020-02-08', 'root', 0);

-- ----------------------------
-- Table structure for tb_bookreturn
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookreturn`;
CREATE TABLE `tb_bookreturn`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `reader_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `book_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `return_time` date NULL DEFAULT NULL,
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_id_return`(`book_id`) USING BTREE,
  INDEX `fk_reader_id_return`(`reader_id`) USING BTREE,
  CONSTRAINT `fk_book_id_return` FOREIGN KEY (`book_id`) REFERENCES `tb_bookinfo` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_reader_id_return` FOREIGN KEY (`reader_id`) REFERENCES `tb_reader` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_bookreturn
-- ----------------------------
INSERT INTO `tb_bookreturn` VALUES (3, 4, 7, '2011-02-24', 'mr');
INSERT INTO `tb_bookreturn` VALUES (7, 6, 9, '2020-02-01', 'root');
INSERT INTO `tb_bookreturn` VALUES (8, 6, 9, '2020-02-01', 'root');
INSERT INTO `tb_bookreturn` VALUES (9, 6, 9, '2020-02-01', 'root');
INSERT INTO `tb_bookreturn` VALUES (10, 6, 9, '2020-02-04', 'root');
INSERT INTO `tb_bookreturn` VALUES (11, 6, 10, '2020-02-08', 'root');

-- ----------------------------
-- Table structure for tb_booktype
-- ----------------------------
DROP TABLE IF EXISTS `tb_booktype`;
CREATE TABLE `tb_booktype`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `limited_time` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_booktype
-- ----------------------------
INSERT INTO `tb_booktype` VALUES (4, '网络编程', 20);
INSERT INTO `tb_booktype` VALUES (5, '数据库开发', 15);
INSERT INTO `tb_booktype` VALUES (6, '操作系统', 90);

-- ----------------------------
-- Table structure for tb_library
-- ----------------------------
DROP TABLE IF EXISTS `tb_library`;
CREATE TABLE `tb_library`  (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `curator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_library
-- ----------------------------
INSERT INTO `tb_library` VALUES (1, '凯尔莫罕图书馆', '徐斌斌', '0431-84978981', 'mingrisoft@mingsoft.com', '本市拥有计算机类图书最多的图书馆。', 'http:// www.KaerMorhen.xxbb.com', '2019-12-12', '凯尔莫罕');

-- ----------------------------
-- Table structure for tb_manager
-- ----------------------------
DROP TABLE IF EXISTS `tb_manager`;
CREATE TABLE `tb_manager`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_manager
-- ----------------------------
INSERT INTO `tb_manager` VALUES (1, 'root', '123456');
INSERT INTO `tb_manager` VALUES (2, 'mr', '123456');
INSERT INTO `tb_manager` VALUES (3, 'wgh', '123456');
INSERT INTO `tb_manager` VALUES (4, 'admin', '123456');
INSERT INTO `tb_manager` VALUES (5, 'xxbb', '123456');
INSERT INTO `tb_manager` VALUES (6, 'aabb', '123456');
INSERT INTO `tb_manager` VALUES (9, 'rick', '123456');

-- ----------------------------
-- Table structure for tb_parameter
-- ----------------------------
DROP TABLE IF EXISTS `tb_parameter`;
CREATE TABLE `tb_parameter`  (
  `id` int(10) UNSIGNED NOT NULL,
  `cost` int(10) UNSIGNED NULL DEFAULT NULL,
  `validity` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_parameter
-- ----------------------------
INSERT INTO `tb_parameter` VALUES (1, 30, 12);

-- ----------------------------
-- Table structure for tb_publishing
-- ----------------------------
DROP TABLE IF EXISTS `tb_publishing`;
CREATE TABLE `tb_publishing`  (
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_publishing
-- ----------------------------
INSERT INTO `tb_publishing` VALUES ('111', '机械工业出版社');
INSERT INTO `tb_publishing` VALUES ('115', '人民邮电出版社');
INSERT INTO `tb_publishing` VALUES ('302', '清华大学出版社');

-- ----------------------------
-- Table structure for tb_purview
-- ----------------------------
DROP TABLE IF EXISTS `tb_purview`;
CREATE TABLE `tb_purview`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_set` tinyint(1) NULL DEFAULT 0,
  `reader_set` tinyint(1) NULL DEFAULT 0,
  `book_set` tinyint(1) NULL DEFAULT 0,
  `borrow_set` tinyint(1) NULL DEFAULT 1,
  `system_query` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purview
-- ----------------------------
INSERT INTO `tb_purview` VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `tb_purview` VALUES (2, 0, 0, 1, 1, 1);
INSERT INTO `tb_purview` VALUES (3, 0, 0, 1, 1, 1);
INSERT INTO `tb_purview` VALUES (4, 0, 0, 0, 0, 1);
INSERT INTO `tb_purview` VALUES (5, 0, 0, 1, 0, 1);
INSERT INTO `tb_purview` VALUES (6, 0, 1, 0, 0, 0);
INSERT INTO `tb_purview` VALUES (9, 1, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for tb_reader
-- ----------------------------
DROP TABLE IF EXISTS `tb_reader`;
CREATE TABLE `tb_reader`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vocation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `paper_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paper_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type_id` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `barcode`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `fk_reader_type`(`type_id`) USING BTREE,
  CONSTRAINT `fk_reader_type` FOREIGN KEY (`type_id`) REFERENCES `tb_readertype` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_reader
-- ----------------------------
INSERT INTO `tb_reader` VALUES (4, '琦琦', '女', '20110224000001', '学生', '2010-07-10', '身份证', '220104201007100001', '84978981', 'wgh717@sohu.com', '2011-02-24', 'mr', '无', 4);
INSERT INTO `tb_reader` VALUES (5, 'wgh', '女', '20110224000002', '程序员', '1980-07-10', '工作证', '20010228', '84978981', 'wgh717@sohu.com', '2011-02-24', 'mr', '无', 4);
INSERT INTO `tb_reader` VALUES (6, 'xxbb', '男', '8866', '运维', '2020-01-16', '身份证', '362426xxxx', '337845818', '337845818', '2011-02-24', 'mr', 'Administrator！！！', 1);
INSERT INTO `tb_reader` VALUES (8, '瓜瓜', '男', '20200202', '梦想家', '2020-02-02', '身份证', '3362426yyy', '18870', '18870', '2020-02-03', 'root', '欢迎梦想家！！！', 17);

-- ----------------------------
-- Table structure for tb_readertype
-- ----------------------------
DROP TABLE IF EXISTS `tb_readertype`;
CREATE TABLE `tb_readertype`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `allow_borrow_amount` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_readertype
-- ----------------------------
INSERT INTO `tb_readertype` VALUES (1, '管理员', 100);
INSERT INTO `tb_readertype` VALUES (4, '学生', 5);
INSERT INTO `tb_readertype` VALUES (12, '市民', 10);
INSERT INTO `tb_readertype` VALUES (17, '员工', 20);

-- ----------------------------
-- Triggers structure for table tb_manager
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_trigger`;
delimiter ;;
CREATE TRIGGER `insert_trigger` AFTER INSERT ON `tb_manager` FOR EACH ROW insert into tb_purview(id) VALUES(new.id)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
