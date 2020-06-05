package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单按钮表
 */
@Setter
@Getter
@ToString
public class SysMenuButton implements Serializable {

    private static final long serialVersionUID = -986150631800387874L;

    private Long        menuButtonId;   // 菜单按钮id
    private Long        menuId;         // 菜单ID
    private String      buttonCode;     // 按钮代码
    private String      buttonName;     // 按钮名称
    private Date        gmtCreate;      // 创建时间
    private Date        gmtUpdate;      // 更新时间
    private String      isDeleted;      // 删除标示，N未删除 Y-已删除


}
