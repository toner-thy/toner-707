/*

MySQL Data Transfer

Source Host: localhost

Source Database: bmp-test-platform-171

Target Host: localhost

Target Database: bmp-test-platform-171

Date: 2012-12-26 9:22:16

*/


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------

-- Table structure for bm_secrecy_person

-- ----------------------------

DROP TABLE IF EXISTS `bm_secrecy_person`;

CREATE TABLE `bm_secrecy_person` (
  `secrecy_person_id` varchar(32) NOT NULL,
  `office_duty` varchar(20) DEFAULT NULL COMMENT '行政职务',
  `first_work_date` datetime DEFAULT NULL COMMENT '参加工作日期',
  `in_now_post_time` datetime DEFAULT NULL COMMENT '进入现岗位日期',
  `foreign_language` varchar(20) DEFAULT NULL COMMENT '掌握外语',
  `familiar_grade` varchar(20) DEFAULT NULL COMMENT '熟悉程度',
  `np_police_station` varchar(100) DEFAULT NULL COMMENT '户籍派出所',
  `secrecy_person_level` int(11) DEFAULT NULL COMMENT '涉密程度',
  `secrecy_work_date` int(11) DEFAULT NULL COMMENT '涉密工作年限',
  `top_secrecy_level` int(11) DEFAULT NULL COMMENT '接触最高密级',
  `sign_secrecy_treaty` int(11) DEFAULT NULL COMMENT '是否签订保密协议',
  `secrecy_treaty_date` datetime DEFAULT NULL COMMENT '签订保密协议日期',
  `fill_date` datetime DEFAULT NULL COMMENT '填表日期',
  `trans_out_date` datetime DEFAULT NULL COMMENT '调离日期',
  `political_status` varchar(50) DEFAULT NULL,
  `has_qualif_card` varchar(50) DEFAULT NULL COMMENT '上岗证编号',
  `resume` varchar(2000) DEFAULT NULL COMMENT '个人简介',
  `other_info` varchar(500) DEFAULT NULL COMMENT '其他说明情况',
  `sec_work_info` varchar(500) DEFAULT NULL COMMENT '从事涉密工作情况',
  `organ_check_opinion` varchar(500) DEFAULT NULL COMMENT '单位审察意见',
  `organ_id` varchar(39) DEFAULT NULL COMMENT '所在单位ID',
  `department_id` varchar(39) DEFAULT NULL COMMENT '所在部门ID',
  `office_phone` varchar(50) DEFAULT NULL,
  `post_train_time` datetime DEFAULT NULL COMMENT '持证上岗时间',
  `sign_dimission_treaty` int(11) DEFAULT NULL COMMENT '是否签订离岗保密协议书',
  `post` varchar(40) DEFAULT NULL COMMENT '岗位',
  `secrecy_status` int(11) DEFAULT NULL COMMENT '涉密状态',
  `sec_sign_book_time` datetime DEFAULT NULL COMMENT '签订保密书时间',
  `sec_uppost_time` datetime DEFAULT NULL COMMENT '取得上岗证书时间',
  `create_person` varchar(39) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_person` varchar(39) DEFAULT NULL COMMENT '修改用户',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `PERSON_ID` varchar(39) DEFAULT NULL COMMENT '用户ID',
  `out_secrecy_years` int(11) DEFAULT NULL COMMENT '脱密期年限',
  PRIMARY KEY (`secrecy_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for bm_secrecy_person_receive
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_person_receive`;
CREATE TABLE `bm_secrecy_person_receive` (
  `secrecy_person_id` varchar(32) NOT NULL COMMENT '人员ID',
  `RECEIVE_ORGAN_ID` varchar(39) NOT NULL COMMENT '接收单位主键ID',
  PRIMARY KEY (`secrecy_person_id`,`RECEIVE_ORGAN_ID`),
  KEY `FKEACF7792CDB7450F` (`secrecy_person_id`),
  CONSTRAINT `bm_secrecy_person_receive_ibfk_1` FOREIGN KEY (`secrecy_person_id`) REFERENCES `bm_secrecy_person` (`secrecy_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;