package com.sm.open.core.facade.model.result.pf.biz.kb.part;

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
public class FaqMedCaseBodyResult implements Serializable {

    private static final long serialVersionUID = 1540450802266L;

    /**
     * 主键
     * 病例组件案例id
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
