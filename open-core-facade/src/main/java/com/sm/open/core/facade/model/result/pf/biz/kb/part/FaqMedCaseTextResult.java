package com.sm.open.core.facade.model.result.pf.biz.kb.part;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_文本
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedCaseTextResult implements Serializable {

    private static final long serialVersionUID = 1540020601377L;

    /**
     * 主键
     * 病历组件案例id
     */
    private Long idMedCase;

    /**
     * 文本内容
     */
    private String content;

}
