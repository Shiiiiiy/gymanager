/*
Navicat MySQL Data Transfer
2期追加3张其他表[初始化得到空表+不必初始化数据].sql
recruit			1期招聘表
resume			1期简历表
innov_business	1期追加的创新创业示范企业表

Source Server         : wangjun
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-03-27 15:14:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `INNOV_BUSINESS`
-- ----------------------------
DROP TABLE IF EXISTS `INNOV_BUSINESS`;
CREATE TABLE `INNOV_BUSINESS` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `NAME` varchar(32) DEFAULT '' COMMENT '成果名称',
  `BELONG` varchar(32) DEFAULT '' COMMENT '归属',
  `LEVEL` varchar(32) DEFAULT '' COMMENT '级别',
  `SCALE` varchar(32) DEFAULT '' COMMENT '投资规模',
  `START_TIME` datetime DEFAULT NULL COMMENT '创立时间',
  `STATUS` bigint(20) DEFAULT NULL COMMENT '状态',
  `COMMENTS` varchar(500) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `RECRUIT`
-- ----------------------------
DROP TABLE IF EXISTS `RECRUIT`;
CREATE TABLE `RECRUIT` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `USER_ID` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `JOB_NAME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '岗位名称',
  `SALARY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '薪酬福利',
  `WORK_PROPERTY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作性质',
  `WORK_LOCATION` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作地点',
  `NEED_NO` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '招聘人数',
  `MAIL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简历投递邮箱',
  `LINK_MAN` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人',
  `PHONE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `DUTY` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '主要职责',
  `TCONDITION` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任职资格要求',
  `CP_BRIEF` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '公司介绍',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- ----------------------------
-- Table structure for `RESUME`
-- ----------------------------
DROP TABLE IF EXISTS `RESUME`;
CREATE TABLE `RESUME` (
  `ID` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `USER_ID` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `NAME` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `GENDER` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `NATION` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名族',
  `BIRTHDAY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出生年月',
  `EDUCATION_LEVEL` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教育程度',
  `SCHOOL` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '毕业院校',
  `MAJOR` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业',
  `GRADUATION` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '毕业时间',
  `LANGUAGE_LEVEL` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '外语水平',
  `MARRY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '婚姻状况',
  `LIVECITY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现所在城市',
  `HOUSEHOLD` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '户口所在地',
  `CERTIFICATE_TYPE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证件类型',
  `CERTIFICATE_NO` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证件号码',
  `JOB_INTENSION` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '求职意向',
  `SALARY` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '期望薪酬',
  `MAIL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `EDUCATION_BG` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '教育背景',
  `WORK_EXP` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作经历',
  `SKILL` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专业技能',
  `PROJECT_EXP` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '项目经验',
  `HOBBY` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '爱好专长、自我评价',
  `RESUME_IMAGE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户简历头像',
  `REC_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

