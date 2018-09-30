package com.sm.open.core.model.vo.pf.biz.disease;

import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfDiseaseZtreeVo extends PfCommonZtreeVo implements Serializable {

    private static final long serialVersionUID = -4901737093643963963L;

    /**
     * 疾病目录ID
     */
    private Long        idDieclass;

    /**
     * 激活标志
     */
    private String fgActive;

}
