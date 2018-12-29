package com.sm.open.core.facade.model.result.pf.biz.tests.room.eva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 临床模拟_评估结果
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmEvaResultResult implements Serializable {

    private static final long serialVersionUID = 1542642009326L;


    /**
     * 主键
     * 病例结果ID
     */
    private Long idTestexecResult;

    /**
     * 加权分值
     */
    private BigDecimal scoreWeight;

    /**
     * 1 侥幸
     * 2 过早诊断但确诊正确
     * 3 合格但滥用检查
     * 4 良好
     * 5 优秀
     * 6 完美
     * isNullAble:0
     */
    private String sdTitle;

}
