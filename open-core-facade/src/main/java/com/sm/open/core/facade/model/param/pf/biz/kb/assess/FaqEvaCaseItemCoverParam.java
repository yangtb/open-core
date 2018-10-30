package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

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
public class FaqEvaCaseItemCoverParam implements Serializable {

    private static final long serialVersionUID = 1540812323124L;


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
     * 2. 检查 3. 检验
     */
    private String sdEvaCover;

    /**
     * 鉴定检查疾病
     */
    private Long idDie;

    /**
     * 鉴定检查_问诊
     */
    private String idInques;

    /**
     * 鉴定检查_检查
     */
    private Long idBody;

    /**
     * 鉴定检查_检验
     */
    private Long idInspectItem;


}
