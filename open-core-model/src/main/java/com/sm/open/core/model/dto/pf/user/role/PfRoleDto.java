package com.sm.open.core.model.dto.pf.user.role;

import com.sm.open.core.model.param.PageParam;
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
public class PfRoleDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -2349611519982619401L;

    private int         state;
    private String      conditionValue;

}
