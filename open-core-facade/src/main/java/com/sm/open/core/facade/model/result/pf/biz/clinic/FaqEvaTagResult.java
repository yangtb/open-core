package com.sm.open.core.facade.model.result.pf.biz.clinic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_评估标签
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqEvaTagResult implements Serializable {

    private static final long serialVersionUID = 1541404298848L;


    /**
     * 主键
     * 评估标签id
     */
    private Long idEvaTag;

    /**
     * 病例id
     */
    private Long idMedicalrec;

    /**
     * 病例名称
     */
    private String caseName;

    /**
     * 模板标签id
     */
    private Long idTag;

    /**
     * 评估组件案例id
     */
    private Long idEvaCase;

}
