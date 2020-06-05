package com.sm.open.core.facade.model.result.pf.user.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfBaseMenuResult implements Serializable {

    private static final long serialVersionUID = 2012402401357939421L;

    private Long            menuId;     // 菜单ID
    private String          url;        // 菜单url
    private String          name;       // 菜单名称
    private String          img;        // 图片
    private String          position;   // 菜单位置left=左边，top=顶部

}
