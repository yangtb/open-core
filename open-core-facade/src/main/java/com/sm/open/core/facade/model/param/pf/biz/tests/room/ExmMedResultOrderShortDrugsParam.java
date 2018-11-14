package com.sm.open.core.facade.model.param.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试执行_病历结果_医嘱_临时用药
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmMedResultOrderShortDrugsParam implements Serializable {

    private static final long serialVersionUID = 1542159819705L;


    /**
     * 主键
     */
    private Long idOrderShortDrugs;

    /**
     * 病历结果医嘱ID
     */
    private Long idTestexecResultOrder;

    /**
     * 临时用药
     */
    private Long idShortDrugs;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;

}
