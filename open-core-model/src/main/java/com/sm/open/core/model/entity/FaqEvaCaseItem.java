package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_病历评估_评估项
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqEvaCaseItem implements Serializable {

    private static final long serialVersionUID = 1540712685163L;


    /**
     * 主键
     * 病历评估项id
     */
    private Long idEvaCaseItem;

    /**
     * 评估组件案例id
     */
    private Long idEvaCase;

    /**
     * 组件id
     */
    private String cdEvaAsse;

    /**
     * 评估项名
     */
    private String itemName;

    /**
     * 评估项分值
     */
    private Integer scoreEva;

    /**
     * 评估阶段
     */
    private String sdEva;

    /**
     * 拟诊id
     */
    private Long idDie;

    /**
     * 疾病text
     */
    private String idDieText;

    /**
     * 数量上限
     */
    private Integer quaUpper;

}
