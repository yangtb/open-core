package com.sm.open.core.facade.model.result.pf.biz.kb.part;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_评估指南
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedCaseGuideResult implements Serializable {

    private static final long serialVersionUID = 8818298620190875763L;

    /**
     * 主键
     * 病例组件案例id
     */
    private Long idMedCase;

    /**
     * 评估指南
     */
    private String guideContent;


    /**
     * 评估指南说明utl
     */
    private String guideNotesUrl;

}
