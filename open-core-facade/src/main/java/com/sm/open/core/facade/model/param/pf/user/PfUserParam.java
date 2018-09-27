package com.sm.open.core.facade.model.param.pf.user;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfUserParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -8538601158882376369L;

    private String type;
    private String conditionValue;
    private Long idOrg;
}
