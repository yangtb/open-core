package com.sm.open.core.model.vo.pf.user.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfBaseMenuVo implements Serializable {

    private static final long serialVersionUID = 1582588956094352246L;

    private Long            menuId;     // 菜单ID
    private String          url;        // 菜单url
    private String          name;       // 菜单名称
    private String          img;        // 图片

}
