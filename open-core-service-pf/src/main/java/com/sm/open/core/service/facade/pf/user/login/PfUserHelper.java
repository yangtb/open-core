package com.sm.open.core.service.facade.pf.user.login;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.facade.model.param.pf.user.register.UserRegisterParam;
import com.sm.open.core.model.dto.pf.user.login.RegisterDto;
import com.sm.open.core.model.entity.SysOrg;

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
     * @return
     */
    public static SysOrg bulidOrgParam(UserRegisterParam param) {
        SysOrg sysOrg = new SysOrg();
        sysOrg.setName(param.getOrgName());
        sysOrg.setPhone(param.getPhone());
        sysOrg.setEmail(param.getEmail());
        sysOrg.setFgPlat(YesOrNoNum.NO.getCode());
        sysOrg.setFgActive(YesOrNoNum.NO.getCode());
        sysOrg.setCreator(param.getOrgName());
        sysOrg.setOperator(param.getOrgName());
        return sysOrg;
    }

    /**
     * 构建用户注册参数
     *
     * @param param
     * @return
     */
    public static RegisterDto bulidRegisterParam(UserRegisterParam param) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername(param.getUsername());
        registerDto.setPassword(param.getPassword());
        registerDto.setEmail(param.getEmail());
        registerDto.setPhoneNo(param.getPhone());
        registerDto.setOperator(param.getOrgName());
        registerDto.setEnabled(false);
        return registerDto;
    }
}
