package com.sm.open.core.model.dto.pf.user.role;

import com.sm.open.core.model.entity.SysRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfRoleListDto implements Serializable {

    private static final long serialVersionUID = 5703704673324231853L;

    private List<SysRole> roles;
}
