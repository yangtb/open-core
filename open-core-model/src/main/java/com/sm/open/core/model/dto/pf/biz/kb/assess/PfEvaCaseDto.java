package com.sm.open.core.model.dto.pf.biz.kb.assess;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfEvaCaseDto extends PageParam implements Serializable {

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 用例名称
     */
    private String name;

    /**
     * 平台标识
     */
    private String fgPlat;

    /**
     * 评估组件编码
     */
    private String cdEvaAsse;

    /**
     * 创建人
     */
    private String creator;


}
