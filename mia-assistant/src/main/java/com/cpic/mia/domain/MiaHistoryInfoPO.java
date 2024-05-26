package com.cpic.mia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 医院病例信息表
 * @TableName mia_history_info
 */
@TableName(value ="mia_history_info")
@Data
public class MiaHistoryInfoPO implements Serializable {
    /**
     * 历史记录id
     */
    @TableId
    private String hisId;

    /**
     * 病人id
     */
    private String patientId;

    /**
     * 住院号
     */
    private String zyh;

    /**
     * 目录名称
     */
    private String pCategory;

    /**
     * 项目日期
     */
    private String usageDate;

    /**
     * 科室id
     */
    private String deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 项目id
     */
    private String itemIdHosp;

    /**
     * 项目名称
     */
    private String itemNameHosp;

    /**
     * 药品分类
     */
    private String drugSpec;

    /**
     * 计量单位
     */
    private String packageUnit;

    /**
     * 单位价格
     */
    private String unitPrice;

    /**
     * 数量
     */
    private String num;

    /**
     * 总金额
     */
    private String costSum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}