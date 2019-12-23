package com.sm.open.core.facade.model.param.pf.biz.check;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCheckQuestionParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;

    /**
     * 部位ID
     */
    private Long idBody;

    /**
     * 部位分类ID
     */
    private Long idBodyCa;

    /**
     * 部位描述
     */
    private String desBody;

    /**
     * 搜索关键字
     */
    private String keywords;

    /**
     * 扩展id
     */
    private Long extId;

    private Long idMedCase;
}
