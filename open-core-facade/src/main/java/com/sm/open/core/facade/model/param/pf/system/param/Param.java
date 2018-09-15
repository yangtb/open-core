package com.sm.open.core.facade.model.param.pf.system.param;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Param extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7532471455664606363L;

    private String      paramName;          // 字典名称
    private String      status;             // 状态

}
