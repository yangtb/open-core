package com.sm.open.core.facade.model.param.pf.system.org;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfOrgParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -8065165575922718762L;

    private String name;
    private String fgActive;
    private String fgValid;
    /**
     * 临过期时间
     */
    private String gmtValid;
}
