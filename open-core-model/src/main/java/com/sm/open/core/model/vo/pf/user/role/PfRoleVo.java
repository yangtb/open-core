package com.sm.open.core.model.vo.pf.user.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class PfRoleVo implements Serializable {

    private static final long serialVersionUID = 3352454116018853943L;

    private Long        roleId;         // 角色ID
    private String      code;           // 角色编码
    private String      name;           // 角色名称
    private String      resume;         // 角色描述
    private int         state;          // 是否有效(0-有效 1-无效)
    private String      operator;       // 创建人
    private Date        gmtCreate;      // 创建时间
    private boolean     checked;        // 是否拥有某权限

}
