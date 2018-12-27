package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_病历评估_评估项_拟诊
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqEvaCaseItemReferralParam implements Serializable {

    private static final long serialVersionUID = 1540712688727L;

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
    private String sdEvaReferral;

    /**
     * 疾病id
     */
    private Long idDie;

}
