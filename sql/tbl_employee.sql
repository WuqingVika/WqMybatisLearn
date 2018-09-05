/*
Navicat MySQL Data Transfer

Source Server         : test1
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : wqmybaits

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-08-26 17:04:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE "tbl_employee" (
  "id" int(11) NOT NULL AUTO_INCREMENT,
  "last_name" varchar(255) DEFAULT NULL,
  "gender" char(1) DEFAULT NULL,
  "email" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id")
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES ('1', 'wq', '1', 'wuqing@hh.com');


--add dept_id
alter table tbl_employee add column d_id int(11);
alter table tbl_employee add CONSTRAINT fk_emp_dept  FOREIGN key (d_id)
REFERENCES tbl_dept (id)