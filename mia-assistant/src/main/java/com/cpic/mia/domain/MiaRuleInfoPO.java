package com.cpic.mia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 稽核规则信息表
 * @TableName mia_rule_info
 */
@TableName(value ="mia_rule_info")
@Data
public class MiaRuleInfoPO implements Serializable {
    /**
     * 主键id
     */
    @TableId
    private String keyId;

    /**
     * 医院id
     */
    private String hospitalId;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则类型
     */
    private String ruleType;

    /**
     * 范围:按天周期/按住院或其他
     */
    private Integer scope;

    /**
     * 收费项目1
     */
    private String itemnamehosp1;

    /**
     * 收费项目2
     */
    private String itemnamehosp2;

    /**
     * 项目数量/次数
     */
    private Long num;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}