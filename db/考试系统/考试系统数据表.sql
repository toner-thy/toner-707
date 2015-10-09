/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : 4212init

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2013-09-11 15:03:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ex_examination`
-- ----------------------------
DROP TABLE IF EXISTS `ex_examination`;
CREATE TABLE `ex_examination` (
  `EXAMINATION_ID` varchar(32) NOT NULL COMMENT 'Ա',
  `PAPER_ID` varchar(39) default NULL,
  `EXAMINATION_NAME` varchar(256) default NULL,
  `START_TIME` date default NULL COMMENT 'ʼʱ',
  `END_TIME` date default NULL COMMENT 'ʱ',
  `FETCH_TYPE` int(11) default NULL,
  `STATE` int(11) default NULL COMMENT '״̬0δɾ1ɾ',
  `ENABLE` int(11) default NULL COMMENT '0δɾ,1ɾ',
  `DESCRIPTION` varchar(1024) default NULL,
  `EXAMNATION_TYPE` int(11) default NULL COMMENT '(0��ԣ1վ��)',
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  PRIMARY KEY  (`EXAMINATION_ID`),
  KEY `FK_Reference_9` (`PAPER_ID`),
  KEY `FKE1FE4A65B0F65A1F` (`MODIFY_USER`),
  KEY `FKE1FE4A6533A87159` (`CREATE_ORGAN`),
  KEY `FKE1FE4A659CDCACB0` (`PAPER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `ex_exam_organ`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_organ`;
CREATE TABLE `ex_exam_organ` (
  `EXAM_ORGAN_ID` varchar(39) NOT NULL,
  `EXAMINATION_ID` varchar(32) default NULL COMMENT 'Ա',
  `ORGAN_NAME` varchar(256) NOT NULL,
  `INDUSTRY_CODE` varchar(128) NOT NULL,
  `ORGAN_ID` varchar(39) NOT NULL,
  `CREATE_TIME` date NOT NULL,
  `CREATE_ORGAN` varchar(39) NOT NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  PRIMARY KEY  (`EXAM_ORGAN_ID`),
  KEY `FK_Reference_21` (`EXAMINATION_ID`),
  KEY `FK61AA44FDB0F65A1F` (`MODIFY_USER`),
  KEY `FK61AA44FD1260E254` (`ORGAN_ID`),
  KEY `FK61AA44FD33A87159` (`CREATE_ORGAN`),
  KEY `FK61AA44FD5D098643` (`INDUSTRY_CODE`),
  KEY `FK61AA44FDAB828BAB` (`EXAMINATION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_exam_organ
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_exam_organ_role`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_organ_role`;
CREATE TABLE `ex_exam_organ_role` (
  `exam_organ_role_id` varchar(39) NOT NULL COMMENT 'Եλɫ',
  `exam_role_id` varchar(39) default NULL COMMENT 'Խɫ',
  `EXAMINATION_ID` varchar(32) default NULL COMMENT 'Ա',
  `exam_role_name` varchar(256) default NULL COMMENT 'Խɫ',
  `total` int(11) default NULL COMMENT 'Ծ',
  `pass` int(11) default NULL,
  `industry` int(11) default NULL COMMENT 'ҵ',
  `personnel` int(11) default NULL COMMENT 'Ա',
  `basic` int(11) default NULL,
  `EXAMORGAN_ID` varchar(39) default NULL,
  `STATE` int(11) default NULL COMMENT '״̬',
  PRIMARY KEY  (`exam_organ_role_id`),
  KEY `FK_Reference_6` (`EXAMINATION_ID`),
  KEY `FK_Reference_8` (`exam_role_id`),
  KEY `FKD74A8A78AB828BAB` (`EXAMINATION_ID`),
  KEY `FKD74A8A78F71C2C1E` (`exam_role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_exam_organ_role
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_exam_role`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_role`;
CREATE TABLE `ex_exam_role` (
  `exam_role_id` varchar(39) NOT NULL COMMENT 'Խɫ',
  `exam_role_name` varchar(256) default NULL COMMENT 'Խɫ',
  `total` int(11) default NULL COMMENT 'Ծ',
  `pass` int(11) default NULL,
  `industry` int(11) default NULL COMMENT 'ҵ',
  `personnel` int(11) default NULL COMMENT 'Ա',
  `basic` int(11) default NULL,
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  PRIMARY KEY  (`exam_role_id`),
  KEY `FK76C4C00A33A87159` (`CREATE_ORGAN`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_exam_role
-- ----------------------------
INSERT INTO ex_exam_role VALUES ('ff8080813d8fef1f013da558a165050b', '涉密人员', '50', '60', '0', '50', '50', '2013-03-26', '402881ee264ad3d401264ad612270003', null, null);
INSERT INTO ex_exam_role VALUES ('ff8080813d8fef1f013da557f3230509', '一般人员', '50', '60', '0', '50', '50', '2013-03-26', '402881ee264ad3d401264ad612270003', null, null);
INSERT INTO ex_exam_role VALUES ('ff8080813d8fef1f013da558578d050a', '领导干部', '50', '60', '0', '50', '50', '2013-03-26', '402881ee264ad3d401264ad612270003', null, null);

-- ----------------------------
-- Table structure for `ex_exam_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_role_user`;
CREATE TABLE `ex_exam_role_user` (
  `USER_ID` varchar(39) NOT NULL COMMENT '用户ID',
  `EXAM_ROLE_ID` varchar(39) NOT NULL COMMENT '角色ID',
  PRIMARY KEY  (`USER_ID`,`EXAM_ROLE_ID`),
  KEY `FK_Reference_36` (`EXAM_ROLE_ID`),
  KEY `FKDCB55AE0E93AB7FE` (`USER_ID`),
  KEY `FKDCB55AE0F71C2C1E` (`EXAM_ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- ----------------------------
-- Records of ex_exam_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_exam_user`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_user`;
CREATE TABLE `ex_exam_user` (
  `EXAM_USER_ID` varchar(39) NOT NULL default '' COMMENT 'Ա',
  `exam_organ_role_id` varchar(39) default NULL COMMENT 'Եλɫ',
  `EXAMINATION_ID` varchar(32) default NULL COMMENT 'Ա',
  `USER_ID` varchar(39) default NULL COMMENT 'Ա',
  `USER_NAME` varchar(256) default NULL COMMENT 'Ա',
  `USER_ORGAN_ID` varchar(39) default NULL COMMENT 'ûλ',
  `USER_ORGAN_NAME` varchar(256) default NULL COMMENT 'ûλ',
  `USER_INFO_ID` varchar(39) default NULL COMMENT 'Ա',
  `USER_INFO_NAME` varchar(256) default NULL COMMENT 'Ա',
  `USER_ROLE_NAME` varchar(256) default NULL COMMENT 'ûԽɫ',
  `USER_ROLE_ID` varchar(39) default NULL COMMENT 'ûԽɫ',
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  `STATE` int(11) default NULL,
  PRIMARY KEY  (`EXAM_USER_ID`),
  KEY `FK76C62B5FB0F65A1F` (`MODIFY_USER`),
  KEY `FK76C62B5F33A87159` (`CREATE_ORGAN`),
  KEY `FK76C62B5F9681F500` (`USER_ORGAN_ID`),
  KEY `FK76C62B5FE93AB7FE` (`USER_ID`),
  KEY `FK76C62B5FE24A914F` (`exam_organ_role_id`),
  KEY `FK76C62B5FAB828BAB` (`EXAMINATION_ID`),
  KEY `FK76C62B5FF46C2A43` (`USER_INFO_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_exam_user
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_exam_user_state`
-- ----------------------------
DROP TABLE IF EXISTS `ex_exam_user_state`;
CREATE TABLE `ex_exam_user_state` (
  `EXAM_USER_STATE_ID` varchar(39) NOT NULL COMMENT 'û״̬ʷ',
  `EXAM_USER_ID` varchar(39) default NULL COMMENT 'Ա',
  `CREATE_TIME` datetime default NULL COMMENT 'ʱ',
  `STATE` int(11) default NULL COMMENT 'ʷ״̬',
  `CREATE_ORGAN` varchar(39) default NULL,
  `START_TIME` date default NULL,
  `END_TIME` date default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  `HISTORY_PAPER_ID` varchar(39) default NULL,
  PRIMARY KEY  (`EXAM_USER_STATE_ID`),
  KEY `FK_Reference_25` (`EXAM_USER_ID`),
  KEY `FKC0510FB1B0F65A1F` (`MODIFY_USER`),
  KEY `FKC0510FB133A87159` (`CREATE_ORGAN`),
  KEY `FKC0510FB16E615488` (`EXAM_USER_ID`),
  KEY `FKC0510FB19E91BE39` (`HISTORY_PAPER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_exam_user_state
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_history_paper`
-- ----------------------------
DROP TABLE IF EXISTS `ex_history_paper`;
CREATE TABLE `ex_history_paper` (
  `PAPER_ID` varchar(39) NOT NULL COMMENT 'Ծ',
  `PAPER_NAME` varchar(256) default NULL COMMENT 'Ծ',
  `PAPER_TYPE` varchar(32) default NULL COMMENT 'Ծ',
  `DESCRIPTION` varchar(1024) default NULL COMMENT 'Ծ',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT 'Ծ��λ',
  `CREATE_TIME` date default NULL COMMENT 'Ծ��ʱ',
  `IS_PASS` int(11) default NULL COMMENT 'Ƿͨ',
  `SCORE` float default NULL COMMENT 'Գɼ',
  `state` int(11) default NULL,
  `EXAM_USER_ID` varchar(39) default NULL COMMENT 'Ա',
  `EXAM_USER_NAME` varchar(256) default NULL COMMENT 'û',
  `EXAM_ORGAN_ROLE_ID` varchar(39) default NULL,
  `EXAM_ORGAN_ROLE_NAME` varchar(256) default NULL,
  `EXAM_USER_INFO_ID` varchar(39) default NULL COMMENT 'ûԱ',
  `EXAM_ORGAN_ID` varchar(39) default NULL COMMENT 'ûλ',
  `EXAM_ORGAN_NAME` varchar(256) default NULL COMMENT 'ûλ',
  `MODIFY_USER` varchar(39) default NULL,
  `examination_id` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  PRIMARY KEY  (`PAPER_ID`),
  KEY `FKC507C3953E024663` (`EXAM_USER_INFO_ID`),
  KEY `FKC507C395B0F65A1F` (`MODIFY_USER`),
  KEY `FKC507C395BBC7C72C` (`EXAM_ORGAN_ID`),
  KEY `FKC507C39533A87159` (`CREATE_ORGAN`),
  KEY `FKC507C3956E615488` (`EXAM_USER_ID`),
  KEY `FKC507C395AB828BAB` (`examination_id`),
  KEY `FKC507C395E24A914F` (`EXAM_ORGAN_ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_history_paper
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_history_paper_question`
-- ----------------------------
DROP TABLE IF EXISTS `ex_history_paper_question`;
CREATE TABLE `ex_history_paper_question` (
  `PAPER_QUESTION_ID` varchar(39) NOT NULL COMMENT 'ԾĿ',
  `OPTIONF` varchar(512) default NULL,
  `OPTIONE` varchar(512) default NULL,
  `OPTIONL` varchar(512) default NULL,
  `OPTIONK` varchar(512) default NULL,
  `OPTIONJ` varchar(512) default NULL,
  `OPTIONI` varchar(512) default NULL,
  `OPTIONH` varchar(512) default NULL,
  `OPTIONG` varchar(512) default NULL,
  `OPTIOND` varchar(512) default NULL,
  `OPTIONC` varchar(512) default NULL,
  `OPTIONB` varchar(512) default NULL,
  `OPTIONA` varchar(512) default NULL,
  `COMMENT` varchar(512) default NULL COMMENT 'Ŀע',
  `QUESTION_TYPE` int(11) default NULL COMMENT 'Ŀ',
  `ANSWER` varchar(1024) default NULL COMMENT 'Ŀ',
  `ENABLE` int(11) default NULL COMMENT 'Ƿɾ',
  `STATE` int(11) default NULL COMMENT 'Ŀ״̬',
  `CONTENT` varchar(1024) default NULL COMMENT 'Ŀ',
  `QUESTION_NO` int(11) default NULL COMMENT 'Ŀ',
  `QUESTION_ID` varchar(39) default NULL COMMENT 'Ŀ',
  `PAPER_QUESTION_TYPE_ID` varchar(39) default NULL COMMENT 'ԾĿͱ',
  `SORT` int(11) default NULL COMMENT 'Զ',
  `USER_ANSWER` varchar(1024) default NULL COMMENT 'û',
  `IS_RIGHT` int(11) default NULL COMMENT 'Ƿȷ',
  `QUESTION_CATEGORY_ID` varchar(39) default NULL COMMENT 'Ŀ',
  `QUESTION_CATEGORY_NAME` varchar(512) default NULL COMMENT 'Ŀ',
  `EXAM_ROLE_ID` varchar(39) default NULL COMMENT 'ɫ',
  `EXAM_INDUSTRY_CODE` varchar(39) default NULL COMMENT 'ҵ',
  `SCORE` float default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `CREATE_TIME` date default NULL,
  PRIMARY KEY  (`PAPER_QUESTION_ID`),
  KEY `FK_Reference_13` (`PAPER_QUESTION_TYPE_ID`),
  KEY `FKD2A9E15033A87159` (`CREATE_ORGAN`),
  KEY `FKD2A9E150F07138FD` (`QUESTION_CATEGORY_ID`),
  KEY `FKD2A9E150533B8DDA` (`PAPER_QUESTION_TYPE_ID`),
  KEY `FKD2A9E150F71C2C1E` (`EXAM_ROLE_ID`),
  KEY `FKD2A9E1502CD3DAD0` (`QUESTION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_history_paper_question
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_history_paper_question_type`
-- ----------------------------
DROP TABLE IF EXISTS `ex_history_paper_question_type`;
CREATE TABLE `ex_history_paper_question_type` (
  `PAPER_QUESTION_TYPE_ID` varchar(39) NOT NULL COMMENT 'ԾĿͱ',
  `PAPER_ID` varchar(39) default NULL COMMENT 'Ծ',
  `QUESTION_TYPE` int(11) default NULL COMMENT 'Ŀ',
  `SORT` int(11) default NULL,
  `SCORE` float default NULL COMMENT '题目类型总分',
  PRIMARY KEY  (`PAPER_QUESTION_TYPE_ID`),
  KEY `FK_Reference_12` (`PAPER_ID`),
  KEY `FKEF4FB8C976568AEE` (`PAPER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_history_paper_question_type
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_law`
-- ----------------------------
DROP TABLE IF EXISTS `ex_law`;
CREATE TABLE `ex_law` (
  `LAW_ID` varchar(32) NOT NULL COMMENT 'ɷID',
  `LAW_NAME` varchar(256) default NULL COMMENT 'ɷ',
  `LAW_CLASS_FK` varchar(32) default NULL COMMENT 'ɷID',
  `SORT` int(11) default NULL,
  `CONTENT` longtext COMMENT 'ɷ',
  `CREATE_TIME` datetime default NULL COMMENT 'ʱ',
  PRIMARY KEY  (`LAW_ID`),
  KEY `FK_Ref6` (`LAW_CLASS_FK`),
  KEY `FKB3214C16847067F` (`LAW_CLASS_FK`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ɷ';

-- ----------------------------
-- Records of ex_law
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_law_class`
-- ----------------------------
DROP TABLE IF EXISTS `ex_law_class`;
CREATE TABLE `ex_law_class` (
  `LAW_CLASS_ID` varchar(32) NOT NULL COMMENT 'ɷID',
  `PARENT` varchar(32) default NULL COMMENT 'ุڵID',
  `LAW_CLASS_NAME` varchar(128) default NULL,
  `SORT` int(11) default NULL,
  `LAYER` varchar(128) default NULL COMMENT '㼶',
  `DESCRIPTOIN` varchar(512) default NULL COMMENT '˵',
  `TOP` int(11) default NULL COMMENT 'ö',
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  PRIMARY KEY  (`LAW_CLASS_ID`),
  KEY `FK_Ref9` (`PARENT`),
  KEY `FKA383700FB0F65A1F` (`MODIFY_USER`),
  KEY `FKA383700F33A87159` (`CREATE_ORGAN`),
  KEY `FKA383700F668A1440` (`PARENT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='ɷ';

-- ----------------------------
-- Records of ex_law_class
-- ----------------------------
INSERT INTO ex_law_class VALUES ('1', null, '知识库类别', '1', '1', null, null, null, null, null, null);
INSERT INTO ex_law_class VALUES ('ca8789993ee9f8c2013eea17fc220093', '1', '保密法', '1', '1000', '保密法', '1', '2013-05-28', '402881ee264ad3d401264ad612270003', null, null);

-- ----------------------------
-- Table structure for `ex_paper`
-- ----------------------------
DROP TABLE IF EXISTS `ex_paper`;
CREATE TABLE `ex_paper` (
  `PAPER_ID` varchar(39) NOT NULL COMMENT 'Ծ',
  `PAPER_NAME` varchar(256) NOT NULL COMMENT 'Ծ',
  `PAPER_TYPE` varchar(32) NOT NULL COMMENT 'Ծ',
  `DESCRIPTION` varchar(1024) default NULL COMMENT 'Ծ',
  `CREATE_ORGAN` varchar(39) NOT NULL COMMENT 'Ծ��λ',
  `CREATE_TIME` date NOT NULL COMMENT 'Ծ��ʱ',
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  `TOTAL_SCORE` int(11) default NULL,
  `PASS_SCORE` int(11) default NULL,
  `IMPORT_STATE` int(11) default NULL COMMENT '导入状态：0未导入，1导入',
  `TYPE` int(11) default NULL COMMENT '试卷类型：1混编试卷；空其他',
  PRIMARY KEY  (`PAPER_ID`),
  KEY `FK7036EF00B0F65A1F` (`MODIFY_USER`),
  KEY `FK7036EF0033A87159` (`CREATE_ORGAN`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_paper
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_paper_question`
-- ----------------------------
DROP TABLE IF EXISTS `ex_paper_question`;
CREATE TABLE `ex_paper_question` (
  `PAPER_QUESTION_ID` varchar(39) NOT NULL COMMENT 'ԾĿ',
  `OPTIONA` varchar(512) default NULL,
  `OPTIONB` varchar(512) default NULL,
  `OPTIONC` varchar(512) default NULL,
  `OPTIOND` varchar(512) default NULL,
  `OPTIONG` varchar(512) default NULL,
  `OPTIONH` varchar(512) default NULL,
  `OPTIONI` varchar(512) default NULL,
  `OPTIONJ` varchar(512) default NULL,
  `OPTIONK` varchar(512) default NULL,
  `OPTIONL` varchar(512) default NULL,
  `OPTIONE` varchar(512) default NULL,
  `OPTIONF` varchar(512) default NULL,
  `COMMENT` varchar(512) NOT NULL COMMENT 'Ŀע',
  `QUESTION_TYPE` int(11) NOT NULL COMMENT 'Ŀ',
  `ANSWER` varchar(1024) default NULL,
  `ENABLE` int(11) NOT NULL COMMENT 'Ƿɾ',
  `STATE` int(11) default NULL COMMENT 'Ŀ״̬',
  `CONTENT` varchar(1024) default NULL COMMENT 'Ŀ',
  `QUESTION_NO` int(11) default NULL COMMENT 'Ŀ',
  `QUESTION_ID` varchar(39) default NULL COMMENT 'Ŀ',
  `PAPER_QUESTION_TYPE_ID` varchar(39) default NULL COMMENT 'ԾĿͱ',
  `SORT` int(11) default NULL COMMENT 'Զ',
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  `EXAM_ROLE_ID` varchar(39) default NULL COMMENT 'ɫ',
  `SCORE` float default NULL,
  `EXAM_INDUSTRY_CODE` varchar(39) default NULL COMMENT 'ҵ',
  `RANDOM_STATE` int(11) default NULL COMMENT '随机状态：1随机2定制',
  `QUESTION_CATEGORYID` varchar(2000) default NULL COMMENT '保存随机哪几个题库ID',
  `PAPER_QUESTION_CATEGORY_NAME` varchar(2000) default NULL COMMENT '保存随机哪几个题库名称',
  PRIMARY KEY  (`PAPER_QUESTION_ID`),
  KEY `FK_Reference_4` (`QUESTION_ID`),
  KEY `FK_Reference_5` (`PAPER_QUESTION_TYPE_ID`),
  KEY `FKBD0D5E45B0F65A1F` (`MODIFY_USER`),
  KEY `FKBD0D5E4533A87159` (`CREATE_ORGAN`),
  KEY `FKBD0D5E4583E31C9C` (`PAPER_QUESTION_TYPE_ID`),
  KEY `FKBD0D5E452CD3DAD0` (`QUESTION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_paper_question
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_paper_question_type`
-- ----------------------------
DROP TABLE IF EXISTS `ex_paper_question_type`;
CREATE TABLE `ex_paper_question_type` (
  `SCORE` float default NULL,
  `PAPER_QUESTION_TYPE_ID` varchar(39) NOT NULL,
  `PAPER_ID` varchar(39) default NULL COMMENT 'Ծ',
  `QUESTION_TYPE` int(11) default NULL COMMENT 'Ŀ',
  `SORT` int(11) default NULL,
  PRIMARY KEY  (`PAPER_QUESTION_TYPE_ID`),
  KEY `FK_Reference_26` (`PAPER_ID`),
  KEY `FK45CD0CF49CDCACB0` (`PAPER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_paper_question_type
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_question`
-- ----------------------------
DROP TABLE IF EXISTS `ex_question`;
CREATE TABLE `ex_question` (
  `QUESTION_ID` varchar(39) NOT NULL COMMENT 'Ŀ',
  `QUESTION_NO` int(11) default NULL,
  `CONTENT` varchar(1024) default NULL COMMENT 'Ŀ',
  `STATE` int(11) default NULL COMMENT 'Ŀ״̬',
  `ENABLE` int(11) default NULL COMMENT 'Ƿɾ',
  `ANSWER` varchar(1024) default NULL,
  `QUESTION_TYPE` int(11) default NULL COMMENT 'Ŀ',
  `COMMENT` varchar(512) default NULL COMMENT 'Ŀע',
  `OPTIONA` varchar(512) default NULL,
  `OPTIONB` varchar(512) default NULL,
  `OPTIONC` varchar(512) default NULL,
  `OPTIOND` varchar(512) default NULL,
  `OPTIONE` varchar(512) default NULL,
  `OPTIONF` varchar(512) default NULL,
  `OPTIONG` varchar(512) default NULL,
  `OPTIONH` varchar(512) default NULL,
  `OPTIONI` varchar(512) default NULL,
  `OPTIONJ` varchar(512) default NULL,
  `OPTIONK` varchar(512) default NULL,
  `OPTIONL` varchar(512) default NULL,
  `CREATE_TIME` date default NULL,
  `CREATE_ORGAN` varchar(39) default NULL,
  `MODIFY_USER` varchar(39) default NULL,
  `MODIFY_TIME` date default NULL,
  `EXAM_ROLE_ID` varchar(39) default NULL COMMENT 'ɫ',
  `EXAM_INDUSTRY_CODE` varchar(39) default NULL COMMENT 'ҵ',
  PRIMARY KEY  (`QUESTION_ID`),
  KEY `FK8EF9F2B0F65A1F` (`MODIFY_USER`),
  KEY `FK8EF9F233A87159` (`CREATE_ORGAN`),
  KEY `FK8EF9F24636EE23` (`EXAM_INDUSTRY_CODE`),
  KEY `FK8EF9F2F71C2C1E` (`EXAM_ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_question
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_question_category`
-- ----------------------------
DROP TABLE IF EXISTS `ex_question_category`;
CREATE TABLE `ex_question_category` (
  `QUESTION_CATEGORY_ID` varchar(39) NOT NULL COMMENT 'Ŀ',
  `PARENT` varchar(39) default NULL COMMENT 'Ŀ',
  `QUESTION_CATEGORY_NAME` varchar(256) NOT NULL COMMENT 'Ŀ',
  `DESCRIPTION` varchar(512) default NULL COMMENT 'Ŀ',
  `SORT` int(11) default NULL COMMENT 'Ŀ',
  `LAYER` varchar(1024) NOT NULL,
  `ENABLE` int(11) NOT NULL COMMENT 'Ƿɾ',
  `QUESTION_CATEGORY_TYPE` int(11) default NULL COMMENT '1.基本题库 2.行业题库 3.人员题库',
  `EXAM_ROLE_ID` varchar(39) default NULL COMMENT 'ɫ',
  `EXAM_INDUSTRY_CODE` varchar(39) default NULL COMMENT 'ҵ',
  `CREATE_TIME` date NOT NULL COMMENT 'ʱ',
  `CREATE_ORGAN` varchar(39) NOT NULL COMMENT 'λ',
  `MODIFY_USER` varchar(39) default NULL COMMENT '޸Ա',
  `MODIFY_TIME` date default NULL COMMENT '޸ʱ',
  PRIMARY KEY  (`QUESTION_CATEGORY_ID`),
  KEY `FK_Reference_10` (`PARENT`),
  KEY `FK240E04ABB0F65A1F` (`MODIFY_USER`),
  KEY `FK240E04AB9E3D63A` (`EXAM_INDUSTRY_CODE`),
  KEY `FK240E04ABF71C2C1E` (`EXAM_ROLE_ID`),
  KEY `FK240E04ABFACDA484` (`PARENT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_question_category
-- ----------------------------
INSERT INTO ex_question_category VALUES ('1', null, '题库分类', null, '0', '100', '1', '0', null, '1', '2010-10-22', '1', '1', null);
INSERT INTO ex_question_category VALUES ('2', '1', '基本题库', null, '1', '100000', '1', '1', null, '1', '2010-10-22', '1', '1', null);
INSERT INTO ex_question_category VALUES ('3', '1', '行业题库', null, '1', '100001', '1', '2', null, '1', '2010-10-22', '1', '1', null);
INSERT INTO ex_question_category VALUES ('4', '1', '人员题库', null, '1', '100002', '1', '3', null, '1', '2010-10-22', '1', '1', null);
INSERT INTO ex_question_category VALUES ('ff8080813d8fef1f013da54fcd940042', '4', '涉密人员', '涉密人员', '3', '100002002', '1', '3', 'ff8080813d8fef1f013da558a165050b', null, '2013-03-26', '402881ee264ad3d401264ad612270003', 'SYSTEM_402881ee264ad3d401264ad612270003', '2013-03-26');
INSERT INTO ex_question_category VALUES ('ff8080813d8fef1f013da54f09d50040', '4', '一般人员', '一般人员', '1', '100002000', '1', '3', 'ff8080813d8fef1f013da557f3230509', null, '2013-03-26', '402881ee264ad3d401264ad612270003', 'SYSTEM_402881ee264ad3d401264ad612270003', '2013-03-26');
INSERT INTO ex_question_category VALUES ('ff8080813d8fef1f013da54f6f080041', '4', '领导干部', '领导干部', '2', '100002001', '1', '3', 'ff8080813d8fef1f013da558578d050a', null, '2013-03-26', '402881ee264ad3d401264ad612270003', 'SYSTEM_402881ee264ad3d401264ad612270003', '2013-03-26');

-- ----------------------------
-- Table structure for `ex_question_questioncategory`
-- ----------------------------
DROP TABLE IF EXISTS `ex_question_questioncategory`;
CREATE TABLE `ex_question_questioncategory` (
  `QUESTION_CATEGORY_ID` varchar(39) NOT NULL default '' COMMENT 'Ŀ',
  `QUESTION_ID` varchar(39) NOT NULL default '' COMMENT 'Ŀ',
  PRIMARY KEY  (`QUESTION_CATEGORY_ID`,`QUESTION_ID`),
  KEY `FK_Reference_19` (`QUESTION_CATEGORY_ID`),
  KEY `FK_Reference_20` (`QUESTION_ID`),
  KEY `FK29A8FD91F07138FD` (`QUESTION_CATEGORY_ID`),
  KEY `FK29A8FD912CD3DAD0` (`QUESTION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_question_questioncategory
-- ----------------------------

-- ----------------------------
-- Table structure for `ex_question_type`
-- ----------------------------
DROP TABLE IF EXISTS `ex_question_type`;
CREATE TABLE `ex_question_type` (
  `QUESTION_TYPE_ID` varchar(32) NOT NULL,
  `QUESTION_TYPE_NAME` varchar(256) NOT NULL,
  `QUESTION_TYPE_VALUE` int(11) NOT NULL,
  PRIMARY KEY  (`QUESTION_TYPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ex_question_type
-- ----------------------------
INSERT INTO ex_question_type VALUES ('1', '单选题', '1');
INSERT INTO ex_question_type VALUES ('2', '多选题', '2');
INSERT INTO ex_question_type VALUES ('3', '判断题', '3');
INSERT INTO ex_question_type VALUES ('4', '填空题', '4');

-- ----------------------------
-- Table structure for ex_learn_phase
-- ----------------------------
DROP TABLE IF EXISTS `ex_learn_phase`;
CREATE TABLE `ex_learn_phase` (
  `LEARN_PHASE_ID` varchar(39) NOT NULL,
  `PHASE_NO` int(11) DEFAULT NULL,
  `PHASE_NAME` varchar(255) DEFAULT NULL,
  `LEARN_HOURS` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(39) DEFAULT NULL,
  `CREATE_ORGAN` varchar(39) DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `MODIFY_USER` varchar(39) DEFAULT NULL,
  `MODIFY_ORGAN` varchar(39) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`LEARN_PHASE_ID`),
  KEY `FKC9E5EE34B0F65A1F` (`MODIFY_USER`),
  KEY `FKC9E5EE3433A87159` (`CREATE_ORGAN`),
  KEY `FKC9E5EE34E3710297` (`MODIFY_ORGAN`),
  KEY `FKC9E5EE34D4950B1D` (`CREATE_USER`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ex_learn_phase_book
-- ----------------------------
DROP TABLE IF EXISTS `ex_learn_phase_book`;
CREATE TABLE `ex_learn_phase_book` (
  `LEARN_PHASE_ID` varchar(39) DEFAULT NULL,
  `LEARN_PHASE_BOOK_ID` varchar(39) NOT NULL,
  `FILE_ID` varchar(39) DEFAULT NULL,
  `LEARN_HOURS` int(10) DEFAULT NULL,
  PRIMARY KEY (`LEARN_PHASE_BOOK_ID`),
  KEY `FK1CC3C614D79679C0` (`FILE_ID`),
  KEY `FK1CC3C614B93FC48A` (`LEARN_PHASE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ex_learn`;
CREATE TABLE `ex_learn` (
  `LEARN_ID` varchar(39) NOT NULL,
  `LEARN_TEST_RULE_ID` varchar(39) DEFAULT NULL,
  `SUMMERY` varchar(100) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `INSTRODUCT` text,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(39) DEFAULT NULL,
  `CREATE_ORGAN` varchar(39) DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `MODIFY_USER` varchar(39) DEFAULT NULL,
  `MODIFY_ORGAN` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`LEARN_ID`),
  KEY `FK70002BB8B0F65A1F` (`MODIFY_USER`),
  KEY `FK70002BB833A87159` (`CREATE_ORGAN`),
  KEY `FK70002BB882EF99DC` (`LEARN_TEST_RULE_ID`),
  KEY `FK70002BB8E3710297` (`MODIFY_ORGAN`),
  KEY `FK70002BB8D4950B1D` (`CREATE_USER`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_attend_learn_organ`;
CREATE TABLE `ex_learn_attend_learn_organ` (
  `ATTEND_LEARN_ORGAN_ID` varchar(39) NOT NULL,
  `LEARN_ID` varchar(39) DEFAULT NULL,
  `ORGAN_ID` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`ATTEND_LEARN_ORGAN_ID`),
  KEY `FK57E316181260E254` (`ORGAN_ID`),
  KEY `FK57E31618357A63F8` (`LEARN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_attend_learn_phase`;
CREATE TABLE `ex_learn_attend_learn_phase` (
  `ATTEND_LEARN_PHASE_ID` varchar(39) NOT NULL,
  `LEARN_ID` varchar(39) DEFAULT NULL,
  `LEARN_PHASE_ID` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`ATTEND_LEARN_PHASE_ID`),
  KEY `FK57EC8D82B93FC48A` (`LEARN_PHASE_ID`),
  KEY `FK57EC8D82357A63F8` (`LEARN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ex_learn_attend_learn_role`;
CREATE TABLE `ex_learn_attend_learn_role` (
  `ATTEND_LEARN_ROLE_ID` varchar(39) NOT NULL,
  `LEARN_ID` varchar(39) DEFAULT NULL,
  `EXAM_ROLE_ID` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`ATTEND_LEARN_ROLE_ID`),
  KEY `FK_EX_ATTEN_REFERENCE_EX_LEARN` (`LEARN_ID`),
  KEY `FK_EX_ATTEN_REFERENCE_EX_EXAM_` (`EXAM_ROLE_ID`),
  KEY `FK65EFDF8F357A63F8` (`LEARN_ID`),
  KEY `FK65EFDF8FF71C2C1E` (`EXAM_ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_attend_learn_user`;
CREATE TABLE `ex_learn_attend_learn_user` (
  `ATTEND_LEARN_USER_ID` varchar(39) NOT NULL,
  `ATTEND_LEARN_ORGAN_ID` varchar(39) DEFAULT NULL,
  `ATTEND_LEARN_ROLE_ID` varchar(39) DEFAULT NULL,
  `LEARN_ID` varchar(39) DEFAULT NULL,
  `LAST_LEARN_TIME` datetime DEFAULT NULL,
  `USER_ID` varchar(39) DEFAULT NULL,
  `LEARN_STATUS` int(11) DEFAULT NULL,
  `TEST_STATUS` int(11) DEFAULT NULL,
  `PREPARED_TEST` int(11) DEFAULT NULL,
  PRIMARY KEY (`ATTEND_LEARN_USER_ID`),
  KEY `FK65F14AE4C82E2442` (`ATTEND_LEARN_ROLE_ID`),
  KEY `FK65F14AE49BC2A6D2` (`ATTEND_LEARN_ORGAN_ID`),
  KEY `FK65F14AE4E93AB7FE` (`USER_ID`),
  KEY `FK65F14AE4357A63F8` (`LEARN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ex_learn_test_rule`;
CREATE TABLE `ex_learn_test_rule` (
  `LEARN_TEST_RULE_ID` varchar(39) NOT NULL,
  `IS_ACCORD_ROLE` int(11) DEFAULT NULL,
  `IS_NOT_PASS_TEST` int(11) DEFAULT NULL,
  `IS_PASS_TEST` int(11) DEFAULT NULL,
  `QUESTION_NUM` int(11) DEFAULT NULL,
  `TEST_TIME` int(11) DEFAULT NULL,
  `IS_REPEAT_QUESTION` int(11) DEFAULT NULL,
  `IS_QUESTION_TYPE` int(11) DEFAULT NULL,
  `PASS_QUESTION_NUM` int(11) DEFAULT NULL,
  `PREPARED_TEST` int(11) DEFAULT NULL,
  PRIMARY KEY (`LEARN_TEST_RULE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_test_rule_qtype`;
CREATE TABLE `ex_learn_test_rule_qtype` (
  `LEARN_TEST_RULE_QTYPE_ID` varchar(39) NOT NULL,
  `LEARN_TEST_RULE_ID` varchar(39) DEFAULT NULL,
  `QUESTION_TYPE_ID` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`LEARN_TEST_RULE_QTYPE_ID`),
  KEY `FK_EX_LEARN_REFERENCE_EX_LEARN` (`LEARN_TEST_RULE_ID`),
  KEY `FK4403AF6E82EF99DC` (`LEARN_TEST_RULE_ID`),
  KEY `FK4403AF6EA139B8FD` (`QUESTION_TYPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_book_situation`;
CREATE TABLE `ex_learn_book_situation` (
  `LEARN_BOOK_SITUATION_ID` varchar(39) NOT NULL,
  `LEARN_PHASE_SITUATION_ID` varchar(39) DEFAULT NULL,
  `ATTEND_LEARN_USER_ID` varchar(39) DEFAULT NULL,
  `LEARN_PHASE_BOOK_ID` varchar(39) DEFAULT NULL,
  `LEARN_STATUS` int(11) DEFAULT NULL,
  `FINISH_LEARN_TIME` int(11) DEFAULT NULL,
  `PERCENT` int(11) DEFAULT NULL,
  PRIMARY KEY (`LEARN_BOOK_SITUATION_ID`),
  KEY `FK9FBE1B8F6D58E822` (`ATTEND_LEARN_USER_ID`),
  KEY `FK9FBE1B8FFA568206` (`LEARN_PHASE_SITUATION_ID`),
  KEY `FK9FBE1B8F1EBD0A4B` (`LEARN_PHASE_BOOK_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_history`;
CREATE TABLE `ex_learn_history` (
  `LEARN_HISTORY_ID` varchar(39) NOT NULL,
  `LEARN_PHASE_SITUATION_ID` varchar(39) DEFAULT NULL,
  `LEARN_BOOK_SITUATION_ID` varchar(39) DEFAULT NULL,
  `ENTER_TIME` datetime DEFAULT NULL,
  `EXIT_TIME` datetime DEFAULT NULL,
  `LEARN_TIME` int(11) DEFAULT NULL,
  PRIMARY KEY (`LEARN_HISTORY_ID`),
  KEY `FK43A47F0DB8120144` (`LEARN_BOOK_SITUATION_ID`),
  KEY `FK43A47F0DFA568206` (`LEARN_PHASE_SITUATION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `ex_learn_phase_situation`;
CREATE TABLE `ex_learn_phase_situation` (
  `LEARN_PHASE_SITUATION_ID` varchar(39) NOT NULL,
  `ATTEND_LEARN_PHASE_ID` varchar(39) DEFAULT NULL,
  `ATTEND_LEARN_USER_ID` varchar(39) DEFAULT NULL,
  `LEARN_STATUS` int(11) DEFAULT NULL,
  `TEST_STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`LEARN_PHASE_SITUATION_ID`),
  KEY `FKEF2A11B36D58E822` (`ATTEND_LEARN_USER_ID`),
  KEY `FKEF2A11B3E96B7692` (`ATTEND_LEARN_PHASE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;






