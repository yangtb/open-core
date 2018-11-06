package com.sm.open.core.facade.model.param.pf.biz.clinic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_病历标签
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedTagParam implements Serializable {

    private static final long serialVersionUID = 1541404303662L;


    /**
     * 主键
     * 病历标签id
     */
    private Long idMedTag;

    /**
     * 所属病历
     */
    private Long idMedicalrec;

    /**
     * 标签id
     */
    private Long idTag;

    /**
     * 病历组件案例id
     */
    private Long idMedCase;

}
