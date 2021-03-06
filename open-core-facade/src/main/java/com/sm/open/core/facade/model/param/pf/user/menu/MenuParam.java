package com.sm.open.core.facade.model.param.pf.user.menu;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class MenuParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = 1699729232949638650L;

    /**
     * 菜单ID
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 菜单状态
     */
    private String status;
    /**
     * 父编码
     */
    private String parentCode;

}
