package com.sm.open.core.facade.model.result.pf.biz.clinic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_病例标签
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedTagResult implements Serializable {

    private static final long serialVersionUID = 1541404303662L;

    /**
     * 主键
     * 病例标签id
     */
    private Long idMedTag;

    /**
     * 所属病例
     */
    private Long idMedicalrec;

    /**
     * 所属病例名称
     */
    private String caseName;

    /**
     * 标签id
     */
    private Long idTag;

    /**
     * 病例组件案例id
     */
    private Long idMedCase;

}
