package com.sm.open.core.facade.model.param.pf.user.role;

import com.sm.open.core.facade.model.param.pf.user.SysRoleMenuParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfRoleMenuParam implements Serializable {

    private static final long serialVersionUID = 5703704673324231853L;

    private List<SysRoleMenuParam> roleMenus;

}
