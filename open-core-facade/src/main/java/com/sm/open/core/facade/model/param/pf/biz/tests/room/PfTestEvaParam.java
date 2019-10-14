package com.sm.open.core.facade.model.param.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestEvaParam implements Serializable {

    private static final long serialVersionUID = -1707262746906227797L;


    /**
     * 病例结果ID
     */
    private Long idTestexecResult;

    /**
     * 病例评估维度ID
     */
    private Long idTestexecResultDimension;

    /**
     * 病例id
     */
    private Long idMedicalrec;

    /**
     * 疾病字符串
     */
    private String idDieStr;

    /**
     * 类型 ：1=确诊项 2=排除拟诊项
     */
    private Integer type;

    /**
     * 思维导图类型 3=鉴别诊断
     */
    private Integer chartType;
}
