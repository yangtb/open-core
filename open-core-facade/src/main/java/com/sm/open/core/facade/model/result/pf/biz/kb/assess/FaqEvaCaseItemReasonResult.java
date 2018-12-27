package com.sm.open.core.facade.model.result.pf.biz.kb.assess;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqEvaCaseItemReasonResult implements Serializable {

    private static final long serialVersionUID = 1540812307781L;


    /**
     * 主键
     * 明细id
     */
    private Long idEvaCaseItemList;

    /**
     * 病历评估项id
     */
    private Long idEvaCaseItem;

    /**
     * 1. 问诊 2. 检查 3. 检验
     */
    private String sdEvaEffciency;

    /**
     * 确诊理由_问诊
     */
    private Long idInques;

    /**
     * 确诊理由_检查
     */
    private Long idBody;

    /**
     * 确诊理由_检验
     */
    private Long idInspectItem;

    /**
     * 字典文本
     */
    private String desText;

    /**
     * 诊断
     */
    private Long idDie;

}
