package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 */
@Setter
@Getter
@ToString
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -7847458250447175878L;

    private Long        menuId;     // 菜单ID
    private Integer     level;      // 菜单等级从1开始
    private String      url;        // 菜单url
    private String      name;       // 菜单名称
    private Long        parentId;   // 父菜单id
    private int         sort;       // 排序(越大越前)
    private int         disable;    // 是否有效(0-有效 1-无效)
    private String      isDeleted;  // 删除标示，N未删除 Y-已删除
    private Date        gmtCreate;  // 创建时间
    private Date        gmtModify;  // 更新时间
    private String      img;        // 图片

}
