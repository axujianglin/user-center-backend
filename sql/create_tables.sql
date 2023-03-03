/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : user-center

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 02/03/2023 19:53:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
                         `user_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录账号',
                         `avatarUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
                         `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
                         `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户电话',
                         `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                         `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态，0表示正常，1表示不可用',
                         `user_role` int NOT NULL DEFAULT 0 COMMENT '0表示普通用户，1表示管理员',
                         `planet_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户唯一的星球编号',
                         `creat_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0表示未删除，1表示删除',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test1', 'xiao', '', 0, '25f9e794323b453885f5181f1b624d0b', '', '', 0, 0, '122', '2023-02-28 16:47:11', '2023-03-02 10:38:27', 0);
INSERT INTO `user` VALUES (5, NULL, 'xiaoXu', NULL, 0, '25f9e794323b453885f5181f1b624d0b', NULL, NULL, 0, 1, '300', '2023-03-01 13:59:16', '2023-03-02 10:38:31', 0);
INSERT INTO `user` VALUES (6, NULL, 'xiaoXu1', NULL, 0, '25f9e794323b453885f5181f1b624d0b', NULL, NULL, 0, 0, '1234', '2023-03-02 11:18:24', '2023-03-02 11:18:24', 0);
INSERT INTO `user` VALUES (7, NULL, 'testPlanet', NULL, 0, '25f9e794323b453885f5181f1b624d0b', NULL, NULL, 0, 0, '301', '2023-03-02 11:25:36', '2023-03-02 11:25:36', 0);

SET FOREIGN_KEY_CHECKS = 1;
