package com.sm.open.core.facade.model.result.pf.biz.disease;

import com.alibaba.fastjson.annotation.JSONField;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfDiseaseZtreeResult extends PfCommonZtreeResult implements Serializable {

    private static final long serialVersionUID = -4901737093643963963L;

    /**
     * 疾病目录ID
     */
    private Long idDieclass;

    /**
     * 激活标志
     */
    private String fgActive;

    /**
     * 是否是父节点
     */
    @JSONField(name = "isParent")
    private boolean isParent;
}
