/*
二期追加+核心7表  基础表+必要初始化数据
sys_dic	后台字典表*
sys_menu	后台菜单表*
sys_role	后台角色表*
sys_user	后台用户表*
ref_role_menu	后台角色-菜单表*
ref_user_role	后台用户-角色表*
ref_role_permission	后台角色-特殊权限表*
--
Source Server         : wangjun
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : cms
Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001
Date: 2017-03-27 15:04:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `REF_ROLE_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `REF_ROLE_MENU`;
CREATE TABLE `REF_ROLE_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ref_role_menu
-- ----------------------------
INSERT INTO `REF_ROLE_MENU` VALUES ('1', '1', '2');
INSERT INTO `REF_ROLE_MENU` VALUES ('2', '1', '3');
INSERT INTO `REF_ROLE_MENU` VALUES ('3', '1', '4');
INSERT INTO `REF_ROLE_MENU` VALUES ('4', '1', '5');
INSERT INTO `REF_ROLE_MENU` VALUES ('5', '1', '6');
INSERT INTO `REF_ROLE_MENU` VALUES ('6', '1', '7');
INSERT INTO `REF_ROLE_MENU` VALUES ('7', '1', '8');
INSERT INTO `REF_ROLE_MENU` VALUES ('8', '1', '9');
INSERT INTO `REF_ROLE_MENU` VALUES ('9', '1', '1121');
INSERT INTO `REF_ROLE_MENU` VALUES ('10', '1', '1122');
INSERT INTO `REF_ROLE_MENU` VALUES ('11', '1', '1123');
INSERT INTO `REF_ROLE_MENU` VALUES ('12', '1', '1124');
INSERT INTO `REF_ROLE_MENU` VALUES ('13', '1', '1125');
INSERT INTO `REF_ROLE_MENU` VALUES ('14', '1', '1126');
INSERT INTO `REF_ROLE_MENU` VALUES ('15', '1', '1127');
INSERT INTO `REF_ROLE_MENU` VALUES ('16', '1', '1128');
INSERT INTO `REF_ROLE_MENU` VALUES ('17', '1', '1129');
INSERT INTO `REF_ROLE_MENU` VALUES ('18', '1', '1130');
INSERT INTO `REF_ROLE_MENU` VALUES ('19', '17', '8');
INSERT INTO `REF_ROLE_MENU` VALUES ('20', '17', '9');
INSERT INTO `REF_ROLE_MENU` VALUES ('21', '17', '1121');
INSERT INTO `REF_ROLE_MENU` VALUES ('22', '17', '1122');
INSERT INTO `REF_ROLE_MENU` VALUES ('23', '17', '1123');
INSERT INTO `REF_ROLE_MENU` VALUES ('24', '17', '1124');
INSERT INTO `REF_ROLE_MENU` VALUES ('25', '17', '1125');
INSERT INTO `REF_ROLE_MENU` VALUES ('26', '17', '1130');
INSERT INTO `REF_ROLE_MENU` VALUES ('27', '17', '1126');
INSERT INTO `REF_ROLE_MENU` VALUES ('28', '17', '1127');
INSERT INTO `REF_ROLE_MENU` VALUES ('29', '17', '1128');
INSERT INTO `REF_ROLE_MENU` VALUES ('30', '17', '1129');
INSERT INTO `REF_ROLE_MENU` VALUES ('31', '1', '1131');
INSERT INTO `REF_ROLE_MENU` VALUES ('32', '1', '1132');
INSERT INTO `REF_ROLE_MENU` VALUES ('33', '1', '1133');
INSERT INTO `REF_ROLE_MENU` VALUES ('34', '1', '1134');
INSERT INTO `REF_ROLE_MENU` VALUES ('35', '1', '10');
INSERT INTO `REF_ROLE_MENU` VALUES ('36', '1', '13');
INSERT INTO `REF_ROLE_MENU` VALUES ('37', '1', '12');
INSERT INTO `REF_ROLE_MENU` VALUES ('38', '1', '11');
INSERT INTO `REF_ROLE_MENU` VALUES ('39', '1', '1136');
INSERT INTO `REF_ROLE_MENU` VALUES ('40', '1', '1135');
INSERT INTO `REF_ROLE_MENU` VALUES ('41', '1', '1137');
INSERT INTO `REF_ROLE_MENU` VALUES ('42', '1', '1138');
INSERT INTO `REF_ROLE_MENU` VALUES ('43', '1', '1139');
INSERT INTO `REF_ROLE_MENU` VALUES ('44', '1', '1140');
INSERT INTO `REF_ROLE_MENU` VALUES ('48', '1', '1144');
INSERT INTO `REF_ROLE_MENU` VALUES ('49', '1', '1145');
INSERT INTO `REF_ROLE_MENU` VALUES ('50', '1', '1146');
INSERT INTO `REF_ROLE_MENU` VALUES ('51', '1', '1147');
INSERT INTO `REF_ROLE_MENU` VALUES ('52', '1', '1148');
INSERT INTO `REF_ROLE_MENU` VALUES ('53', '1', '1149');
INSERT INTO `REF_ROLE_MENU` VALUES ('54', '1', '1150');
INSERT INTO `REF_ROLE_MENU` VALUES ('55', '1', '1151');
INSERT INTO `REF_ROLE_MENU` VALUES ('56', '1', '1152');
INSERT INTO `REF_ROLE_MENU` VALUES ('57', '1', '1153');
INSERT INTO `REF_ROLE_MENU` VALUES ('58', '1', '1154');
INSERT INTO `REF_ROLE_MENU` VALUES ('59', '1', '1155');
INSERT INTO `REF_ROLE_MENU` VALUES ('62', '1', '1141');
INSERT INTO `REF_ROLE_MENU` VALUES ('63', '1', '1142');
INSERT INTO `REF_ROLE_MENU` VALUES ('64', '1', '1143');
INSERT INTO `REF_ROLE_MENU` VALUES ('65', '1', '1156');
INSERT INTO `REF_ROLE_MENU` VALUES ('66', '1', '1157');

-- ----------------------------
-- Table structure for `REF_ROLE_PERMISSION`
-- ----------------------------
DROP TABLE IF EXISTS `REF_ROLE_PERMISSION`;
CREATE TABLE `REF_ROLE_PERMISSION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色id',
  `TYPE` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型 （园区、支柱产业、工业产品、生产服务）',
  `VALUE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应的值 ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ref_role_permission
-- ----------------------------
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('78', '1', 'INDUSTRY_ABMAIN', 'N');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('79', '1', 'GARDEN', 'IG_C000');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('80', '1', 'GARDEN', 'IG_C001');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('81', '1', 'GARDEN', 'IG_C002');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('82', '1', 'GARDEN', 'IG_C003');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('83', '1', 'GARDEN', 'IG_C004');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('84', '1', 'GARDEN', 'IG_C005');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('85', '1', 'GARDEN', 'IG_C006');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('86', '1', 'GARDEN', 'IG_C007');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('87', '1', 'GARDEN', 'IG_C008');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('88', '1', 'GARDEN', 'IG_C009');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('89', '1', 'GARDEN', 'IG_C010');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('90', '1', 'GARDEN', 'IG_C011');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('91', '1', 'GARDEN', 'IG_C012');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('92', '1', 'GARDEN', 'IG_C013');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('94', '1', 'INDUSTRY', 'IG_D002');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('95', '1', 'INDUSTRY', 'IG_D003');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('110', '1', 'INDUSTRY_B', 'IG_B001');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('111', '1', 'INDUSTRY_B', 'IG_B002');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('112', '1', 'INDUSTRY_A', 'IG_A001');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('113', '1', 'INDUSTRY_A', 'IG_A002');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('114', '1', 'INDUSTRY_A', 'IG_A003');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('115', '1', 'INDUSTRY_A', 'IG_A004');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('116', '1', 'INDUSTRY_A', 'IG_A005');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('117', '1', 'INDUSTRY_A', 'IG_A006');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('118', '1', 'INDUSTRY_A', 'IG_A007');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('119', '1', 'INDUSTRY_A', 'IG_A008');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('120', '1', 'INDUSTRY_B', 'IG_B003');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('121', '1', 'INDUSTRY_B', 'IG_B004');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('122', '1', 'INDUSTRY_B', 'IG_B005');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('123', '1', 'INDUSTRY_B', 'IG_B006');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('124', '1', 'INDUSTRY', 'IG_D001');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('125', '1', 'INDUSTRY', 'IG_D004');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('126', '1', 'INDUSTRY', 'IG_D005');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('127', '1', 'INDUSTRY', 'IG_D006');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('128', '1', 'INDUSTRY', 'IG_D007');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('129', '1', 'INDUSTRY', 'IG_D008');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('130', '1', 'INDUSTRY', 'IG_D009');
INSERT INTO `REF_ROLE_PERMISSION` VALUES ('131', '1', 'INDUSTRY', 'MODULE_E_MAIN');

-- ----------------------------
-- Table structure for `REF_USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `REF_USER_ROLE`;
CREATE TABLE `REF_USER_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ref_user_role
-- ----------------------------
INSERT INTO `REF_USER_ROLE` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `SYS_DIC`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DIC`;
CREATE TABLE `SYS_DIC` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `P_ID` bigint(20) DEFAULT NULL COMMENT '父ID',
  `NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'CODE值',
  `VAL` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'VALUE值',
  `REMARK` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `STATUS` bigint(20) DEFAULT NULL COMMENT '状态',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `SYS_DIC` VALUES ('1', null, '启用禁用状态', 'ENABLE_DISABLE', null, '关键字典', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_DIC` VALUES ('2', '1', '启用', 'ENABLE', null, '关键字典', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_DIC` VALUES ('3', '1', '禁用', 'DISABLE', null, '关键字典', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_DIC` VALUES ('4', null, '项目类型', 'PROJECT_TYPE', null, '项目类型包括基础设施建设项目和园区产业项目', '3', '2017-03-10 09:53:40', '2017-03-22 15:00:12');
INSERT INTO `SYS_DIC` VALUES ('5', '4', '基础设施建设项目', 'BASE', null, '', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('6', '4', '园区产业项目', 'INDUSTRY', null, '', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('7', null, '项目状态', 'PROJECT_STATUS', null, '项目状态包括拟建、在建、竣工', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('8', '7', '拟建', 'PROSTATUS_A', null, '', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('9', '7', '在建', 'PROSTATUS_B', null, '', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('10', '7', '竣工', 'PROSTATUS_C', null, '', '2', '2017-03-10 09:53:40', '2017-03-10 09:53:40');
INSERT INTO `SYS_DIC` VALUES ('13', null, '园区新闻类型', 'GARDEN_NEWS_TYPE', null, '园区新闻类型 : 园区动态和园区政策', '2', '2017-03-10 11:36:43', '2017-03-10 11:36:43');
INSERT INTO `SYS_DIC` VALUES ('14', '13', '园区政策', 'GARDEN_B', '', '', '2', '2017-03-10 11:39:31', '2017-03-10 11:45:54');
INSERT INTO `SYS_DIC` VALUES ('15', '13', '园区动态', 'GARDEN_A', '', '', '2', '2017-03-10 11:40:26', '2017-03-10 11:45:47');

-- ----------------------------
-- Table structure for `SYS_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MENU`;
CREATE TABLE `SYS_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `P_ID` bigint(20) DEFAULT NULL COMMENT '父ID',
  `MENU_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单CODE',
  `TITLE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `URL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单链接',
  `REMARK` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `STATUS` bigint(20) DEFAULT NULL COMMENT '状态',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `SYS_MENU` VALUES ('1', null, 'MENU', '菜单', 'XX', '根目录', '2', '2016-01-01 00:00:00', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('2', '1', 'SYS_MAIN', '系统管理', 'XX', '重要', '2', '2017-03-01 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('3', '2', 'SYS_USER', '用户管理', '/sysuser/list.do', '重要', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('4', '2', 'SYS_ROLE', '角色管理', '/sysrole/opt-query/roleList.do', '重要', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('5', '2', 'SYS_MENU', '菜单管理', '/sysmenu/list.do', '重要', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('6', '2', 'SYS_CATEGORY', '分类管理', '/dicCategory/opt-query/list.do', '重要', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('7', '2', 'SYS_DIC', '字典管理', '/dicItem/opt-query/list.do', '重要', '2', '2017-03-03 15:30:10', '2017-03-03 15:30:10');
INSERT INTO `SYS_MENU` VALUES ('10', '1', 'USER_MANAGE', '用户管理', 'XX', '重要', '2', '2017-03-02 09:59:31', '2017-03-10 09:59:31');
INSERT INTO `SYS_MENU` VALUES ('11', '10', 'USER_PERSON', '个人用户', '/userManage/opt-query/personList.do', '重要', '2', '2017-03-10 09:59:32', '2017-03-10 09:59:32');
INSERT INTO `SYS_MENU` VALUES ('12', '10', 'USER_INSTITUTION', '机构用户', '/userManage/opt-query/institutionList.do', '重要', '2', '2017-03-10 09:59:32', '2017-03-10 09:59:32');
INSERT INTO `SYS_MENU` VALUES ('13', '10', 'USER_COMPANY', '企业用户', '/userManage/opt-query/companyList.do', '重要', '2', '2017-03-10 09:59:32', '2017-03-10 09:59:32');
INSERT INTO `SYS_MENU` VALUES ('1121', '1', 'COM', '企业管理', 'XX', '重要', '2', '2017-03-03 16:48:15', '2017-03-03 16:48:15');
INSERT INTO `SYS_MENU` VALUES ('1122', '1121', 'COM1', '企业列表', '/companyManage/list', '重要', '2', '2017-03-06 09:24:38', '2017-03-03 16:50:17');
INSERT INTO `SYS_MENU` VALUES ('1123', '1', 'COMPANY_INFO', '企业信息', 'XX', '重要', '2', '2017-03-04 16:31:27', '2017-03-27 13:59:08');
INSERT INTO `SYS_MENU` VALUES ('1124', '1123', 'COMPANY_INFO_NEWS', '新闻管理', '/newsall/opt-query/newsAllList.do?moduleCode=NEWS_A', '重要', '2', '2017-03-06 16:33:27', '2017-03-06 16:33:27');
INSERT INTO `SYS_MENU` VALUES ('1125', '1123', 'COMPANY_INFO_1', '企业列表', '/f1company/opt-query/f1companyList.do', '重要', '2', '2017-03-08 16:32:28', '2017-03-09 11:29:30');
INSERT INTO `SYS_MENU` VALUES ('1126', '1', 'JS_CX', '技术创新', 'XX', '重要', '2', '2017-03-08 16:34:17', '2017-03-08 16:38:46');
INSERT INTO `SYS_MENU` VALUES ('1127', '1126', 'JS_CX_NEWS', '新闻管理', '/newsall/opt-query/newsAllList.do?moduleCode=NEWS_C', '重要', '2', '2017-03-08 16:35:31', '2017-03-08 16:40:54');
INSERT INTO `SYS_MENU` VALUES ('1128', '1', 'CX_CY', '创新创业', 'XX', '重要', '2', '2017-03-09 16:36:51', '2017-03-08 16:38:29');
INSERT INTO `SYS_MENU` VALUES ('1129', '1128', 'CX_CY_NEWS', '新闻管理', '/newsall/opt-query/newsAllList.do?moduleCode=NEWS_D', '重要', '2', '2017-03-08 16:38:14', '2017-03-08 16:41:14');
INSERT INTO `SYS_MENU` VALUES ('1130', '1123', 'SOURCE_M', '资源管理', '/f1source/opt-query/f1sourceList.do', '重要', '2', '2017-03-09 17:06:04', '2017-03-09 17:06:04');
INSERT INTO `SYS_MENU` VALUES ('1131', '1', 'INDUSTRYGARDEN', '产业园区', 'XX', '重要', '2', '2017-03-07 09:57:28', '2017-03-10 09:57:28');
INSERT INTO `SYS_MENU` VALUES ('1132', '1131', 'GARDEN_NEWS', '新闻管理', '/industrygarden/opt-query/newslist.do', '重要', '2', '2017-03-10 09:58:04', '2017-03-16 15:33:30');
INSERT INTO `SYS_MENU` VALUES ('1133', '1131', 'GARDEN_MANEGER', '园区管理', '/industrygarden/opt-query/gardenlist.do', '重要', '2', '2017-03-10 09:58:46', '2017-03-16 15:33:37');
INSERT INTO `SYS_MENU` VALUES ('1134', '1131', 'GARDEN_PROJECT', '园区项目', '/industrygarden/opt-query/projectlist.do', '重要', '2', '2017-03-10 09:59:09', '2017-03-16 15:33:42');
INSERT INTO `SYS_MENU` VALUES ('1135', '1128', 'SOURCE_M_G', '资源管理', '/f6source/opt-query/f6sourceList.do', '重要', '2', '2017-03-13 15:26:02', '2017-03-13 16:42:43');
INSERT INTO `SYS_MENU` VALUES ('1136', '1126', 'SOURCE_M_H', '资源管理', '/f5source/opt-query/f5sourceList.do', '重要', '2', '2017-03-13 15:28:17', '2017-03-13 15:28:17');
INSERT INTO `SYS_MENU` VALUES ('1137', '1', 'SERVICE', '产品服务', 'XX', '重要', '2', '2017-03-05 17:34:14', '2017-03-13 17:34:14');
INSERT INTO `SYS_MENU` VALUES ('1138', '1137', 'SERVICE_CPLIST', '企业列表', '/service/cplist.do', '重要', '2', '2017-03-13 17:34:45', '2017-03-13 17:34:45');
INSERT INTO `SYS_MENU` VALUES ('1139', '1137', 'SERVICE_NEWS', '新闻管理', '/service/news.do', '重要', '2', '2017-03-13 17:35:09', '2017-03-13 17:35:09');
INSERT INTO `SYS_MENU` VALUES ('1140', '1137', 'SERVICE_RESOURCE', '资源管理', '/service/opt-query/resource.do', '重要', '2', '2017-03-13 17:35:30', '2017-03-17 09:26:28');
INSERT INTO `SYS_MENU` VALUES ('1141', '1', 'PILLARINDUSTRY', '支柱产业', 'XX', '重要', '2', '2017-03-06 09:54:09', '2017-03-14 09:54:09');
INSERT INTO `SYS_MENU` VALUES ('1142', '1141', 'INDUSTRY_NEWS', '新闻管理', '/pillarindustry/opt-query/newslist.do', '重要', '2', '2017-03-14 09:54:42', '2017-03-14 10:26:48');
INSERT INTO `SYS_MENU` VALUES ('1143', '1141', 'INDUSTRY_MANAGER', '产业管理', '/pillarindustry/opt-query/industrylist.do', '重要', '2', '2017-03-14 09:55:31', '2017-03-14 09:55:31');
INSERT INTO `SYS_MENU` VALUES ('1144', '1126', 'INNOVATE', '创新人才', '/ilperson/opt-query/ilList?type=INNOVATE', '重要', '2', '2017-03-14 13:20:14', '2017-03-14 13:20:14');
INSERT INTO `SYS_MENU` VALUES ('1145', '1128', 'LEADER', '领军人物', '/ilperson/opt-query/ilList?type=LEADER', '重要', '2', '2017-03-14 13:20:54', '2017-03-14 13:20:54');
INSERT INTO `SYS_MENU` VALUES ('1146', '1128', 'COMPANY_INFO_2', '示范企业', '/f6company/opt-query/f6companyList.do', '重要', '2', '2017-03-15 09:16:59', '2017-03-15 09:16:59');
INSERT INTO `SYS_MENU` VALUES ('1147', '1', 'ABUTMENT', '对接服务', 'XX', '重要', '2', '2017-03-11 11:18:04', '2017-03-15 13:28:28');
INSERT INTO `SYS_MENU` VALUES ('1148', '1147', 'NEWS_LIST', '新闻管理', '/news/query/list', '重要', '2', '2017-03-15 11:23:58', '2017-03-15 11:33:22');
INSERT INTO `SYS_MENU` VALUES ('1149', '1147', 'RESOURCE_MANAGE', '资源管理', '/resouce/query/list', '重要', '2', '2017-03-15 11:26:54', '2017-03-15 11:33:04');
INSERT INTO `SYS_MENU` VALUES ('1150', '1147', 'DYNAMIC_DATA', '动态数据', '/dynamic/query/list', '重要', '2', '2017-03-15 11:32:08', '2017-03-15 11:32:08');
INSERT INTO `SYS_MENU` VALUES ('1151', '1126', 'DYNAMIC_DATA_F5', '动态数据', '/dynamicdata/opt-query/dynamicData.do', '重要', '2', '2017-03-15 15:24:23', '2017-03-15 15:24:23');
INSERT INTO `SYS_MENU` VALUES ('1152', '1128', 'DYNAMIC_DATA_F6', '动态数据', '/dynamicdata/opt-query/dynamicData1.do', '重要', '2', '2017-03-15 15:25:08', '2017-03-16 15:43:03');
INSERT INTO `SYS_MENU` VALUES ('1153', '1', 'advertisement', '广告管理', 'XX', '重要', '2', '2017-03-12 16:16:44', '2017-03-15 16:16:44');
INSERT INTO `SYS_MENU` VALUES ('1154', '1153', 'advertisementList', '广告列表', '/advertisement/list', '重要', '2', '2017-03-15 16:17:33', '2017-03-15 16:20:55');
INSERT INTO `SYS_MENU` VALUES ('1155', '1131', 'GARDEN_RESOURCE', '资源管理', '/industrygarden/opt-query/resource.do', '重要', '2', '2017-03-16 15:34:38', '2017-03-16 15:34:38');
INSERT INTO `SYS_MENU` VALUES ('1156', '1141', 'INDUSTRY_RESOURCE', '资源管理', '/pillarindustry/opt-query/resource.do', '重要', '2', '2017-03-16 15:35:12', '2017-03-16 15:35:12');
INSERT INTO `SYS_MENU` VALUES ('1157', '1141', 'INDUSTRY_COMPANY', '企业列表', '/pillarindustry/opt-query/companylist.do', '重要', '2', '2017-03-16 15:35:36', '2017-03-16 15:35:36');

-- ----------------------------
-- Table structure for `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色CODE',
  `ROLE_NAME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `REMARK` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `STATUS` bigint(20) DEFAULT NULL COMMENT '状态',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `SYS_ROLE` VALUES ('1', 'system', '系统管理员', '系统初始数据', '2', '2017-03-20 14:00:05', '2017-03-20 14:00:05');

-- ----------------------------
-- Table structure for `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_NO` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '帐号',
  `PWD` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `PWD_MD` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码加密后',
  `NAME` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `TEL_NO` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `IMAGE_NAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像名称',
  `IMAGE_URL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像地址',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` bigint(20) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('1', 'system', '12345678', '25d55ad283aa400af464c76d713c07ad', '系统管理员', '', '', '系统头像', null, '2017-03-03 15:30:10', '2017-03-03 15:30:10', '2');
