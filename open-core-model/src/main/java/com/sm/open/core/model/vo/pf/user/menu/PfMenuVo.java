package com.sm.open.core.model.vo.pf.user.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfMenuVo
 * @Description: 菜单分组
 * @Author yangtongbin
 * @Date 2017/10/2 19:34
 */
@Setter
@Getter
@ToString
public class PfMenuVo extends PfBaseMenuVo implements Serializable {

    private static final long serialVersionUID = -8884040581281729809L;

    private Long                        parentMenuId;
    private String                      parentUrl;
    private String                      parentMenuName;
    private String                      parentImg;
    private String                      parentCode;
    private List<PfBaseMenuVo>          groupList;      //  菜单list

}
