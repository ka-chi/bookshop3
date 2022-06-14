/*
 Navicat Premium Data Transfer

 Source Server         : blog
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 121.40.251.111:3306
 Source Schema         : bookShop

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 28/11/2021 20:40:52
*/

---this is a test.................
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `booka`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publishing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` float(255, 0) NULL DEFAULT NULL COMMENT '价格',
  `sale_num` int(0) NULL DEFAULT NULL COMMENT '销量',
  `log` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `inventory` int(0) NULL DEFAULT NULL COMMENT '库存',
  `shop_id` int(0) NULL DEFAULT NULL COMMENT '商铺id',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容简介',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '图书类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (3, '《资治通鉴》', '司马光', '光明日报出版社', 60, 3, 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/40543961/O1CN01e9wni11f8BW0ec3kA_!!0-saturn_solar.jpg_250x250.jpg', 100, 1, '111233123311331', NULL, 7);
INSERT INTO `book` VALUES (4, '《内蒙古通史》', '郝维民', '人民出版社', 1410, 4, 'https://img.alicdn.com/imgextra/i2/47377476/O1CN01VvKket2563gDrOhjS_!!2-saturn_solar.png_270x270.jpg', 100, 6, NULL, NULL, 6);
INSERT INTO `book` VALUES (5, '《羽毛球全攻略》', '贝恩德-沃克尔·勃拉姆斯', '人民邮电出版社', 38, 12, 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/132542465/O1CN01lXpTLk1U510Udl5xi_!!0-saturn_solar.jpg_250x250.jpg_.webp', 100, 6, NULL, NULL, 10);
INSERT INTO `book` VALUES (16, '富婆爱我', '向超', '向超出版社', 123, 0, 'https://shu-ai-blog.oss-cn-beijing.aliyuncs.com/articles/1442686554273705985.jpg', 123, 7, NULL, NULL, 8);

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `book_id` int(0) NOT NULL,
  `type_id` int(0) NOT NULL COMMENT '三级分类的id',
  PRIMARY KEY (`book_id`, `type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, 6);
INSERT INTO `book_type` VALUES (1, 7);
INSERT INTO `book_type` VALUES (2, 7);
INSERT INTO `book_type` VALUES (3, 7);
INSERT INTO `book_type` VALUES (4, 6);
INSERT INTO `book_type` VALUES (5, 10);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论',
  `star` int(0) NULL DEFAULT NULL COMMENT '星级',
  `c_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时间',
  `c_id` int(0) NULL DEFAULT NULL COMMENT '顾客id',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '图书id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (3, '司马光真牛逼', 5, '2021-11-12 11:57:45', 6, 5);
INSERT INTO `comment` VALUES (4, '搜索三', 4, '2021-11-12 11:58:12', 7, 5);
INSERT INTO `comment` VALUES (5, '111', 4, '2021-11-12 15:16:13', 6, 5);
INSERT INTO `comment` VALUES (6, '请问恶趣味', 5, '2021-11-12 15:16:41', 6, 5);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称，不能为空',
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别，默认男',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号，不为空',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码，不为空，不少于6位',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `money` float(255, 0) NULL DEFAULT 0 COMMENT '用户余额，初始为0',
  `register_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间，',
  `authentic` int(0) NULL DEFAULT NULL COMMENT '权限，用户1，商家2，超级管理员0.',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收获地址',
  `remark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段2',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_id`(`username`, `name`) USING BTREE COMMENT '用户名不可重复'
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (6, '11', '1', 'cust11', '$2a$10$v7bxQOmbFyEUAFsyPaH.i.Sl8gR7b.kcy0AKrrxP9wQG0KVo90OYa', '17374596260', 7036, '2021-10-30 12:28:28', 1, '长沙', NULL);
INSERT INTO `customer` VALUES (7, '12', '1', 'cust12', '$2a$10$X9sJl0zA/6CxaLRo/JN1fukY870w5od05jvmw2soGuTp6mqPS9Wjm', '17374596260', 8000, '2021-10-30 13:09:34', 1, '长沙', NULL);
INSERT INTO `customer` VALUES (9, 'xc', '1', 'custxc', '$2a$10$ctLhIKkPT828S8k43241OugDF.7qo9pG/vfEjdAXyB1CJo96cFMNy', '17374596260', 20236, '2021-11-27 10:26:30', 1, 'cs', NULL);

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `authentic` int(0) NOT NULL COMMENT '权限',
  `c_id` int(0) NULL DEFAULT NULL COMMENT '客户对应id',
  `s_id` int(0) NULL DEFAULT NULL COMMENT '商店对应id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商铺名字或用户昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (1, 'supershuai', '$2a$10$tr62FdSv.WI2L7OmtwFD3u98w83A/hFx2dkC5Hhvr66wKQyh2Bb7W', 0, NULL, NULL, NULL);
