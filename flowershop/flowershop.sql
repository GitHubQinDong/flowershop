DROP DATABASE IF EXISTS flowershop;
CREATE DATABASE flowershop;
USE flowershop;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `catalogid` int(11) NOT NULL AUTO_INCREMENT,
  `catalogname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`catalogid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for flower
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `flowerid` int(11) NOT NULL AUTO_INCREMENT,
  `flowername` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `picture` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `catalogid` int(11) DEFAULT NULL,
  PRIMARY KEY (`flowerid`),
  KEY `FK_flower` (`catalogid`),
  CONSTRAINT `FK_flower` FOREIGN KEY (`catalogid`) REFERENCES `catalog` (`catalogid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderitemid` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `orderid` int(11) DEFAULT NULL,
  `flowerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderitemid`),
  KEY `FK_orderitem` (`flowerid`),
  KEY `FK_orderitem2` (`orderid`),
  CONSTRAINT `FK_orderitem` FOREIGN KEY (`flowerid`) REFERENCES `flower` (`flowerid`) ON UPDATE CASCADE,
  CONSTRAINT `FK_orderitem2` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `orderdate` datetime DEFAULT NULL,
  `totalprice` float DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `FK_orders` (`userid`),
  CONSTRAINT `FK_orders` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for userdetail
-- ----------------------------
DROP TABLE IF EXISTS `userdetail`;
CREATE TABLE `userdetail` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `xb` bit(1) DEFAULT NULL,
  `truename` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `csrq` date DEFAULT NULL,
  `phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `FK_userdetail` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `catalog` VALUES ('1', '红玫瑰');
INSERT INTO `catalog` VALUES ('2', '白玫瑰');
INSERT INTO `catalog` VALUES ('3', '粉玫瑰');
INSERT INTO `catalog` VALUES ('4', '紫玫瑰');
INSERT INTO `catalog` VALUES ('5', '蓝玫瑰');
INSERT INTO `catalog` VALUES ('6', '香槟玫瑰');
INSERT INTO `flower` VALUES ('1', '醉爱-红玫瑰（9支）', '119', 'h1.jpg', '1');
INSERT INTO `flower` VALUES ('2', '香水百合玫瑰', '129', 'h2.jpg', '1');
INSERT INTO `flower` VALUES ('3', '唯爱红玫瑰', '126', 'h3.jpg', '1');
INSERT INTO `flower` VALUES ('4', '梦巴黎红玫瑰', '116', 'h4.jpg', '1');
INSERT INTO `flower` VALUES ('5', '思念红玫瑰', '139', 'h5.jpg', '1');
INSERT INTO `flower` VALUES ('6', '愿得一人心玫瑰', '136', 'h6.jpg', '1');
INSERT INTO `flower` VALUES ('7', '约见', '115', 'l8.jpg', '2');
INSERT INTO `flower` VALUES ('8', '相知', '199', 'b2.jpg', '2');
INSERT INTO `flower` VALUES ('9', '相依相伴', '176', 'b3.jpg', '2');
INSERT INTO `flower` VALUES ('10', '初遇见', '159', 'b4.jpg', '6');
INSERT INTO `flower` VALUES ('11', '心醉', '169', 'b5.jpg', '6');
INSERT INTO `flower` VALUES ('12', '夏之恋', '187', 'b6.jpg', '2');
INSERT INTO `flower` VALUES ('13', '粉红回忆', '201', 'f1.jpg', '3');
INSERT INTO `flower` VALUES ('14', ' 暖', '220', 'f2.jpg', '3');
INSERT INTO `flower` VALUES ('15', '纯真年代', '216', 'b1.jpg', '4');
INSERT INTO `flower` VALUES ('17', '蓝魅', '139', 'l2.jpg', '5');
INSERT INTO `flower` VALUES ('18', '蓝色醉爱', '145', 'l3.jpg', '5');
INSERT INTO `flower` VALUES ('19', '至尊玫瑰', '127', 'l7.jpg', '5');
INSERT INTO `flower` VALUES ('20', '一生一世', '123', 'h7.jpg', '1');
INSERT INTO `flower` VALUES ('21', '海洋之心', '223', 'b7.jpg', '2');
INSERT INTO `flower` VALUES ('22', '一往情深', '440', 'h8.jpg', '1');
INSERT INTO `flower` VALUES ('23', '蓝色妖姬', '123', 'l4.jpg', '5');
INSERT INTO `flower` VALUES ('24', '一片真情', '450', 'g1.jpg', '6');
INSERT INTO `flower` VALUES ('25', '烂漫满屋', '340', 'g2.jpg', '6');
INSERT INTO `flower` VALUES ('26', '苏格兰风情', '201', 'f3.jpg', '3');
INSERT INTO `flower` VALUES ('27', '真爱如初', '203', 'b8.jpg', '2');
INSERT INTO `flower` VALUES ('28', '心心相印', '204', 'b9.jpg', '2');
INSERT INTO `flower` VALUES ('29', '阳光海岸', '109', 'g3.jpg', '6');
INSERT INTO `flower` VALUES ('30', '戴安娜', '112', 'f4.jpg', '3');
INSERT INTO `flower` VALUES ('31', '浅紫罗兰', '134', 'g4.jpg', '6');
INSERT INTO `flower` VALUES ('32', '芳华', '114', 'f5.jpg', '3');
INSERT INTO `flower` VALUES ('33', '美丽出漾', '156', 'f7.jpg', '3');
INSERT INTO `flower` VALUES ('34', '爱如初恋', '421', 'g5.jpg', '6');
INSERT INTO `flower` VALUES ('35', '萌动的心', '167', 'g6.jpg', '6');
INSERT INTO `flower` VALUES ('36', '海之恋', '156', 'l5.jpg', '5');
INSERT INTO `flower` VALUES ('37', '天空之城', '568', 'l6.jpg', '4');
INSERT INTO `flower` VALUES ('38', '梦之蓝', '332', 'l9.jpg', '5');
INSERT INTO `orderitem` VALUES ('26', '1', '26', '1');
INSERT INTO `orderitem` VALUES ('27', '1', '26', '2');
INSERT INTO `orderitem` VALUES ('28', '3', '26', '3');
INSERT INTO `orderitem` VALUES ('29', '1', '27', '33');
INSERT INTO `orderitem` VALUES ('30', '1', '27', '3');
INSERT INTO `orderitem` VALUES ('31', '1', '28', '9');
INSERT INTO `orderitem` VALUES ('32', '1', '30', '1');
INSERT INTO `orderitem` VALUES ('33', '1', '30', '13');
INSERT INTO `orderitem` VALUES ('34', '1', '31', '19');
INSERT INTO `orderitem` VALUES ('35', '1', '31', '10');
INSERT INTO `orderitem` VALUES ('36', '1', '33', '12');
INSERT INTO `orderitem` VALUES ('37', '1', '34', '1');
INSERT INTO `orderitem` VALUES ('38', '1', '34', '11');
INSERT INTO `orderitem` VALUES ('39', '1', '35', '10');
INSERT INTO `orderitem` VALUES ('40', '1', '35', '13');
INSERT INTO `orderitem` VALUES ('41', '1', '36', '8');
INSERT INTO `orderitem` VALUES ('42', '1', '37', '15');
INSERT INTO `orderitem` VALUES ('43', '1', '38', '35');
INSERT INTO `orderitem` VALUES ('44', '1', '38', '33');
INSERT INTO `orderitem` VALUES ('45', '1', '38', '38');
INSERT INTO `orderitem` VALUES ('46', '1', '39', '1');
INSERT INTO `orderitem` VALUES ('47', '10', '40', '1');
INSERT INTO `orderitem` VALUES ('48', '1', '41', '1');
INSERT INTO `orders` VALUES ('26', '2016-05-23 20:22:55', null, '16');
INSERT INTO `orders` VALUES ('27', '2016-05-23 20:52:51', null, '16');
INSERT INTO `orders` VALUES ('28', '2016-05-23 21:15:09', null, '16');
INSERT INTO `orders` VALUES ('30', '2016-05-23 21:20:10', '320', '16');
INSERT INTO `orders` VALUES ('31', '2016-05-23 21:28:59', '286', '16');
INSERT INTO `orders` VALUES ('33', '2016-05-23 21:30:55', '187', '16');
INSERT INTO `orders` VALUES ('34', '2016-05-24 09:28:46', '288', '1');
INSERT INTO `orders` VALUES ('35', '2016-05-24 09:38:18', '360', '1');
INSERT INTO `orders` VALUES ('36', '2016-05-24 10:14:58', '199', '1');
INSERT INTO `orders` VALUES ('37', '2016-05-24 10:16:29', '216', '1');
INSERT INTO `orders` VALUES ('38', '2016-05-24 10:55:22', '655', '1');
INSERT INTO `orders` VALUES ('39', '2016-05-24 13:04:47', '119', '1');
INSERT INTO `orders` VALUES ('40', '2016-05-24 20:43:49', '1190', '19');
INSERT INTO `orders` VALUES ('41', '2016-05-25 16:42:20', '119', '19');
INSERT INTO `user` VALUES ('1', 'tom', '123', 'customer');
INSERT INTO `user` VALUES ('2', 'admin', 'admin666', 'admin');
INSERT INTO `user` VALUES ('3', 'er', '666', 'customer');
INSERT INTO `user` VALUES ('5', 'fg', '66', null);
INSERT INTO `user` VALUES ('15', 'q12345', '1234567', 'customer');
INSERT INTO `user` VALUES ('16', 'w12345', '12345', 'customer');
INSERT INTO `user` VALUES ('17', 'huahua', 'FCEA920F7412B5DA7BE0CF42B8C93759', 'customer');
INSERT INTO `user` VALUES ('18', 'xiao12', 'FCEA920F7412B5DA7BE0CF42B8C93759', 'customer');
INSERT INTO `user` VALUES ('19', 'dalong', 'C8837B23FF8AAA8A2DDE915473CE0991', 'customer');
INSERT INTO `userdetail` VALUES ('1', '', 'tom111', '2016-04-20', '1234', 'tom@qq.com', 'tom111');
INSERT INTO `userdetail` VALUES ('15', '', 'aa', '2016-05-02', '12345678901', '', '浑南新区辽宁省沈阳');
INSERT INTO `userdetail` VALUES ('16', '', 'aa', '2016-05-02', '12345678901', '', '浑南新区辽宁省沈阳');
INSERT INTO `userdetail` VALUES ('17', '', '花花', '2016-05-01', '18946117256', 'huahua@qq.com', '黑龙江省哈尔滨道里区');
INSERT INTO `userdetail` VALUES ('18', '', '小明', '2016-05-01', '12345678901', 'xiao12@qq.com', '黑龙江省null啊啊');
INSERT INTO `userdetail` VALUES ('19', '', '大龙11', '2016-05-01', '15475570911', 'dalomn@qq.com', '辽宁省沈阳道里区');
