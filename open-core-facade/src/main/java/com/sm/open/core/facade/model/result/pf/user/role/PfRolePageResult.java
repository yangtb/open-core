package com.sm.open.core.facade.model.result.pf.user.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfRolePageResult
 * @Description: 角色分页
 * @Author yangtongbin
 * @Date 2018/9/14 23:35
 */
@Setter
@Getter
@ToString
public class PfRolePageResult implements Serializable {

    private Long count;
    private List<PfRoleResult> pfRoleResult;
}
