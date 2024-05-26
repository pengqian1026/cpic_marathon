package com.cpic.mia.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 稽核对话信息记录表
 * @TableName mia_cnsv_info
 */
@TableName(value ="mia_cnsv_info")
@Data
public class MiaCnsvInfoPO implements Serializable {
    /**
     * 主键id
     */
    @TableId
    private String keyId;

    /**
     * 对话id
     */
    private String cnsvId;

    /**
     * 当前对话序号
     */
    private Integer cnsvSeqNo;

    /**
     * 对话角色
     */
    private String role;

    /**
     * 对话内容
     */
    private String content;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}