package com.sm.open.core.facade.model.param.pf.biz.kb.part;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfPartCommonParam extends PfPageParam implements Serializable {

    /**
     * 病例组件案例id
     */
    private Long idMedCase;

    /**
     * 搜索关键字
     */
    private String keyword;
}
