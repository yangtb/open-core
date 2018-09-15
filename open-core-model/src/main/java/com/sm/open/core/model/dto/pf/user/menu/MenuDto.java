package com.sm.open.core.model.dto.pf.user.menu;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class MenuDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1699729232949638650L;

    private Long        id;         // 菜单ID
    private String      name;       // 菜单名称
    private Integer     level;      // 菜单级别
    private String      status;     // 菜单状态

}
