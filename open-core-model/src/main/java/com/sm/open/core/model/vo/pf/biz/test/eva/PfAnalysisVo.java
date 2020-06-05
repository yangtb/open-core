package com.sm.open.core.model.vo.pf.biz.test.eva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfAnalysisVo implements Serializable {

    private static final long serialVersionUID = -3658629749787443176L;

    /**
     * 阶段
     */
    private Integer sdEvaEffciency;

    /**
     * id
     */
    private Long idEvaCaseItem;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 疾病项目
     */
    private String idDie;

    /**
     * 原因id
     */
    private String idReason;

    /**
     * 标识
     */
    private Integer flag;

}
