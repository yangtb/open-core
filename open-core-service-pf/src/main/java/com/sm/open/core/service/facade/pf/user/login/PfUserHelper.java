package com.sm.open.core.service.facade.pf.user.login;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.utils.DateUtil;
import com.sm.open.core.facade.model.param.pf.user.register.UserRegisterParam;
import com.sm.open.core.model.dto.pf.user.login.RegisterDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.model.vo.pf.user.role.PfRoleVo;

import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName: PfUserHelper
 * @Description: 帮助类
 * @Author yangtongbin
 * @Date 2018/9/22 16:38
 */
public class PfUserHelper {

    /**
     * 构建机构参数
     *
     * @param param
     * @param orgExpiryDay 机构有效期（天）
     * @return
     */
    public static SysOrg bulidOrgParam(UserRegisterParam param, int orgExpiryDay) {
        SysOrg sysOrg = new SysOrg();
        sysOrg.setName(param.getOrgName());
        sysOrg.setPhone(param.getPhone());
        sysOrg.setEmail(param.getEmail());
        sysOrg.setFgPlat(YesOrNoNum.NO.getCode());
        if (orgExpiryDay > 0) {
            sysOrg.setFgActive(YesOrNoNum.YES.getCode());
            sysOrg.setGmtValid(DateUtil.date2Str(DateUtil.addDate(new Date(), orgExpiryDay), DateUtil.FORMAT_DATE));
        } else {
            sysOrg.setFgActive(YesOrNoNum.NO.getCode());
        }
        sysOrg.setCreator(param.getOrgName());
        sysOrg.setOperator(param.getOrgName());
        return sysOrg;
    }

    /**
     * 构建用户注册参数
     *
     * @param param
     * @param pfRoleVo 角色信息
     * @param idOrg    机构id
     * @return
     */
    public static RegisterDto bulidRegisterParam(UserRegisterParam param,
                                                 PfRoleVo pfRoleVo,
                                                 Long idOrg) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername(param.getUsername());
        registerDto.setRealName(param.getOrgName());
        registerDto.setPassword(param.getPassword());
        registerDto.setEmail(param.getEmail());
        registerDto.setPhoneNo(param.getPhone());
        registerDto.setOperator(param.getOrgName());
        registerDto.setIdOrg(idOrg);
        registerDto.setEnabled(true);
        if (pfRoleVo != null) {
            registerDto.setRoles(Arrays.asList(pfRoleVo.getRoleId()));
        }
        return registerDto;
    }
}
