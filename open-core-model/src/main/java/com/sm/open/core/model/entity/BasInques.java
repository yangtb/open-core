package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础_问诊问题
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasInques implements Serializable {

    private static final long serialVersionUID = 1538569588154L;


    /**
     * 主键
     * 问题ID
     */
    private Long idInques;

    /**
     * 问题内容
     */
    private String desInques;

    /**
     * 问诊分类ID
     */
    private Long idInquesCa;

    /**
     * 问诊分类
     */
    private String idInquesCaText;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;

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


}
