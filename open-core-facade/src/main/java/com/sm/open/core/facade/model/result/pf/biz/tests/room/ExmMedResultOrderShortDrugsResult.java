package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试执行_病例结果_医嘱_临时用药
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmMedResultOrderShortDrugsResult implements Serializable {

    private static final long serialVersionUID = 1542159819705L;


    /**
     * 主键
     */
    private Long idOrderShortDrugs;

    /**
     * 病例结果医嘱ID
     */
    private Long idTestexecResultOrder;

    /**
     * 临时用药
     */
    private Long idShortDrugs;

    /**
     * 临时用药txt
     */
    private String idShortDrugsText;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;

}