INSERT INTO `login` VALUES (4, 'shopbooksaler', '$2a$10$tr62FdSv.WI2L7OmtwFD3u98w83A/hFx2dkC5Hhvr66wKQyh2Bb7W', 2, NULL, 1, NULL);
INSERT INTO `login` VALUES (5, 'shopgm', '$10$v7bxQOmbFyEUAFsyPaH.i.Sl8gR7b.kcy0AKrrxP9wQG0KVo90OYa', 2, NULL, 3, NULL);
INSERT INTO `login` VALUES (6, 'cust11', '$2a$10$v7bxQOmbFyEUAFsyPaH.i.Sl8gR7b.kcy0AKrrxP9wQG0KVo90OYa', 1, 6, NULL, '11');
INSERT INTO `login` VALUES (7, 'cust12', '$2a$10$X9sJl0zA/6CxaLRo/JN1fukY870w5od05jvmw2soGuTp6mqPS9Wjm', 1, 7, NULL, '12');
INSERT INTO `login` VALUES (8, 'shopqf', '$2a$10$v7bxQOmbFyEUAFsyPaH.i.Sl8gR7b.kcy0AKrrxP9wQG0KVo90OYa', 2, NULL, 6, '起飞');
INSERT INTO `login` VALUES (10, 'custxc', '$2a$10$ctLhIKkPT828S8k43241OugDF.7qo9pG/vfEjdAXyB1CJo96cFMNy', 1, 9, NULL, 'xc');
INSERT INTO `login` VALUES (11, 'shopxc', '$2a$10$pP4ZYHcmKEqY9vL3RIzxKO83nC6GDc8j0MTNRxbb0IbM2bw7DWbYO', 2, NULL, 7, 'xc');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `c_id` int(0) NULL DEFAULT NULL COMMENT '顾客id',
  `book_id` int(0) NULL DEFAULT NULL COMMENT '图书id',
  `shop_id` int(0) NULL DEFAULT NULL COMMENT '商家id',
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该单数量',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `pay_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付时间',
  `sum_money` float(255, 0) NULL DEFAULT NULL COMMENT '改单总价格',
  `is_comment` int(0) NULL DEFAULT 0 COMMENT '是否评价改订单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (26, 6, 3, 1, '1', '3', '1458330230367916035', '长沙', '2021-11-10 15:06:36', 60, 0);
INSERT INTO `orders` VALUES (27, 6, 5, 6, '1', '4', '1458330230367916036', '长沙', '2021-11-10 15:06:36', 38, 0);
INSERT INTO `orders` VALUES (28, 6, 4, 6, '1', '4', '1458330230367916036', '长沙', '2021-11-10 15:06:36', 1410, 1);
INSERT INTO `orders` VALUES (29, 6, 3, 6, '3', '2', '1458368521658109954', '北京', '2021-11-10 17:38:45', 180, 0);
INSERT INTO `orders` VALUES (30, 6, 5, 6, '1', '4', '1458368521658109955', '长沙', '2021-11-10 17:38:45', 38, 0);
INSERT INTO `orders` VALUES (31, 6, 4, 6, '1', '4', '1458368521658109955', '长沙', '2021-11-10 17:38:45', 1410, 0);
INSERT INTO `orders` VALUES (32, 6, 5, 6, '2', '4', '1459146683887271938', '长沙', '2021-11-12 21:10:53', 76, 0);
INSERT INTO `orders` VALUES (33, 6, 3, 1, '1', '0', '1459148023602495491', '长沙', '2021-11-12 21:16:13', 60, 0);
INSERT INTO `orders` VALUES (34, 6, 4, 6, '1', '0', '1459148023602495492', '长沙', '2021-11-12 21:16:13', 1410, 0);
INSERT INTO `orders` VALUES (35, 6, 3, 1, '1', '3', '1459148296563605507', '长沙', '2021-11-12 21:17:18', 60, 0);
INSERT INTO `orders` VALUES (36, 6, 5, 6, '1', '0', '1459148296563605508', '长沙4', '2021-11-12 21:17:18', 38, 0);
INSERT INTO `orders` VALUES (37, 6, 4, 6, '1', '0', '1459148296563605508', '长沙4', '2021-11-12 21:17:18', 1410, 0);
INSERT INTO `orders` VALUES (38, 9, 5, 6, '1', '0', '1464436499457675266', '湖南', '2021-11-27 11:30:44', 38, 0);
INSERT INTO `orders` VALUES (39, 9, 4, 6, '1', '0', '1464436499457675266', '湖南', '2021-11-27 11:30:44', 1410, 0);
INSERT INTO `orders` VALUES (40, 9, 16, 7, '1', '2', '1464456814137102339', 'cs', '2021-11-27 12:51:27', 123, 0);
INSERT INTO `orders` VALUES (41, 9, 5, 6, '4', '0', '1464612146368937987', 'cs', '2021-11-27 23:08:41', 152, 0);
INSERT INTO `orders` VALUES (42, 9, 5, 6, '1', '0', '1464896183302070273', 'cs', '2021-11-28 17:57:21', 38, 0);
INSERT INTO `orders` VALUES (43, 9, 5, 6, '1', '0', '1464926712525201411', 'cs', '2021-11-28 19:58:39', 38, 0);
INSERT INTO `orders` VALUES (44, 9, 16, 7, '1', '4', '1464926985419202563', 'cs', '2021-11-28 19:59:45', 123, 0);

