package com.sm.open.core.facade.pf.user.login;

import com.sm.open.core.facade.model.param.pf.user.PfUserParam;
import com.sm.open.core.facade.model.param.pf.user.login.RegisterParam;
import com.sm.open.core.facade.model.param.pf.user.login.UpdatePswParam;
import com.sm.open.core.facade.model.param.pf.user.register.UserRegisterParam;
import com.sm.open.core.facade.model.result.pf.common.auth.UserInfoResult;
import com.sm.open.core.facade.model.result.pf.user.login.PfUsersResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfUserFacade
 * @Description: 用户模块
 * @Author yangtongbin
 * @Date 2017/9/29 14:55
 */
public interface PfUserFacade {

    /**
     * 用户列表
     *
     * @param param
     * @return
     */
    PfPageResult<PfUsersResult> listUsers(PfUserParam param);

    /**
     * 新增用户
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveUser(RegisterParam param);

    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateUser(RegisterParam param);

    /**
     * 删除用户
     *
     * @param users 用户ID
     * @return
     */
    CommonResult<Boolean> delUser(List<Long> users);

    /**
     * 删除用户
     *
     * @param users 用户ID
     * @return
     */
    CommonResult<Boolean> freezeUser(List<Long> users);

    /**
     * 修改密码
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updatePsw(UpdatePswParam param);

    /**
     * 密码重置
     *
     * @return
     */
    CommonResult<Boolean> resetPsw(RegisterParam param);

    /**
     * 根据用户获取用户信息
     *
     * @param userName
     * @return
     */
    CommonResult<UserInfoResult> selectUser(String userName);

    /**
     * 根据用户ID查找用户的权限编码集合
     *
     * @param userId   用户id
     * @param roleType 角色类型
     * @return
     */
    CommonResult<List<String>> findAuthoritiesByUserId(Long userId, String roleType);

    /**
     * 用户注册
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> registerUser(UserRegisterParam param);

    /**
     * 发送邮件验证码
     *
     * @param email 邮箱
     * @return
     */
    CommonResult<Boolean> sendRegisterEmailVcode(String email, Long userId);
}
