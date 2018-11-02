package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试计划_关联病历
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmTestplanMedicalrec extends FaqMedicalrec implements Serializable {

    private static final long serialVersionUID = 1541156590151L;

    /**
     * 主键
     * 关联病历ID
     */
    private Long idTestplanMedicalrec;

    /**
     * 测试计划ID
     */
    private Long idTestplan;

    /**
     * 病历ID
     */
    private Long idMedicalrec;

    /**
     * 排序
     */
    private Integer sort;


}
