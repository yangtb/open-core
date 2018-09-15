package com.sm.open.core.model.dto.pf.user.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfRoleCommonDto
 * @Description: 角色通用参数
 * @Author yangtongbin
 * @Date 2017/9/28 16:37
 */
@Setter
@Getter
@ToString
public class PfRoleCommonDto implements Serializable {

    private static final long serialVersionUID = -2956233063776652089L;

    private Long        roleId;             // 角色ID


 }
