package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 */
@Setter
@Getter
@ToString
public class SysRole implements Serializable {

    private static final long serialVersionUID = -4292388147414854049L;

    private Long        roleId;         // 角色ID
    private String      name;           // 角色名称
    private String      resume;         // 角色描述
    private int         state;          // 是否有效(0-有效 1-无效)
    private String      isDeleted;      // 删除标示，N未删除 Y-已删除
    private String      operator;       // 创建人ID
    private Date        gmtCreate;      // 创建时间
    private Date        gmtUpdate;      // 更新时间

}
