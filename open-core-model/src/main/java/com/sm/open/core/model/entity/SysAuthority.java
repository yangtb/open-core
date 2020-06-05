package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class SysAuthority implements Serializable {

    private static final long serialVersionUID = -5969072684531040902L;

    private Long        authorityId;       // 权限资源主键id
    private String      authorityName;     // 权限资源名称、代号，存储URL
    private int         authorityType;     // 权限资源类型 ，1为url资源权限，2为菜单资源权限
    private String      description;       // 资源接口描述
    private Date        gmtCreate;         // 创建时间
    private Date        gmtUpdate;         // 最后更新时间
    private String      remark;            // 接口备注


}