-- ----------------------------
-- Table structure for shopcar
-- ----------------------------
DROP TABLE IF EXISTS `shopcar`;
CREATE TABLE `shopcar`  (
  `c_id` int(0) NOT NULL,
  `book_id` int(0) NOT NULL,
  `num` int(0) NOT NULL,
  `price` float NOT NULL,
  `id` int(0) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopcar
-- ----------------------------
INSERT INTO `shopcar` VALUES (6, 4, 1, 1410, 53);
INSERT INTO `shopcar` VALUES (0, 5, 3, 38, 54);

-- ----------------------------
-- Table structure for shopper
-- ----------------------------
DROP TABLE IF EXISTS `shopper`;
CREATE TABLE `shopper`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家名称',
  `shop_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家地址',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营类型',
  `register_money` float(255, 0) NULL DEFAULT NULL COMMENT '注册资金',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `log` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'log图片，这里存储地址',
  `check_status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态，0表示待审核，1表示通过，2表示未通过',
  `register_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间',
  `authentic` int(0) NULL DEFAULT NULL COMMENT '权限',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段',
  `remark2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用字段2',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_id2`(`username`, `shop_name`) USING BTREE COMMENT '用户名不重复'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopper
-- ----------------------------
INSERT INTO `shopper` VALUES (1, '买书人', '长沙', '史书', 0, '13389657458', 'http://sc.adminbuy.cn/uploads/allimg/160920/1-160920153S70-L.png', '1', '2020-10-22', 2, 'shopbooksaler', '$10$tr62FdSv.WI2L7OmtwFD3u98w83A/hFx2dkC5Hhvr66wKQyh2Bb7W', '', NULL);
INSERT INTO `shopper` VALUES (3, '体育人', '长沙', '体育', 0, '13366554422', 'http://sc.adminbuy.cn/uploads/allimg/160920/1-160920153S70-L.png', '1', '2020-10-22', 2, 'shopgm', '$10$tr62FdSv.WI2L7OmtwFD3u98w83A/hFx2dkC5Hhvr66wKQyh2Bb7W', NULL, NULL);
INSERT INTO `shopper` VALUES (6, '起飞', '长沙', '图书', 100, '18229790113', 'http://sc.adminbuy.cn/uploads/allimg/160920/1-160920153S70-L.png', '1 ', '2021-11-01 13:54:09', 2, 'shopqf', '$2a$10$v7bxQOmbFyEUAFsyPaH.i.Sl8gR7b.kcy0AKrrxP9wQG0KVo90OYa', NULL, NULL);
INSERT INTO `shopper` VALUES (7, 'xc', 'cs', '文史', 100000000, '18800000000', 'http://sc.adminbuy.cn/uploads/allimg/160920/1-160920153S70-L.png', '1', '2021-11-27 12:17:32', 2, 'shopxc', '$2a$10$pP4ZYHcmKEqY9vL3RIzxKO83nC6GDc8j0MTNRxbb0IbM2bw7DWbYO', NULL, NULL);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父分类',
  `level` int(0) NULL DEFAULT NULL COMMENT '分类等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '文史', NULL, 1);
INSERT INTO `type` VALUES (2, '科技', NULL, 1);
INSERT INTO `type` VALUES (3, '体育', NULL, 1);
INSERT INTO `type` VALUES (4, '中国文史', 1, 2);
INSERT INTO `type` VALUES (5, '外国文史', 1, 2);
INSERT INTO `type` VALUES (6, '中国近代', 4, 3);
INSERT INTO `type` VALUES (7, '中国古代', 4, 3);
INSERT INTO `type` VALUES (8, '中国现代', 4, 3);
INSERT INTO `type` VALUES (9, '球类运动', 3, 2);
INSERT INTO `type` VALUES (10, '羽毛球', 9, 3);

SET FOREIGN_KEY_CHECKS = 1;
