package com.sm.open.core.facade.model.result.pf.biz.tests.room.eva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfEvaExecResult extends ExmEvaDimensionResult implements Serializable {

    /**
     * 评估项
     */
    private String pgItem;

    /**
     * 拟诊名称
     */
    private String nzName;

    /**
     * 病例结果ID
     */
    private Long parDimemsion;

    private String fgSystemAlgorithm;

}
