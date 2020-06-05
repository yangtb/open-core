package com.sm.open.core.facade.model.param.pf.user.role;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色表
 */
@Setter
@Getter
@ToString
public class PfRoleParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -2349611519982619401L;

    private int         state;
    private String      conditionValue;

}
