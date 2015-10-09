/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp-test-platform-171
Target Host: localhost
Target Database: bmp-test-platform-171
Date: 2013-1-6 11:27:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sys_dictionary_field` VALUES ('bmp_secrecy_levle_section', 'secrecy_level_section', '涉密等级（要害部门）', '0', '3', 'bmp');

INSERT INTO `sys_dictionary_option` VALUES ('bmp_secrecy_levle_section_1', '核心', '1', '3', '1', 'bmp_secrecy_levle_section');
INSERT INTO `sys_dictionary_option` VALUES ('bmp_secrecy_levle_section_2', '重要', '2', '2', '1', 'bmp_secrecy_levle_section');
INSERT INTO `sys_dictionary_option` VALUES ('bmp_secrecy_levle_section_3', '一般', '3', '1', '1', 'bmp_secrecy_levle_section');
