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
public class FaqEvaCaseItemMustResult implements Serializable {

    private static final long serialVersionUID = 1540812314287L;


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
     * 1 问诊 2 检查 3 检验
     */
    private String sdEvaMust;

    /**
     * 覆盖检查_问诊
     */
    private Long idInques;

    /**
     * 覆盖检查_检查
     */
    private Long idBody;

    /**
     * 覆盖检查_检验
     */
    private Long idInspectItem;

    /**
     * 字典文本
     */
    private String desText;

}
