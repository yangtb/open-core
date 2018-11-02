package com.sm.open.core.facade.model.result.pf.biz.tests.plan;

import com.sm.open.core.facade.model.result.pf.biz.kb.casehistory.FaqMedicalrecResult;
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
public class ExmTestplanMedicalrecResult extends FaqMedicalrecResult implements Serializable {

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
