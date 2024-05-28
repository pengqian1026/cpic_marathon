use coredb_dev;

DROP TABLE IF EXISTS mia_history_info;
CREATE TABLE `mia_history_info`(
`his_id` varchar(200) COMMENT '历史记录id',
`patient_id` varchar(200) COMMENT '病人id',
`zyh` varchar(200) COMMENT '住院号',
`p_category` varchar(200) COMMENT '目录名称',
`usage_date` varchar(200) COMMENT '项目日期',
`dept_id` varchar(200) COMMENT '科室id',
`dept_name` varchar(200) COMMENT '科室名称',
`item_id_hosp` varchar(200) COMMENT '项目id',
`item_name_hosp` varchar(200) COMMENT '项目名称',
`drug_spec` varchar(200) COMMENT '药品分类',
`package_unit` varchar(200) COMMENT '计量单位',
`unit_price` varchar(200) COMMENT '单位价格',
`num` varchar(200) COMMENT '数量',
`cost_sum` varchar(200) COMMENT '总金额',
PRIMARY KEY(`his_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='医院病例信息表';

DROP TABLE IF EXISTS mia_rule_info;
CREATE TABLE `mia_rule_info`(
`key_id` varchar(200) COMMENT '主键id',
`hospital_id` varchar(200) COMMENT '医院id',
`rule_code` varchar(200) COMMENT '规则编码',
`rule_name` varchar(200) COMMENT '规则名称',
`rule_type` varchar(200) COMMENT '规则类型',
`scope` tinyint(1) COMMENT '范围:按天周期/按住院或其他',
`itemNameHosp1` varchar(2000) COMMENT '收费项目1',
`itemNameHosp2` varchar(2000) COMMENT '收费项目2',
`num` bigint COMMENT '项目数量/次数',
PRIMARY KEY(`key_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='稽核规则信息表';


DROP TABLE IF EXISTS mia_cnsv_info;
CREATE TABLE `mia_cnsv_info`(
`key_id` varchar(200) COMMENT '主键id',
`cnsv_id` varchar(200) COMMENT '对话id',
`cnsv_seq_no` int COMMENT '当前对话序号',
`role` varchar(50) COMMENT '对话角色',
`content` text COMMENT '对话内容',
`question` text COMMENT '问题',
`answer` text COMMENT '答案',
PRIMARY KEY(`key_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='稽核对话信息记录表';