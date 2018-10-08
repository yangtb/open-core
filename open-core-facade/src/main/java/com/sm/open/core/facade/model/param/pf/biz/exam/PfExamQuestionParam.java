package com.sm.open.core.facade.model.param.pf.biz.exam;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfExamQuestionParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;


    /**
     * 检验分类ID
     */
    private Long idInspect;
    /**
     * 项目ID
     */
    private Long idInspectItem;

    /**
     * 项目名称
     */
    private String naItem;

}
