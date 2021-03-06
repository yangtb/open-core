package com.sm.open.core.facade.model.param.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试执行_病例结果_医嘱_长期用药
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmMedResultOrderLogDrugsParam implements Serializable {

    private static final long serialVersionUID = 1542159815889L;

    /**
     * 主键
     */
    private Long idOrderLongDrugs;

    /**
     * 病例结果医嘱ID
     */
    private Long idTestexecResultOrder;

    /**
     * 长期用药
     */
    private Long idLongDrugs;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;


}
