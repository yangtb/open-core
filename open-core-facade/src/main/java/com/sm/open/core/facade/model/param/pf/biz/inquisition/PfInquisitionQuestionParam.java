package com.sm.open.core.facade.model.param.pf.biz.inquisition;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfInquisitionQuestionParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;

    /**
     * 问题ID
     */
    private Long idInques;
    /**
     * 问诊分类ID
     */
    private Long idInquesCa;

    /**
     * 问题内容
     */
    private String desInques;

    /**
     * 搜索关键字
     */
    private String keywords;
}
