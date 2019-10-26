package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BasDieResult
 * @Description: 基础_疾病
 * @Author yangtongbin
 * @Date 2018/9/28
 */
@Setter
@Getter
@ToString
public class BasDie implements Serializable {

    private static final long serialVersionUID = -118794936400891949L;

    /**
     * 主键
     * 疾病ID
     */
    private Long idDie;

    /**
     * 疾病名称
     */
    private String name;

    /**
     * 疾病目录编码
     */
    private String cdDieclass;

    /**
     * 疾病目录名称
     */
    private String cdDieclassText;

    /**
     * ICD编码
     */
    private String icd;

    /**
     * 拼音助记符
     */
    private String pinyin;

    /**
     * 激活标志
     */
    private String fgActive;

    /**
     * 删除标志
     */
    private String fgValid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 疾病分类标记，如果为1 表示存储的是疾病目录，如果为2 表示存储的是疾病
     */
    private String fgDieClass;

    /**
     * 拟诊id
     */
    private Long idTestexecResultReferral;

}
