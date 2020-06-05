package com.sm.open.core.facade.model.param.pf.common;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCommonSearchParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;

    /**
     * 问题内容
     */
    private String keywords;
}
