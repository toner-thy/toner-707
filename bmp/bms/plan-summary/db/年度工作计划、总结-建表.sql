SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS BM_ANNUAL_SUMMARY;
DROP TABLE IF EXISTS BM_ANNUAL_PLAN;




/* Create Tables */

-- 年度工作计划表
CREATE TABLE BM_ANNUAL_PLAN
(
	-- 年度工作计划 ID
	ANNUAL_PLAN_ID VARCHAR(39) NOT NULL COMMENT '年度工作计划 ID',
	-- 年度工作计划标题
	ANNUAL_PLAN_TITLE VARCHAR(200) COMMENT '年度工作计划标题',
	-- 年度
	ANNUAL_PLAN_YEAR INT COMMENT '年度',
	-- 年度工作计划内容
	ANNUAL_PLAN_CONTENT TEXT COMMENT '年度工作计划内容',
	-- 状态
	STATUS INT DEFAULT 1 COMMENT '状态',
	-- 创建人
	CREATE_PERSON VARCHAR(39) COMMENT '创建人',
	-- 创建时间
	CREATE_TIME DATETIME COMMENT '创建时间',
	-- 修改人
	MODIFY_PERSON VARCHAR(39) COMMENT '修改人',
	-- 修改时间
	MODIFY_TIME DATETIME COMMENT '修改时间',
	-- 创建单位
	CREATE_ORGAN VARCHAR(39) COMMENT '创建单位',
	PRIMARY KEY (ANNUAL_PLAN_ID)
) COMMENT = '年度工作计划表';


-- 年度工作总结表
CREATE TABLE BM_ANNUAL_SUMMARY
(
	-- 年度工作总结id
	ANNUAL_SUMMARY_ID VARCHAR(39) NOT NULL COMMENT '年度工作总结id',
	-- 年度工作总结标题
	ANNUAL_SUMMARY_TITLE VARCHAR(200) COMMENT '年度工作总结标题',
	-- 年度
	ANNUAL_SUMMARY_YEAR INT COMMENT '年度',
	-- 年度工作总结内容
	ANNUAL_SUMMARY_CONTENT TEXT COMMENT '年度工作总结内容',
	-- 年度工作计划 ID
	ANNUAL_SUMMARY_PLAN VARCHAR(39) COMMENT '年度工作计划 ID',
	-- 状态
	STATUS INT DEFAULT 1 COMMENT '状态',
	CREATE_PERSON VARCHAR(39),
	CREATE_TIME DATETIME,
	-- 39
	MODIFY_PERSON VARCHAR(39) COMMENT '修改人',
	MODIFY_TIME DATETIME,
	CREATE_ORGAN VARCHAR(39),
	PRIMARY KEY (ANNUAL_SUMMARY_ID)
) COMMENT = '年度工作总结表';



/* Create Foreign Keys */

ALTER TABLE BM_ANNUAL_SUMMARY
	ADD FOREIGN KEY (ANNUAL_SUMMARY_PLAN)
	REFERENCES BM_ANNUAL_PLAN (ANNUAL_PLAN_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



