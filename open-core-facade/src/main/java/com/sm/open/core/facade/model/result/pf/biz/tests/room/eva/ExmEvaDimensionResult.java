package com.sm.open.core.facade.model.result.pf.biz.tests.room.eva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 临床模拟_评估维度_分值
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmEvaDimensionResult implements Serializable {

    private static final long serialVersionUID = 1542552394382L;


    /**
     * 主键
     * 病历评估维度ID
     */
    private Long idTestexecResultDimension;

    /**
     * 病历结果ID
     */
    private Long idTestexecResult;

    /**
     * 评估维度ID
     */
    private Long idDimemsion;

    /**
     * 评估分值
     */
    private BigDecimal scoreDimemsion;

    /**
     * 评估维度权重
     */
    private BigDecimal weightDimemsion;

    /**
     * 评估维度加权得分
     */
    private BigDecimal weightScoreDimemsion;

    private BigDecimal scoreMax;

    private BigDecimal weightScoreMax;

    /**
     * 平均得分
     */
    private BigDecimal avgScore;

}
