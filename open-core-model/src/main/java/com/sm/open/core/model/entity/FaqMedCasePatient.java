package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_患者信息定义
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedCasePatient extends FaqMedTag implements Serializable {

    private static final long serialVersionUID = 1540020746319L;

    /**
     * 主键
     * 病历组件案例id
     */
    private Long idMedCase;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 1 男 2 女
     */
    private int age;

    /**
     * 患者主诉
     */
    private String complaint;

}
