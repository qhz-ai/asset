/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : pp_base

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 01/03/2021 18:26:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'sys_msg', '消息', NULL, NULL, 'SysMsg', 'crud', 'com.ruoyi.sys', 'sys', 'msg', '消息', 'yepanpan', '0', '/', '{\"parentMenuId\":\"2\"}', 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53', NULL);
INSERT INTO `gen_table` VALUES (2, 'sys_chart', '报表', NULL, NULL, 'SysChart', 'crud', 'com.ruoyi.sys', 'sys', 'chart', '报表管理', 'yepanpan', '0', '/', '{\"parentMenuId\":2002}', 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57', NULL);
INSERT INTO `gen_table` VALUES (3, 'sys_dataset', '数据集', NULL, NULL, 'SysDataset', 'crud', 'com.ruoyi.sys', 'sys', 'dataset', '数据集', 'yepanpan', '0', '/', '{\"parentMenuId\":2002}', 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:25', NULL);
INSERT INTO `gen_table` VALUES (4, 'xdata_client', '应用', NULL, NULL, 'XdataClient', 'crud', 'com.ruoyi.sys', 'sys', 'client', '应用管理', 'yepanpan', '0', '/', '{\"parentMenuId\":2001}', 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56', NULL);
INSERT INTO `gen_table` VALUES (5, 'xdata_model', '模型', NULL, NULL, 'XdataModel', 'crud', 'com.ruoyi.sys', 'sys', 'model', '模型管理', 'yepanpan', '0', '/', '{\"parentMenuId\":2001}', 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21', NULL);
INSERT INTO `gen_table` VALUES (6, 'xdata_pipe', '通道', NULL, NULL, 'XdataPipe', 'crud', 'com.ruoyi.sys', 'sys', 'pipe', '通道', 'yepanpan', '0', '/', '{\"parentMenuId\":2001}', 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (2, '1', 'title', '标题', 'varchar(100)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (3, '1', 'content', '内容', 'varchar(255)', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (4, '1', 'url', '链接', 'varchar(255)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (5, '1', 'type', '消息类型', 'varchar(1)', 'String', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', 'ext_msg_type', 5, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (6, '1', 'add_time', '发送时间', 'datetime', 'Date', 'addTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (7, '1', 'add_user', '发信人', 'int(11)', 'Long', 'addUser', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (8, '1', 'add_user_name', '发信人姓名', 'varchar(255)', 'String', 'addUserName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 8, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (9, '1', 'to_user', '收信人', 'int(11)', 'Long', 'toUser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (10, '1', 'to_user_name', '收信人姓名', 'varchar(50)', 'String', 'toUserName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 10, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (11, '1', 'read_time', '读取时间', 'datetime', 'Date', 'readTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 11, 'admin', '2021-02-25 17:34:22', '', '2021-02-25 17:53:53');
INSERT INTO `gen_table_column` VALUES (12, '2', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (13, '2', 'type', '类型', 'varchar(10)', 'String', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (14, '2', 'code', '代码', 'varchar(50)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (15, '2', 'title', '名称', 'varchar(100)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (16, '2', 'ds_id', '数据源', 'int(11)', 'Long', 'dsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (17, '2', 'xaxis', '横向坐标', 'varchar(50)', 'String', 'xaxis', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (18, '2', 'yaxis', '纵向坐标', 'varchar(50)', 'String', 'yaxis', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (19, '2', 'comment', '说明', 'varchar(200)', 'String', 'comment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (20, '2', 'list_sort', '排序', 'int(11)', 'Long', 'listSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (21, '2', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 10, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:54:57');
INSERT INTO `gen_table_column` VALUES (22, '3', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:25');
INSERT INTO `gen_table_column` VALUES (23, '3', 'type', '类型', 'char(10)', 'String', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (24, '3', 'code', '代码', 'varchar(50)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (25, '3', 'title', '名称', 'varchar(100)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (26, '3', 'sqls', '查询SQL', 'text', 'String', 'sqls', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (27, '3', 'param', '参数', 'varchar(255)', 'String', 'param', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (28, '3', 'list_sort', '排序', 'int(11)', 'Long', 'listSort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (29, '3', 'comment', '说明', 'varchar(200)', 'String', 'comment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (30, '3', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 9, 'admin', '2021-02-25 17:34:33', '', '2021-02-25 17:55:26');
INSERT INTO `gen_table_column` VALUES (31, '4', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (32, '4', 'title', '应用名称', 'varchar(10)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (33, '4', 'type', '类型', 'varchar(1)', 'String', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (34, '4', 'code', '应用代码', 'varchar(32)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (35, '4', 'secret', '应用密钥', 'varchar(32)', 'String', 'secret', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (36, '4', 'url', '媒体附件', 'varchar(255)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (37, '4', 'icon', '图标', 'varchar(255)', 'String', 'icon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (38, '4', 'db_type', '数据库类型', 'char(1)', 'String', 'dbType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 8, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (39, '4', 'jdbc', '数据连接', 'varchar(255)', 'String', 'jdbc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (40, '4', 'db_user', '数据库账号', 'varchar(50)', 'String', 'dbUser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (41, '4', 'db_pass', '数据库密码', 'varchar(50)', 'String', 'dbPass', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (42, '4', 'status', '状态', 'varchar(1)', 'String', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 12, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:31:56');
INSERT INTO `gen_table_column` VALUES (43, '5', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (44, '5', 'title', '模型名称', 'varchar(10)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (45, '5', 'type', '类型', 'varchar(1)', 'String', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (46, '5', 'tables', '数据表', 'varchar(100)', 'String', 'tables', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (47, '5', 'list_sort', '排序', 'int(11)', 'Long', 'listSort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (48, '5', 'status', '状态', 'varchar(1)', 'String', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:21');
INSERT INTO `gen_table_column` VALUES (49, '6', 'id', '自增长主键ID', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (50, '6', 'client_id', '应用', 'int(11)', 'Long', 'clientId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (51, '6', 'model_id', '模型名称', 'int(11)', 'Long', 'modelId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (52, '6', 'mode', '权限', 'varchar(1)', 'String', 'mode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (53, '6', 'read_sql', '读取操作', 'varchar(500)', 'String', 'readSql', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (54, '6', 'insert_sql', '新增操作', 'varchar(1000)', 'String', 'insertSql', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (55, '6', 'update_sql', '更新操作', 'varchar(1000)', 'String', 'updateSql', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (56, '6', 'del_sql', '删除操作', 'varchar(255)', 'String', 'delSql', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (57, '6', 'exist_sql', '查询操作', 'varchar(255)', 'String', 'existSql', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (58, '6', 'last_time', '上次同步时间', 'datetime', 'Date', 'lastTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (59, '6', 'last_result', '上次同步结果', 'int(11)', 'Long', 'lastResult', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (60, '6', 'list_sort', '优先级', 'int(11)', 'Long', 'listSort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');
INSERT INTO `gen_table_column` VALUES (61, '6', 'status', '状态', 'varchar(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 13, 'admin', '2021-02-25 17:34:43', '', '2021-02-25 18:32:46');

-- ----------------------------
-- Table structure for sys_chart
-- ----------------------------
DROP TABLE IF EXISTS `sys_chart`;
CREATE TABLE `sys_chart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `ds_id` int(11) NOT NULL COMMENT '数据源',
  `xaxis` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '横向坐标',
  `yaxis` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纵向坐标',
  `comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `list_sort` int(11) NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE COMMENT '代码不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_chart
-- ----------------------------
INSERT INTO `sys_chart` VALUES (1, 'line', 'student_grade', '按年级统计在校生', 2, 'grade=年级', 'total=学生数', NULL, 1, '1');
INSERT INTO `sys_chart` VALUES (2, 'bar', 'student_grade2', '年级学生柱状图', 2, 'year=年级', 'total=学生数据', '', 2, '1');
INSERT INTO `sys_chart` VALUES (3, 'pie', 'student_grade3', '年级学生组成', 2, 'grade=年级', 'total=学生数', NULL, 3, '1');
INSERT INTO `sys_chart` VALUES (4, 'table', 'student_result', '成绩查询', 3, NULL, NULL, 'name=姓名\ncourse=课程\nresult=成绩', 5, '1');
INSERT INTO `sys_chart` VALUES (5, 'pie', 'teacher_type', '教师分类组成', 8, 'title=教师类型', 'total=教师数', NULL, 2, '1');
INSERT INTO `sys_chart` VALUES (6, 'pie', 'student_specialty', '学生专业分布', 9, 'title=专业', 'total=学生数', NULL, 1, '1');
INSERT INTO `sys_chart` VALUES (7, 'table', 'scheduling_day', '今日全校课表', 10, NULL, NULL, 'sectionName=节次\nroomName=教室\ncourseName=课程\nname=上课教师\nclassName=班级\nspecName=专业', 4, '1');
INSERT INTO `sys_chart` VALUES (8, 'table', 'my_scheduling', '教师本人课表', 11, NULL, NULL, 'week=周次\nsectionName=节次\nroomName=教室\ncourseName=课程\nclassName=班级\nspecName=专业', 4, '1');
INSERT INTO `sys_chart` VALUES (9, 'gauge', 'enroll_signup', '招生注册率', 12, '', '', NULL, 9, '1');
INSERT INTO `sys_chart` VALUES (10, 'table', 'student_glory_query', '学生荣耀查询', 21, NULL, NULL, 'name=学生姓名\ncode=学号\ntitle=荣耀名称\nlevel_name=级别\ntype_name=类型\ndepart=颁奖单位\nget_time=获奖时间', 9, '1');
INSERT INTO `sys_chart` VALUES (11, 'table', 'student_scheduling', '学生课表', 22, NULL, NULL, 'week=周次\nsectionName=节次\nroomName=教室\ncourseName=课程\nname=教师', 9, '1');
INSERT INTO `sys_chart` VALUES (12, 'line', 'student_checkin', '招生报到统计', 23, 'year=年份', 'total=报到人数', NULL, 9, '1');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-02-25 16:38:41', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-02-25 16:38:41', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-02-25 16:38:41', '', NULL, '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dataset
-- ----------------------------
DROP TABLE IF EXISTS `sys_dataset`;
CREATE TABLE `sys_dataset`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `type` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '代码',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `sqls` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '查询SQL',
  `param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `list_sort` int(11) NOT NULL COMMENT '排序',
  `comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE COMMENT '代码不能重复'
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据集' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dataset
-- ----------------------------
INSERT INTO `sys_dataset` VALUES (1, '3', 'student_count', '在校学生数', 'SELECT count(id) FROM base_student where status=\'1\'', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (2, '2', 'student_grade', '学生年级统计', 'SELECT COUNT(id) total,year FROM base_student WHERE status=\'1\' GROUP BY year ORDER BY year ASC LIMIT 5', NULL, 2, NULL, '1');
INSERT INTO `sys_dataset` VALUES (3, '1', 'student_result', '成绩查询', 'select s.name,c.title course,r.total_result result, dd.dict_label level from exam_result r\njoin base_student s on r.student_id = s.id\njoin base_course c on c.id=r.course_id\njoin sys_dict_data dd on dd.dict_type=\'result_level\' and dd.dict_value=r.level', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (4, '3', 'unread_msg', '未读消息数', 'SELECT count(id) FROM sys_msg WHERE to_user=ME AND read_time IS NULL', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (5, '3', 'teacher_count', '教师总数', 'SELECT count(id) FROM base_teacher where status=\'1\'', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (6, '3', 'clazz_count', '开设班级数', 'SELECT count(id) FROM base_class where status=\'1\'', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (7, '3', 'apply_count', '招生数', 'SELECT count(id) FROM student_checkin WHERE year(create_time)=year(now())', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (8, '2', 'teacher_type', '教师分类统计', 'SELECT count(t.id) total, d.dict_label title FROM base_teacher t JOIN sys_dict_data d ON d.dict_value=t.type AND d.dict_type=\'teacher_type\' WHERE t.status=\'1\' GROUP BY d.dict_label', NULL, 2, NULL, '1');
INSERT INTO `sys_dataset` VALUES (9, '2', 'student_specialty', '学生专业分布', 'SELECT COUNT(s.id) total,ss.title FROM base_student s JOIN base_specialty ss ON s.spec_id=ss.id WHERE s.status=\'1\' GROUP BY ss.title', NULL, 1, NULL, '1');
INSERT INTO `sys_dataset` VALUES (10, '1', 'scheduling_day', '全校当日课表', 'SELECT sc.title sectionName,br.title roomName, bt.name,s.title specName,bc.title className, c.title courseName\nfrom education_scheduling es \nJOIN base_semster bs ON bs.id=es.sem_id AND bs.is_current=\'Y\'\nLEFT JOIN base_specialty s ON s.id=es.spec_id\nLEFT JOIN base_class bc ON bc.id=es.class_id\nJOIN base_teacher bt ON bt.user_id=es.teacher_id\nJOIN base_room br ON br.id=es.room_id\nJOIN base_section sc ON sc.id=es.section_id\nJOIN base_course c ON c.id=es.course_id\nWHERE es.week=dayofweek(now())-1', NULL, 4, NULL, '1');
INSERT INTO `sys_dataset` VALUES (11, '1', 'my_scheduling', '教师本人课表', 'SELECT sc.title sectionName,br.title roomName, bt.name,s.title specName,bc.title className, c.title courseName, dd.dict_label week\nfrom education_scheduling es \nJOIN base_semster bs ON bs.id=es.sem_id AND bs.is_current=\'Y\'\nLEFT JOIN base_specialty s ON s.id=es.spec_id\nLEFT JOIN base_class bc ON bc.id=es.class_id\nJOIN base_teacher bt ON bt.user_id=es.teacher_id\nJOIN base_room br ON br.id=es.room_id\nJOIN base_section sc ON sc.id=es.section_id\nJOIN base_course c ON c.id=es.course_id\nJOIN sys_dict_data dd ON dd.dict_type=\'common_week\' and dd.dict_value=es.week\nWHERE es.teacher_id=ME\nORDER BY es.week ASC', NULL, 4, NULL, '1');
INSERT INTO `sys_dataset` VALUES (12, '3', 'enroll_signup', '招生注册率', 'select sum(signups)/sum(total) * 100 rate from student_enroll where status=\'1\'', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (13, '1', 'student_glory', '学生个人荣耀', 'select gs.*,s.name from glory_student gs\njoin base_student s on s.id=gs.student_id\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (16, '1', 'my_result', '查询学生成绩', 'select s.name,c.title course,r.total_result result, dd.dict_label level from exam_result r\njoin base_student s on r.student_id = s.id\njoin base_course c on c.id=r.course_id\njoin sys_dict_data dd on dd.dict_type=\'result_level\' and dd.dict_value=r.level\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (17, '1', 'my_practice_check', '个人实习考勤', 'select pc.*,c.title company_name from practice_check pc\njoin base_company c on c.id=pc.company_id\njoin base_student s on s.id=pc.stu_id\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (18, '1', 'my_practice_summary', '个人实习总结', 'select ps.*,c.title company_name from practice_summary ps\njoin base_company c on c.id=ps.company_id\njoin base_student s on s.id=ps.stu_id\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (19, '1', 'my_pay', '个人缴费查询', 'select p.* from wechat_pay p\njoin base_student s on s.id=p.stu_id\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (20, '1', 'my_teach_check', '我的课堂考勤', 'select pc.*,c.title course_name, t.name teacher_name, st.title sem_name from teach_check pc\njoin base_course c on c.id=pc.course_id\njoin base_teacher t on t.id=pc.teacher_id\njoin base_semster st on st.id=pc.sem_id\njoin base_student s on s.id=pc.student_id\nWHERE s.user_id=ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (21, '1', 'student_glory_query', '学生荣耀查询', 'select gs.*,s.name,s.code, dl.dict_label level_name,dt.dict_label type_name \nfrom glory_student gs\njoin base_student s on s.id=gs.student_id\njoin sys_dict_data dl on dl.dict_value=gs.level and dl.dict_type=\'glory_level\'\njoin sys_dict_data dt on dt.dict_value=gs.type and dt.dict_type=\'glory_type\'', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (22, '1', 'student_scheduling', '学生课表', 'SELECT sc.title sectionName,br.title roomName, bt.name, c.title courseName, dd.dict_label week\nfrom education_scheduling es \nJOIN base_semster bs ON bs.id=es.sem_id AND bs.is_current=\'Y\'\nJOIN base_class bc ON bc.id=es.class_id\nJOIN base_student ss ON ss.class_id=bc.id\nJOIN base_teacher bt ON bt.user_id=es.teacher_id\nJOIN base_room br ON br.id=es.room_id\nJOIN base_section sc ON sc.id=es.section_id\nJOIN base_course c ON c.id=es.course_id\nJOIN sys_dict_data dd ON dd.dict_type=\'common_week\' and dd.dict_value=es.week\nWHERE ss.user_id = ME\nUNION \nSELECT sc.title sectionName,br.title roomName, bt.name, c.title courseName, dd.dict_label week\nfrom education_scheduling es \nJOIN base_semster bs ON bs.id=es.sem_id AND bs.is_current=\'Y\'\nJOIN education_student ess ON ess.task_id=es.task_id\nJOIN base_student ss ON ss.id=ess.student_id\nJOIN base_teacher bt ON bt.user_id=es.teacher_id\nJOIN base_room br ON br.id=es.room_id\nJOIN base_section sc ON sc.id=es.section_id\nJOIN base_course c ON c.id=es.course_id\nJOIN sys_dict_data dd ON dd.dict_type=\'common_week\' and dd.dict_value=es.week\nWHERE ss.user_id = ME', NULL, 9, NULL, '1');
INSERT INTO `sys_dataset` VALUES (23, '2', 'student_checkin', '学生招生报到统计', 'select count(id) total, year(create_time) year from student_checkin GROUP BY year(create_time) ORDER BY year(create_time)', NULL, 9, NULL, '1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 1, '系统消息', '0', 'ext_msg_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 17:37:35', 'admin', '2021-02-25 17:37:54', NULL);
INSERT INTO `sys_dict_data` VALUES (101, 1, '私人消息', '1', 'ext_msg_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 17:37:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (102, 1, 'web应用', '1', 'xdata_app_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:46:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 2, '移动应用', '2', 'xdata_app_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:46:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 3, '其它', '3', 'xdata_app_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:46:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 1, '基础数据', '1', 'xdata_model_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:47:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 2, '业务数据', '2', 'xdata_model_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:47:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 3, '归档数据', '3', 'xdata_model_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:47:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 4, '其它数据', '4', 'xdata_model_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:47:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 1, '读', 'r', 'xdata_pipe_mode', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:48:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 2, '写', 'w', 'xdata_pipe_mode', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:48:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 1, 'MySQL', '1', 'xdata_db_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:49:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 2, 'Oracle', '2', 'xdata_db_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:49:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (113, 3, 'SQL Server', '3', 'xdata_db_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:49:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 4, 'PostgreSQL', '4', 'xdata_db_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:49:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 9, '其它', '9', 'xdata_db_type', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:50:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (116, 1, '启用', '1', 'ext_status', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:53:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (117, 2, '禁用', '0', 'ext_status', NULL, NULL, 'N', '0', 'admin', '2021-02-25 18:54:24', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '消息类型', 'ext_msg_type', '0', 'admin', '2021-02-25 17:37:14', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (101, '应用类型', 'xdata_app_type', '0', 'admin', '2021-02-25 18:45:59', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (102, '模型分类', 'xdata_model_type', '0', 'admin', '2021-02-25 18:46:55', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (103, '管道模式', 'xdata_pipe_mode', '0', 'admin', '2021-02-25 18:48:14', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (104, '数据库类型', 'xdata_db_type', '0', 'admin', '2021-02-25 18:49:10', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (105, '通用状态', 'ext_status', '0', 'admin', '2021-02-25 18:53:16', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2021-02-25 16:38:41', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-02-25 17:14:38');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2042 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', '2021-02-25 16:38:41', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 4, 3, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:25:09', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '开发工具', 0, 4, 'tool', NULL, 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:53:35', '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '管理工具', 0, 4, 'ext', NULL, 1, 0, 'M', '0', '0', '', 'validCode', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:18:02', '若依官网地址');
INSERT INTO `sys_menu` VALUES (9, '查询统计', 0, 9, 'charts', NULL, 1, 0, 'M', '0', '0', '', 'chart', 'admin', '2021-02-25 17:19:34', 'admin', '2021-02-25 17:21:16', '');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:20:30', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:20:38', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:20:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:20:48', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:20:54', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2021-02-25 16:38:41', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2021-02-25 16:38:41', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2021-02-25 16:38:41', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2021-02-25 16:38:41', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2021-02-25 16:38:41', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2021-02-25 16:38:41', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2021-02-25 16:38:41', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2021-02-25 16:38:41', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2021-02-25 16:38:41', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2021-02-25 16:38:41', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2021-02-25 16:38:41', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-02-25 16:38:41', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-02-25 16:38:41', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '数据中心', 4, 1, 'xdata', NULL, 1, 0, 'M', '0', '0', NULL, 'build', 'admin', '2021-02-25 17:24:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2002, '数据报表', 4, 2, 'chart', NULL, 1, 0, 'M', '0', '0', NULL, 'chart', 'admin', '2021-02-25 17:24:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2003, '消息查询', 2, 1, 'msg', 'sys/msg/index', 1, 0, 'C', '0', '0', 'sys:msg:list', 'message', 'admin', '2021-02-25 18:31:07', 'admin', '2021-02-25 19:01:58', '消息菜单');
INSERT INTO `sys_menu` VALUES (2004, '消息查询', 2003, 1, '#', '', 1, 0, 'F', '0', '0', 'sys:msg:query', '#', 'admin', '2021-02-25 18:31:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '消息新增', 2003, 2, '#', '', 1, 0, 'F', '0', '0', 'sys:msg:add', '#', 'admin', '2021-02-25 18:31:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '消息修改', 2003, 3, '#', '', 1, 0, 'F', '0', '0', 'sys:msg:edit', '#', 'admin', '2021-02-25 18:31:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '消息删除', 2003, 4, '#', '', 1, 0, 'F', '0', '0', 'sys:msg:remove', '#', 'admin', '2021-02-25 18:31:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2008, '消息导出', 2003, 5, '#', '', 1, 0, 'F', '0', '0', 'sys:msg:export', '#', 'admin', '2021-02-25 18:31:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '应用管理', 2001, 1, 'client', 'xdata/client/index', 1, 0, 'C', '0', '0', 'xdata:client:list', 'server', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:50:41', '应用管理菜单');
INSERT INTO `sys_menu` VALUES (2010, '应用管理查询', 2009, 1, '#', '', 1, 0, 'F', '0', '0', 'xdata:client:query', '#', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:42:50', '');
INSERT INTO `sys_menu` VALUES (2011, '应用管理新增', 2009, 2, '#', '', 1, 0, 'F', '0', '0', 'xdata:client:add', '#', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:42:55', '');
INSERT INTO `sys_menu` VALUES (2012, '应用管理修改', 2009, 3, '#', '', 1, 0, 'F', '0', '0', 'xdata:client:edit', '#', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:43:04', '');
INSERT INTO `sys_menu` VALUES (2013, '应用管理删除', 2009, 4, '#', '', 1, 0, 'F', '0', '0', 'xdata:client:remove', '#', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:43:10', '');
INSERT INTO `sys_menu` VALUES (2014, '应用管理导出', 2009, 5, '#', '', 1, 0, 'F', '0', '0', 'xdata:client:export', '#', 'admin', '2021-02-25 18:33:19', 'admin', '2021-02-25 18:43:15', '');
INSERT INTO `sys_menu` VALUES (2015, '模型管理', 2001, 1, 'model', 'xdata/model/index', 1, 0, 'C', '0', '0', 'xdata:model:list', 'table', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:51:34', '模型管理菜单');
INSERT INTO `sys_menu` VALUES (2016, '模型管理查询', 2015, 1, '#', '', 1, 0, 'F', '0', '0', 'xdata:model:query', '#', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:43:25', '');
INSERT INTO `sys_menu` VALUES (2017, '模型管理新增', 2015, 2, '#', '', 1, 0, 'F', '0', '0', 'xdata:model:add', '#', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:43:30', '');
INSERT INTO `sys_menu` VALUES (2018, '模型管理修改', 2015, 3, '#', '', 1, 0, 'F', '0', '0', 'xdata:model:edit', '#', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:43:35', '');
INSERT INTO `sys_menu` VALUES (2019, '模型管理删除', 2015, 4, '#', '', 1, 0, 'F', '0', '0', 'xdata:model:remove', '#', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:43:41', '');
INSERT INTO `sys_menu` VALUES (2020, '模型管理导出', 2015, 5, '#', '', 1, 0, 'F', '0', '0', 'xdata:model:export', '#', 'admin', '2021-02-25 18:33:31', 'admin', '2021-02-25 18:43:47', '');
INSERT INTO `sys_menu` VALUES (2021, '通道管理', 2001, 1, 'pipe', 'xdata/pipe/index', 1, 0, 'C', '0', '0', 'xdata:pipe:list', 'swagger', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:51:26', '通道菜单');
INSERT INTO `sys_menu` VALUES (2022, '通道查询', 2021, 1, '#', '', 1, 0, 'F', '0', '0', 'xdata:pipe:query', '#', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:43:54', '');
INSERT INTO `sys_menu` VALUES (2023, '通道新增', 2021, 2, '#', '', 1, 0, 'F', '0', '0', 'xdata:pipe:add', '#', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:43:59', '');
INSERT INTO `sys_menu` VALUES (2024, '通道修改', 2021, 3, '#', '', 1, 0, 'F', '0', '0', 'xdata:pipe:edit', '#', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:44:04', '');
INSERT INTO `sys_menu` VALUES (2025, '通道删除', 2021, 4, '#', '', 1, 0, 'F', '0', '0', 'xdata:pipe:remove', '#', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:44:09', '');
INSERT INTO `sys_menu` VALUES (2026, '通道导出', 2021, 5, '#', '', 1, 0, 'F', '0', '0', 'xdata:pipe:export', '#', 'admin', '2021-02-25 18:33:42', 'admin', '2021-02-25 18:44:14', '');
INSERT INTO `sys_menu` VALUES (2027, '数据集', 2002, 1, 'dataset', 'sys/dataset/index', 1, 0, 'C', '0', '0', 'sys:dataset:list', 'tab', 'admin', '2021-02-25 18:33:52', 'admin', '2021-02-25 18:52:08', '数据集菜单');
INSERT INTO `sys_menu` VALUES (2028, '数据集查询', 2027, 1, '#', '', 1, 0, 'F', '0', '0', 'sys:dataset:query', '#', 'admin', '2021-02-25 18:33:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '数据集新增', 2027, 2, '#', '', 1, 0, 'F', '0', '0', 'sys:dataset:add', '#', 'admin', '2021-02-25 18:33:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '数据集修改', 2027, 3, '#', '', 1, 0, 'F', '0', '0', 'sys:dataset:edit', '#', 'admin', '2021-02-25 18:33:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '数据集删除', 2027, 4, '#', '', 1, 0, 'F', '0', '0', 'sys:dataset:remove', '#', 'admin', '2021-02-25 18:33:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '数据集导出', 2027, 5, '#', '', 1, 0, 'F', '0', '0', 'sys:dataset:export', '#', 'admin', '2021-02-25 18:33:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '报表设计', 2002, 1, 'chart', 'sys/chart/index', 1, 0, 'C', '0', '0', 'sys:chart:list', 'chart', 'admin', '2021-02-25 18:34:01', 'admin', '2021-02-25 18:52:22', '报表管理菜单');
INSERT INTO `sys_menu` VALUES (2034, '报表管理查询', 2033, 1, '#', '', 1, 0, 'F', '0', '0', 'sys:chart:query', '#', 'admin', '2021-02-25 18:34:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '报表管理新增', 2033, 2, '#', '', 1, 0, 'F', '0', '0', 'sys:chart:add', '#', 'admin', '2021-02-25 18:34:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '报表管理修改', 2033, 3, '#', '', 1, 0, 'F', '0', '0', 'sys:chart:edit', '#', 'admin', '2021-02-25 18:34:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2037, '报表管理删除', 2033, 4, '#', '', 1, 0, 'F', '0', '0', 'sys:chart:remove', '#', 'admin', '2021-02-25 18:34:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '报表管理导出', 2033, 5, '#', '', 1, 0, 'F', '0', '0', 'sys:chart:export', '#', 'admin', '2021-02-25 18:34:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2039, '个人中心', 0, 2, 'person', NULL, 1, 0, 'M', '0', '0', NULL, 'star', 'admin', '2021-02-25 19:19:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2040, '收件箱', 2039, 1, 'msg/in', 'sys/msg/in', 1, 0, 'C', '0', '0', 'sys:msg:in', 'message', 'admin', '2021-02-25 19:21:10', 'admin', '2021-02-25 19:21:55', '');
INSERT INTO `sys_menu` VALUES (2041, '发件箱', 2039, 2, 'msg/out', 'sys/msg/out', 1, 0, 'C', '0', '0', 'sys:msg:out', 'message', 'admin', '2021-02-25 19:21:47', 'admin', '2021-02-25 19:29:44', '');

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息类型',
  `add_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `add_user` int(11) NOT NULL DEFAULT 0 COMMENT '发信人',
  `add_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '发信人姓名',
  `to_user` int(11) NULL DEFAULT NULL COMMENT '收信人',
  `to_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收信人姓名',
  `read_time` datetime NULL DEFAULT NULL COMMENT '读取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_msg
-- ----------------------------
INSERT INTO `sys_msg` VALUES (1, 'fadf', 'adfadsf', 'adfadsf', '1', '2020-12-11 17:39:10', 1, '管理员', 1, '管理员', '2020-12-14 10:57:12');
INSERT INTO `sys_msg` VALUES (2, 'fadf', 'adfadsf', 'adfadsf', '1', '2020-12-11 17:39:10', 1, '管理员', 2, '若依', NULL);
INSERT INTO `sys_msg` VALUES (3, '回复 fadf', 'dsafa', 'asdfdasf', '1', '2020-12-11 17:46:48', 1, '管理员', 1, '管理员', '2020-12-14 10:57:12');
INSERT INTO `sys_msg` VALUES (4, '4rewrtre', 'rewtewrt', 'https://www.baidu.com/', '1', '2020-12-22 11:54:21', 4, '宋波', 1, '管理员', '2020-12-23 09:05:23');
INSERT INTO `sys_msg` VALUES (5, '回复 4rewrtre', 'afasfadsfdasf', NULL, '1', '2020-12-22 12:00:35', 1, '管理员', 4, '宋波', '2020-12-22 13:34:15');
INSERT INTO `sys_msg` VALUES (6, '34214', '234234324', NULL, '1', '2020-12-23 13:48:10', 3, '高老师', 1, '管理员', '2020-12-23 13:52:26');
INSERT INTO `sys_msg` VALUES (7, '回复 34214', '123123123', NULL, '1', '2020-12-23 13:48:24', 1, '管理员', 3, '高老师', '2020-12-23 13:48:35');
INSERT INTO `sys_msg` VALUES (8, '341234', '234234342', NULL, '1', NULL, 0, '', NULL, NULL, NULL);
INSERT INTO `sys_msg` VALUES (9, 'sadfasd', 'fasdfsadf', 'asdfsadf', '1', NULL, 0, '', NULL, NULL, NULL);
INSERT INTO `sys_msg` VALUES (10, 'asdf', 'adsfasdf', NULL, '1', NULL, 0, '', NULL, NULL, NULL);
INSERT INTO `sys_msg` VALUES (11, 'asfasd', 'fdasf', NULL, '1', '2021-02-25 19:51:59', 1, '若依', 1, '超级管理员', '2021-02-25 19:52:48');
INSERT INTO `sys_msg` VALUES (12, '回复 asfasd', 'asdfasdfsdaf', NULL, '1', '2021-02-25 19:52:51', 1, '若依', 1, '超级管理员', NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"validCode\",\"orderNum\":\"4\",\"menuName\":\"管理工具\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"ext\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":4,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:18:02');
INSERT INTO `sys_oper_log` VALUES (101, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"3\",\"menuName\":\"系统工具\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:18:29');
INSERT INTO `sys_oper_log` VALUES (102, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"monitor\",\"orderNum\":\"2\",\"menuName\":\"系统监控\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"monitor\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:18:38');
INSERT INTO `sys_oper_log` VALUES (103, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"qq\",\"orderNum\":\"1\",\"menuName\":\"用户权限\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"perms\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:19:34');
INSERT INTO `sys_oper_log` VALUES (104, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"user\",\"orderNum\":\"1\",\"menuName\":\"用户管理\",\"params\":{},\"parentId\":2000,\"isCache\":\"0\",\"path\":\"user\",\"component\":\"system/user/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":100,\"menuType\":\"C\",\"perms\":\"system:user:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:19:43');
INSERT INTO `sys_oper_log` VALUES (105, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"2\",\"menuName\":\"角色管理\",\"params\":{},\"parentId\":2000,\"isCache\":\"0\",\"path\":\"role\",\"component\":\"system/role/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":101,\"menuType\":\"C\",\"perms\":\"system:role:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:19:50');
INSERT INTO `sys_oper_log` VALUES (106, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tree-table\",\"orderNum\":\"3\",\"menuName\":\"菜单管理\",\"params\":{},\"parentId\":2000,\"isCache\":\"0\",\"path\":\"menu\",\"component\":\"system/menu/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":102,\"menuType\":\"C\",\"perms\":\"system:menu:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:19:58');
INSERT INTO `sys_oper_log` VALUES (107, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tree\",\"orderNum\":\"4\",\"menuName\":\"部门管理\",\"params\":{},\"parentId\":2000,\"isCache\":\"0\",\"path\":\"dept\",\"component\":\"system/dept/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":103,\"menuType\":\"C\",\"perms\":\"system:dept:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:05');
INSERT INTO `sys_oper_log` VALUES (108, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"5\",\"menuName\":\"岗位管理\",\"params\":{},\"parentId\":2000,\"isCache\":\"0\",\"path\":\"post\",\"component\":\"system/post/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":104,\"menuType\":\"C\",\"perms\":\"system:post:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:12');
INSERT INTO `sys_oper_log` VALUES (109, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"user\",\"orderNum\":\"1\",\"menuName\":\"用户管理\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"user\",\"component\":\"system/user/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":100,\"menuType\":\"C\",\"perms\":\"system:user:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:30');
INSERT INTO `sys_oper_log` VALUES (110, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"peoples\",\"orderNum\":\"2\",\"menuName\":\"角色管理\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"role\",\"component\":\"system/role/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":101,\"menuType\":\"C\",\"perms\":\"system:role:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:38');
INSERT INTO `sys_oper_log` VALUES (111, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tree-table\",\"orderNum\":\"3\",\"menuName\":\"菜单管理\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"menu\",\"component\":\"system/menu/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":102,\"menuType\":\"C\",\"perms\":\"system:menu:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:43');
INSERT INTO `sys_oper_log` VALUES (112, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tree\",\"orderNum\":\"4\",\"menuName\":\"部门管理\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"dept\",\"component\":\"system/dept/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":103,\"menuType\":\"C\",\"perms\":\"system:dept:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:48');
INSERT INTO `sys_oper_log` VALUES (113, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"post\",\"orderNum\":\"5\",\"menuName\":\"岗位管理\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"post\",\"component\":\"system/post/index\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":104,\"menuType\":\"C\",\"perms\":\"system:post:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:20:54');
INSERT INTO `sys_oper_log` VALUES (114, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"9\",\"menuName\":\"查询统计\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"charts\",\"children\":[],\"createTime\":1614244774000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2000,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:21:16');
INSERT INTO `sys_oper_log` VALUES (115, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"3\",\"menuName\":\"开发工具\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:23:05');
INSERT INTO `sys_oper_log` VALUES (116, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"build\",\"orderNum\":\"1\",\"menuName\":\"数据中心\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"xdata\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:24:17');
INSERT INTO `sys_oper_log` VALUES (117, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"2\",\"menuName\":\"数据报表\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"chart\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:24:55');
INSERT INTO `sys_oper_log` VALUES (118, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"monitor\",\"orderNum\":\"3\",\"menuName\":\"系统监控\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"monitor\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:25:09');
INSERT INTO `sys_oper_log` VALUES (119, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"3\",\"menuName\":\"开发工具\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:25:13');
INSERT INTO `sys_oper_log` VALUES (120, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"4\",\"menuName\":\"开发工具\",\"params\":{},\"parentId\":4,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:25:19');
INSERT INTO `sys_oper_log` VALUES (121, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_msg', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:34:22');
INSERT INTO `sys_oper_log` VALUES (122, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_chart,sys_dataset', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:34:33');
INSERT INTO `sys_oper_log` VALUES (123, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'xdata_client,xdata_model,xdata_pipe', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:34:43');
INSERT INTO `sys_oper_log` VALUES (124, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"editor\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:36:21');
INSERT INTO `sys_oper_log` VALUES (125, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"消息类型\",\"params\":{},\"dictType\":\"ext_msg_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:37:14');
INSERT INTO `sys_oper_log` VALUES (126, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":1,\"params\":{},\"dictType\":\"ext_msg_type\",\"dictLabel\":\"通知公告\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:37:35');
INSERT INTO `sys_oper_log` VALUES (127, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"params\":{},\"dictType\":\"ext_msg_type\",\"dictLabel\":\"私人消息\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:37:45');
INSERT INTO `sys_oper_log` VALUES (128, '字典数据', 2, 'com.ruoyi.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":1,\"params\":{},\"dictType\":\"ext_msg_type\",\"dictLabel\":\"系统消息\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1614245855000,\"dictCode\":100,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:37:54');
INSERT INTO `sys_oper_log` VALUES (129, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614245781000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614245781000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614245781000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614245781000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:38:53');
INSERT INTO `sys_oper_log` VALUES (130, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614245933000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614245933000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614245933000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614245933000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:39:03');
INSERT INTO `sys_oper_log` VALUES (131, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614245943000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614245943000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614245943000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614245943000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:48:13');
INSERT INTO `sys_oper_log` VALUES (132, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614246493000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614246493000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614246493000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614246493000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:48:29');
INSERT INTO `sys_oper_log` VALUES (133, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614246509000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614246509000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614246509000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614246509000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:49:58');
INSERT INTO `sys_oper_log` VALUES (134, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2021-02-25 17:50:25');
INSERT INTO `sys_oper_log` VALUES (135, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1614242321000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[2],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"1\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1614242321000,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:50:41');
INSERT INTO `sys_oper_log` VALUES (136, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614246598000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614246598000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614246598000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614246598000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:52:08');
INSERT INTO `sys_oper_log` VALUES (137, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"4\",\"menuName\":\"开发工具\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1614242321000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:53:35');
INSERT INTO `sys_oper_log` VALUES (138, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":1,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"updateTime\":1614246728000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245662000,\"tableId\":1,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":2,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"标题\",\"isQuery\":\"1\",\"updateTime\":1614246728000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Content\",\"usableColumn\":false,\"columnId\":3,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"content\",\"htmlType\":\"textarea\",\"edit\":true,\"query\":true,\"columnComment\":\"内容\",\"isQuery\":\"1\",\"updateTime\":1614246728000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245662000,\"isEdit\":\"1\",\"tableId\":1,\"pk\":false,\"columnName\":\"content\"},{\"capJavaField\":\"Url\",\"usableColumn\":false,\"columnId\":4,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"url\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"链接\",\"isQuery\":\"1\",\"updateTime\":1614246728000,\"sort\":4,\"list', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:53:53');
INSERT INTO `sys_oper_log` VALUES (139, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":12,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245673000,\"tableId\":2,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":13,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"类型\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245673000,\"isEdit\":\"1\",\"tableId\":2,\"pk\":false,\"columnName\":\"type\"},{\"capJavaField\":\"Code\",\"usableColumn\":false,\"columnId\":14,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"code\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"代码\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245673000,\"isEdit\":\"1\",\"tableId\":2,\"pk\":false,\"columnName\":\"code\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":15,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"名称\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admi', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:54:57');
INSERT INTO `sys_oper_log` VALUES (140, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":22,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245673000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":23,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"类型\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"char(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245673000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"type\"},{\"capJavaField\":\"Code\",\"usableColumn\":false,\"columnId\":24,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"code\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"代码\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245673000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"code\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":25,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"名称\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"crea', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 17:55:26');
INSERT INTO `sys_oper_log` VALUES (141, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":31,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245683000,\"tableId\":4,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":32,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"应用名称\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":33,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"类型\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"type\"},{\"capJavaField\":\"Code\",\"usableColumn\":false,\"columnId\":34,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"code\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"应用代码\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(32)\",\"createBy\":\"a', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:31:56');
INSERT INTO `sys_oper_log` VALUES (142, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":43,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245683000,\"tableId\":5,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Title\",\"usableColumn\":false,\"columnId\":44,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"title\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"模型名称\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"title\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":45,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"columnComment\":\"类型\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"type\"},{\"capJavaField\":\"Tables\",\"usableColumn\":false,\"columnId\":46,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"tables\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"数据表\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(100)\",\"createBy', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:32:21');
INSERT INTO `sys_oper_log` VALUES (143, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"yepanpan\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":49,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"自增长主键ID\",\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1614245683000,\"tableId\":6,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"ClientId\",\"usableColumn\":false,\"columnId\":50,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"clientId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"应用\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"client_id\"},{\"capJavaField\":\"ModelId\",\"usableColumn\":false,\"columnId\":51,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"modelId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"模型名称\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1614245683000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"model_id\"},{\"capJavaField\":\"Mode\",\"usableColumn\":false,\"columnId\":52,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"mode\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"权限\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(1)\",\"createB', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:32:46');
INSERT INTO `sys_oper_log` VALUES (144, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2021-02-25 18:32:53');
INSERT INTO `sys_oper_log` VALUES (145, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"应用管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"client\",\"component\":\"xdata/client/index\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2009,\"menuType\":\"C\",\"perms\":\"xdata:client:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:42:25');
INSERT INTO `sys_oper_log` VALUES (146, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"模型管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"model\",\"component\":\"xdata/model/index\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2015,\"menuType\":\"C\",\"perms\":\"xdata:model:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:42:33');
INSERT INTO `sys_oper_log` VALUES (147, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"通道\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"pipe\",\"component\":\"xdata/pipe/index\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2021,\"menuType\":\"C\",\"perms\":\"xdata:pipe:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:42:41');
INSERT INTO `sys_oper_log` VALUES (148, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"应用管理查询\",\"params\":{},\"parentId\":2009,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2010,\"menuType\":\"F\",\"perms\":\"xdata:client:query\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:42:51');
INSERT INTO `sys_oper_log` VALUES (149, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"应用管理新增\",\"params\":{},\"parentId\":2009,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2011,\"menuType\":\"F\",\"perms\":\"xdata:client:add\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:42:55');
INSERT INTO `sys_oper_log` VALUES (150, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"应用管理修改\",\"params\":{},\"parentId\":2009,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2012,\"menuType\":\"F\",\"perms\":\"xdata:client:edit\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:04');
INSERT INTO `sys_oper_log` VALUES (151, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"应用管理删除\",\"params\":{},\"parentId\":2009,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2013,\"menuType\":\"F\",\"perms\":\"xdata:client:remove\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:10');
INSERT INTO `sys_oper_log` VALUES (152, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"应用管理导出\",\"params\":{},\"parentId\":2009,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2014,\"menuType\":\"F\",\"perms\":\"xdata:client:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:15');
INSERT INTO `sys_oper_log` VALUES (153, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"模型管理查询\",\"params\":{},\"parentId\":2015,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2016,\"menuType\":\"F\",\"perms\":\"xdata:model:query\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:25');
INSERT INTO `sys_oper_log` VALUES (154, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"模型管理新增\",\"params\":{},\"parentId\":2015,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2017,\"menuType\":\"F\",\"perms\":\"xdata:model:add\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:30');
INSERT INTO `sys_oper_log` VALUES (155, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"模型管理修改\",\"params\":{},\"parentId\":2015,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2018,\"menuType\":\"F\",\"perms\":\"xdata:model:edit\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:36');
INSERT INTO `sys_oper_log` VALUES (156, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"模型管理删除\",\"params\":{},\"parentId\":2015,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2019,\"menuType\":\"F\",\"perms\":\"xdata:model:remove\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:41');
INSERT INTO `sys_oper_log` VALUES (157, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"模型管理导出\",\"params\":{},\"parentId\":2015,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2020,\"menuType\":\"F\",\"perms\":\"xdata:model:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:47');
INSERT INTO `sys_oper_log` VALUES (158, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"通道查询\",\"params\":{},\"parentId\":2021,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2022,\"menuType\":\"F\",\"perms\":\"xdata:pipe:query\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:54');
INSERT INTO `sys_oper_log` VALUES (159, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"通道新增\",\"params\":{},\"parentId\":2021,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2023,\"menuType\":\"F\",\"perms\":\"xdata:pipe:add\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:43:59');
INSERT INTO `sys_oper_log` VALUES (160, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"通道修改\",\"params\":{},\"parentId\":2021,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2024,\"menuType\":\"F\",\"perms\":\"xdata:pipe:edit\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:44:04');
INSERT INTO `sys_oper_log` VALUES (161, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"通道删除\",\"params\":{},\"parentId\":2021,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2025,\"menuType\":\"F\",\"perms\":\"xdata:pipe:remove\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:44:09');
INSERT INTO `sys_oper_log` VALUES (162, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"通道导出\",\"params\":{},\"parentId\":2021,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2026,\"menuType\":\"F\",\"perms\":\"xdata:pipe:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:44:14');
INSERT INTO `sys_oper_log` VALUES (163, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"应用类型\",\"params\":{},\"dictType\":\"xdata_app_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:45:59');
INSERT INTO `sys_oper_log` VALUES (164, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"params\":{},\"dictType\":\"xdata_app_type\",\"dictLabel\":\"web应用\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:46:15');
INSERT INTO `sys_oper_log` VALUES (165, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":2,\"params\":{},\"dictType\":\"xdata_app_type\",\"dictLabel\":\"移动应用\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:46:28');
INSERT INTO `sys_oper_log` VALUES (166, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":3,\"params\":{},\"dictType\":\"xdata_app_type\",\"dictLabel\":\"其它\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:46:38');
INSERT INTO `sys_oper_log` VALUES (167, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"模型分类\",\"params\":{},\"dictType\":\"xdata_model_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:46:55');
INSERT INTO `sys_oper_log` VALUES (168, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"params\":{},\"dictType\":\"xdata_model_type\",\"dictLabel\":\"基础数据\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:47:07');
INSERT INTO `sys_oper_log` VALUES (169, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":2,\"params\":{},\"dictType\":\"xdata_model_type\",\"dictLabel\":\"业务数据\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:47:17');
INSERT INTO `sys_oper_log` VALUES (170, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":3,\"params\":{},\"dictType\":\"xdata_model_type\",\"dictLabel\":\"归档数据\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:47:27');
INSERT INTO `sys_oper_log` VALUES (171, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"4\",\"dictSort\":4,\"params\":{},\"dictType\":\"xdata_model_type\",\"dictLabel\":\"其它数据\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:47:36');
INSERT INTO `sys_oper_log` VALUES (172, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"管道模式\",\"params\":{},\"dictType\":\"xdata_pipe_mode\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:48:14');
INSERT INTO `sys_oper_log` VALUES (173, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"r\",\"dictSort\":1,\"params\":{},\"dictType\":\"xdata_pipe_mode\",\"dictLabel\":\"读\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:48:35');
INSERT INTO `sys_oper_log` VALUES (174, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"w\",\"dictSort\":2,\"params\":{},\"dictType\":\"xdata_pipe_mode\",\"dictLabel\":\"写\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:48:42');
INSERT INTO `sys_oper_log` VALUES (175, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"数据库类型\",\"params\":{},\"dictType\":\"xdata_db_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:49:10');
INSERT INTO `sys_oper_log` VALUES (176, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"params\":{},\"dictType\":\"xdata_db_type\",\"dictLabel\":\"MySQL\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:49:23');
INSERT INTO `sys_oper_log` VALUES (177, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":2,\"params\":{},\"dictType\":\"xdata_db_type\",\"dictLabel\":\"Oracle\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:49:29');
INSERT INTO `sys_oper_log` VALUES (178, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":3,\"params\":{},\"dictType\":\"xdata_db_type\",\"dictLabel\":\"SQL Server\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:49:38');
INSERT INTO `sys_oper_log` VALUES (179, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"4\",\"dictSort\":4,\"params\":{},\"dictType\":\"xdata_db_type\",\"dictLabel\":\"PostgreSQL\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:49:51');
INSERT INTO `sys_oper_log` VALUES (180, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"9\",\"dictSort\":9,\"params\":{},\"dictType\":\"xdata_db_type\",\"dictLabel\":\"其它\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:50:04');
INSERT INTO `sys_oper_log` VALUES (181, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"server\",\"orderNum\":\"1\",\"menuName\":\"应用管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"client\",\"component\":\"xdata/client/index\",\"children\":[],\"createTime\":1614249199000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2009,\"menuType\":\"C\",\"perms\":\"xdata:client:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:50:41');
INSERT INTO `sys_oper_log` VALUES (182, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"build\",\"orderNum\":\"1\",\"menuName\":\"模型管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"model\",\"component\":\"xdata/model/index\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2015,\"menuType\":\"C\",\"perms\":\"xdata:model:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:50:56');
INSERT INTO `sys_oper_log` VALUES (183, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"swagger\",\"orderNum\":\"1\",\"menuName\":\"通道管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"pipe\",\"component\":\"xdata/pipe/index\",\"children\":[],\"createTime\":1614249222000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2021,\"menuType\":\"C\",\"perms\":\"xdata:pipe:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:51:26');
INSERT INTO `sys_oper_log` VALUES (184, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"table\",\"orderNum\":\"1\",\"menuName\":\"模型管理\",\"params\":{},\"parentId\":2001,\"isCache\":\"0\",\"path\":\"model\",\"component\":\"xdata/model/index\",\"children\":[],\"createTime\":1614249211000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2015,\"menuType\":\"C\",\"perms\":\"xdata:model:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:51:34');
INSERT INTO `sys_oper_log` VALUES (185, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"tab\",\"orderNum\":\"1\",\"menuName\":\"数据集\",\"params\":{},\"parentId\":2002,\"isCache\":\"0\",\"path\":\"dataset\",\"component\":\"sys/dataset/index\",\"children\":[],\"createTime\":1614249232000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2027,\"menuType\":\"C\",\"perms\":\"sys:dataset:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:52:08');
INSERT INTO `sys_oper_log` VALUES (186, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"chart\",\"orderNum\":\"1\",\"menuName\":\"报表设计\",\"params\":{},\"parentId\":2002,\"isCache\":\"0\",\"path\":\"chart\",\"component\":\"sys/chart/index\",\"children\":[],\"createTime\":1614249241000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2033,\"menuType\":\"C\",\"perms\":\"sys:chart:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:52:22');
INSERT INTO `sys_oper_log` VALUES (187, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"通用状态\",\"params\":{},\"dictType\":\"ext_status\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:53:16');
INSERT INTO `sys_oper_log` VALUES (188, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":1,\"params\":{},\"dictType\":\"ext_status\",\"dictLabel\":\"启用\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:53:31');
INSERT INTO `sys_oper_log` VALUES (189, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":2,\"params\":{},\"dictType\":\"ext_status\",\"dictLabel\":\"禁用\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 18:54:24');
INSERT INTO `sys_oper_log` VALUES (190, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"1\",\"menuName\":\"消息\",\"params\":{},\"parentId\":2,\"isCache\":\"0\",\"path\":\"msg\",\"component\":\"sys/msg/index\",\"children\":[],\"createTime\":1614249067000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2003,\"menuType\":\"C\",\"perms\":\"sys:msg:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:01:33');
INSERT INTO `sys_oper_log` VALUES (191, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"1\",\"menuName\":\"消息查询\",\"params\":{},\"parentId\":2,\"isCache\":\"0\",\"path\":\"msg\",\"component\":\"sys/msg/index\",\"children\":[],\"createTime\":1614249067000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2003,\"menuType\":\"C\",\"perms\":\"sys:msg:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:01:58');
INSERT INTO `sys_oper_log` VALUES (192, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"star\",\"orderNum\":\"2\",\"menuName\":\"个人中心\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"person\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:19:56');
INSERT INTO `sys_oper_log` VALUES (193, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"1\",\"menuName\":\"收件箱\",\"params\":{},\"parentId\":2039,\"isCache\":\"0\",\"path\":\"msg/in\",\"component\":\"sys/msg/in\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"msg:in\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:21:10');
INSERT INTO `sys_oper_log` VALUES (194, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"2\",\"menuName\":\"发件箱\",\"params\":{},\"parentId\":2039,\"isCache\":\"0\",\"path\":\"msg/out\",\"component\":\"sys/msg/in\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"C\",\"perms\":\"sys:msg:out\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:21:47');
INSERT INTO `sys_oper_log` VALUES (195, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"1\",\"menuName\":\"收件箱\",\"params\":{},\"parentId\":2039,\"isCache\":\"0\",\"path\":\"msg/in\",\"component\":\"sys/msg/in\",\"children\":[],\"createTime\":1614252070000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2040,\"menuType\":\"C\",\"perms\":\"sys:msg:in\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:21:55');
INSERT INTO `sys_oper_log` VALUES (196, '数据集', 2, 'com.ruoyi.sys.controller.SysDatasetController.edit()', 'PUT', 1, 'admin', NULL, '/sys/dataset', '127.0.0.1', '内网IP', '{\"code\":\"unread_msg\",\"listSort\":1,\"params\":{},\"title\":\"未读消息数\",\"type\":\"3\",\"sqls\":\"SELECT count(id) FROM sys_msg WHERE to_user=ME AND read_time IS NULL\",\"id\":4,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:22:29');
INSERT INTO `sys_oper_log` VALUES (197, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"message\",\"orderNum\":\"2\",\"menuName\":\"发件箱\",\"params\":{},\"parentId\":2039,\"isCache\":\"0\",\"path\":\"msg/out\",\"component\":\"sys/msg/out\",\"children\":[],\"createTime\":1614252107000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2041,\"menuType\":\"C\",\"perms\":\"sys:msg:out\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:29:44');
INSERT INTO `sys_oper_log` VALUES (198, '消息', 1, 'com.ruoyi.sys.controller.SysMsgController.add()', 'POST', 1, 'admin', NULL, '/sys/msg', '127.0.0.1', '内网IP', '{\"params\":{},\"title\":\"341234\",\"type\":\"1\",\"content\":\"234234342\",\"id\":8}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:43:05');
INSERT INTO `sys_oper_log` VALUES (199, '消息', 1, 'com.ruoyi.sys.controller.SysMsgController.add()', 'POST', 1, 'admin', NULL, '/sys/msg', '127.0.0.1', '内网IP', '{\"params\":{},\"title\":\"sadfasd\",\"type\":\"1\",\"content\":\"fasdfsadf\",\"url\":\"asdfsadf\",\"users\":[1],\"id\":9}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:48:52');
INSERT INTO `sys_oper_log` VALUES (200, '消息', 1, 'com.ruoyi.sys.controller.SysMsgController.add()', 'POST', 1, 'admin', NULL, '/sys/msg', '127.0.0.1', '内网IP', '{\"params\":{},\"title\":\"asdf\",\"type\":\"1\",\"content\":\"adsfasdf\",\"users\":[1],\"id\":10}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:49:59');
INSERT INTO `sys_oper_log` VALUES (201, '消息', 1, 'com.ruoyi.sys.controller.SysMsgController.add()', 'POST', 1, 'admin', NULL, '/sys/msg', '127.0.0.1', '内网IP', '{\"addTime\":1614253919412,\"addUser\":1,\"params\":{},\"title\":\"asfasd\",\"type\":\"1\",\"content\":\"fdasf\",\"users\":[1],\"addUserName\":\"若依\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:51:59');
INSERT INTO `sys_oper_log` VALUES (202, '消息', 1, 'com.ruoyi.sys.controller.SysMsgController.add()', 'POST', 1, 'admin', NULL, '/sys/msg', '127.0.0.1', '内网IP', '{\"addTime\":1614253970699,\"addUser\":1,\"params\":{},\"title\":\"回复 asfasd\",\"type\":\"1\",\"content\":\"asdfasdfsdaf\",\"users\":[1],\"addUserName\":\"若依\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 19:52:50');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-02-25 16:38:41', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2021-02-25 16:38:41', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '超级管理员', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2021-02-25 16:38:41', 'admin', '2021-02-25 16:38:41', '', NULL, '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1', '0', '127.0.0.1', '2021-02-25 16:38:41', 'admin', '2021-02-25 16:38:41', 'admin', '2021-02-25 17:50:41', '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for xdata_client
-- ----------------------------
DROP TABLE IF EXISTS `xdata_client`;
CREATE TABLE `xdata_client`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用代码',
  `secret` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用密钥',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '媒体附件',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `db_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `jdbc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据连接',
  `db_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库账号',
  `db_pass` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库密码',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdata_client
-- ----------------------------
INSERT INTO `xdata_client` VALUES (1, '数据备份', '1', 'db2f4bf76f21bf9f44bed10ed30a8aba', '9c0e66ab26e561c4ceff9649a8fcd19e', NULL, NULL, '1', 'jdbc:mysql://localhost:3306/java_szxy_data?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8', 'root', 'root', '1');
INSERT INTO `xdata_client` VALUES (2, '教务系统', '1', '12695b8513de059116db8aebf506af6f', '90ca372d19ea42ff8bfeb1d46c5ca7db', NULL, NULL, '1', 'jdbc:mysql://localhost:3306/java_szxy_data?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8', 'root', 'root', '1');

-- ----------------------------
-- Table structure for xdata_model
-- ----------------------------
DROP TABLE IF EXISTS `xdata_model`;
CREATE TABLE `xdata_model`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型名称',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `tables` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据表',
  `list_sort` int(11) NULL DEFAULT 9 COMMENT '排序',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdata_model
-- ----------------------------
INSERT INTO `xdata_model` VALUES (1, '专业信息', '1', 'base_specialty', 1, '1');

-- ----------------------------
-- Table structure for xdata_pipe
-- ----------------------------
DROP TABLE IF EXISTS `xdata_pipe`;
CREATE TABLE `xdata_pipe`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键ID',
  `client_id` int(11) NOT NULL COMMENT '应用',
  `model_id` int(11) NOT NULL COMMENT '模型名称',
  `mode` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `read_sql` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '读取操作',
  `insert_sql` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '新增操作',
  `update_sql` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新操作',
  `del_sql` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除操作',
  `exist_sql` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '查询操作',
  `last_time` datetime NULL DEFAULT NULL COMMENT '上次同步时间',
  `last_result` int(11) NULL DEFAULT NULL COMMENT '上次同步结果',
  `list_sort` int(11) NULL DEFAULT 9 COMMENT '优先级',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdata_pipe
-- ----------------------------
INSERT INTO `xdata_pipe` VALUES (1, 1, 1, 'r', 'SELECT id, dept_id, code, title, period, start_date, end_date, master_user, list_sort, status, is_enroll, create_by, create_time, update_by, update_time FROM base_specialty limit #{_page} , #{_rows};', 'INSERT INTO base_specialty( id, dept_id, code, title, period, start_date, end_date, master_user, list_sort, status, is_enroll, create_by, create_time, update_by, update_time ) VALUES ( NULL, #{dept_id}, #{code}, #{title}, #{period}, #{start_date}, #{end_date}, #{master_user}, #{list_sort}, #{status}, #{is_enroll}, #{create_by}, #{create_time}, #{update_by}, #{update_time} );', 'UPDATE base_specialty SET dept_id =  #{dept_id}, code =  #{code}, title =  #{title}, period =  #{period}, start_date =  #{start_date}, end_date =  #{end_date}, master_user =  #{master_user}, list_sort =  #{list_sort}, status =  #{status}, is_enroll =  #{is_enroll}, create_by =  #{create_by}, create_time =  #{create_time}, update_by =  #{update_by}, update_time =  #{update_time} WHERE id = #{id};', NULL, 'SELECT * FROM base_specialty WHERE id = #{id} LIMIT 1', '2020-12-31 10:52:24', 3, 1, '1');
INSERT INTO `xdata_pipe` VALUES (2, 2, 1, 'w', 'SELECT id, dept_id, code, title, period, start_date, end_date, master_user, list_sort, status, is_enroll, create_by, create_time, update_by, update_time FROM base_specialty limit #{_page} , #{_rows};', 'INSERT INTO base_specialty( id, dept_id, code, title, period, start_date, end_date, master_user, list_sort, status, is_enroll, create_by, create_time, update_by, update_time ) VALUES ( NULL, #{dept_id}, #{code}, #{title}, #{period}, #{start_date}, #{end_date}, #{master_user}, #{list_sort}, #{status}, #{is_enroll}, #{create_by}, #{create_time}, #{update_by}, #{update_time} );', 'UPDATE base_specialty SET dept_id =  #{dept_id}, code =  #{code}, title =  #{title}, period =  #{period}, start_date =  #{start_date}, end_date =  #{end_date}, master_user =  #{master_user}, list_sort =  #{list_sort}, status =  #{status}, is_enroll =  #{is_enroll}, create_by =  #{create_by}, create_time =  #{create_time}, update_by =  #{update_by}, update_time =  #{update_time} WHERE code = #{code};', NULL, 'SELECT * FROM base_specialty WHERE code = #{code}  LIMIT 1', '2020-12-31 09:05:46', 3, 1, '0');

SET FOREIGN_KEY_CHECKS = 1;
