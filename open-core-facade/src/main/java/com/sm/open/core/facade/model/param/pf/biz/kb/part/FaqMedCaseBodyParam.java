package com.sm.open.core.facade.model.param.pf.biz.kb.part;

import com.sm.open.core.facade.model.param.pf.biz.clinic.FaqMedTagParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_检查定义
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqMedCaseBodyParam extends FaqMedTagParam implements Serializable {

    private static final long serialVersionUID = 1540450802266L;

    /**
     * 主键
     * 病历组件案例id
     */
    private Long idMedCase;

    /**
     * 照片_正面
     */
    private Long idMediaFront;

    /**
     * 照片_正面path
     */
    private String frontPath;

    /**
     * 照片_背面
     */
    private Long idMediaBack;

    /**
     * 照片_背面path
     */
    private String backPath;

    /**
     * 照片_胸部
     */
    private Long idMediaChest;

    /**
     * 照片_腹部
     */
    private Long idMediaAbdomen;

    /**
     * 照片_头部
     */
    private Long idMediaHead;


}
