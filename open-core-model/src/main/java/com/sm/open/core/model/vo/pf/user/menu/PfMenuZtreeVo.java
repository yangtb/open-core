package com.sm.open.core.model.vo.pf.user.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfMenuZtreeVo implements Serializable {

    private static final long serialVersionUID = -4901737093643963963L;

    /**
     * 菜单id
     */
    private Long menuId;
    private String id;
    private String pId;
    private String name;
    private boolean checked;
    private boolean open;

    private int level;
    private String position;

}
