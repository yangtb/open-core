package com.sm.open.core.service.facade.pf.user.login;

import com.sm.open.care.core.enums.YesOrNo;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.user.PfUserParam;
import com.sm.open.core.facade.model.param.pf.user.login.RegisterParam;
import com.sm.open.core.facade.model.param.pf.user.login.UpdatePswParam;
import com.sm.open.core.facade.model.result.pf.common.auth.UserInfoResult;
import com.sm.open.core.facade.model.result.pf.user.login.PfUsersResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.user.login.PfUserFacade;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.dto.pf.user.login.RegisterDto;
import com.sm.open.core.model.dto.pf.user.login.UpdatePswDto;
import com.sm.open.core.model.entity.UserInfo;
import com.sm.open.core.service.service.pf.system.email.PfEmailService;
import com.sm.open.core.service.service.pf.user.login.PfUserService;
import com.sm.open.core.service.service.pf.user.security.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfUserFacade")
public class PfUserFacadeImpl implements PfUserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfUserFacadeImpl.class);

    @Resource
    private PfUserService pfUserService;
    @Resource
    private PfEmailService pfEmailService;
    @Resource
    private AuthorityService authorityService;

    /**
     * 发送邮件开关
     */
    @Value(value = "${reset.password.email.switch}")
    private final String resetPswSwitch = "N";

    @Override
    public PfPageResult<PfUsersResult> listUsers(PfUserParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfUserDto pfUserDto = BeanUtil.convert(param, PfUserDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfUserService.countUsers(pfUserDto),
                    BeanUtil.convertList(pfUserService.listUsers(pfUserDto), PfUsersResult.class));
        } catch (Exception e) {
            return PfResultFactory.initPageResultWithError(
                    PfUserConstant.SELECT_PAGE_USER_LIST_ERROR, PfUserConstant.SELECT_PAGE_USER_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> saveUser(RegisterParam param) {
        try {
            if (pfUserService.isExistUser(param.getUsername())) {
                throw new BizRuntimeException(PfUserConstant.ADD_USER_ISEXIST, PfUserConstant.ADD_USER_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(pfUserService.saveUser(BeanUtil.convert(param, RegisterDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-saveUser】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-saveUser-error】新增用户失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.ADD_USER_FAILED, PfUserConstant.ADD_USER_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateUser(RegisterParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfUserService.updateUser(BeanUtil.convert(param, RegisterDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-updateUser】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-updateUser-error】编辑用户失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.EDIT_USER_FAILED, PfUserConstant.EDIT_USER_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delUser(List<Long> users) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfUserService.delUser(users));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-delUser】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-delUser-error】批量删除用户失败，param={}", users, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.DEL_USER_FAILED, PfUserConstant.DEL_USER_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> freezeUser(List<Long> users) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfUserService.freezeUser(users));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-freezeUser】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-freezeUser-error】批量冻结用户失败，param={}", users, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.FREEZE_USER_FAILED, PfUserConstant.FREEZE_USER_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updatePsw(UpdatePswParam pswParam) {
        try {
            // 查询用户密码、盐值等
            UserInfo userInfo = pfUserService.selectUser(pswParam.getUserName());
            if (!pfUserService.matchPassword(pswParam.getOldPassword(), userInfo.getSalt(), userInfo.getPassword())) {
                throw new BizRuntimeException(PfUserConstant.OLD_PASSWORD_ERROR, PfUserConstant.OLD_PASSWORD_ERROR_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(pfUserService.updatePsw(BeanUtil.convert(pswParam, UpdatePswDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-updatePsw】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-updatePsw-error】修改密码失败，param={}", pswParam.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.UPDATE_PSW_FAILED, PfUserConstant.UPDATE_PSW_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> resetPsw(RegisterParam param) {
        try {
            UpdatePswDto updatePswDto = new UpdatePswDto();
            updatePswDto.setUserId(param.getUserId());
            updatePswDto.setNewPassword(param.getPassword());
            if (resetPswSwitch.equals(YesOrNo.YES.getCode())) {
                pfEmailService.sendEmail();
            }
            return ResultFactory.initCommonResultWithSuccess(pfUserService.updatePsw(updatePswDto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-resetPsw】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-resetPsw-error】重置密码失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.RESET_PSW_FAILED, PfUserConstant.RESET_PSW_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<UserInfoResult> selectUser(String userName) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfUserService.selectUser(userName), UserInfoResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-selectUser】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-selectUser-error】根据用户获取用户信息失败，userName:" + userName, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.SELECT_USER_FAILED, PfUserConstant.SELECT_USER_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<List<String>> findAuthoritiesByUserId(Long userId) {
        try {
            return ResultFactory.initCommonResultWithSuccess(authorityService.findAuthoritiesByUserId(userId));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUserFacadeImpl-findAuthoritiesByUserId】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUserFacadeImpl-findAuthoritiesByUserId-error】根据用户ID查找用户的权限编码集合失败，usId:" + userId, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUserConstant.FIND_AUTHORITIES_FAILED, PfUserConstant.FIND_AUTHORITIES_FAILED_MSG));
        }
    }
}
