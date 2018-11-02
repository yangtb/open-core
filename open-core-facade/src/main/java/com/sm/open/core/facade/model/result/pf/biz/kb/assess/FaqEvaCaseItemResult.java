package com.sm.open.core.facade.model.result.pf.biz.kb.assess;

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
public class FaqEvaCaseItemResult implements Serializable {

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
     * 数量上限
     */
    private Integer quaUpper;

}