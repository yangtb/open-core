package com.sm.open.core.service.facade.pf.user.login;

public class PfUserConstant {

    public static final String SELECT_PAGE_USER_LIST_ERROR          = "selectPageUserListError";
    public static final String SELECT_PAGE_USER_LIST_ERROR_MSG      = "分页查询用户列表失败";

    public static final String ADD_USER_ISEXIST                     = "addUserIsExist";
    public static final String ADD_USER_ISEXIST_MSG                 = "该用户名已被使用";

    public static final String USER_NOT_EXIST                       = "userIsNotExist";
    public static final String USER_NOT_EXIST_MSG                   = "该用户名不存在";

    public static final String OLD_PASSWORD_ERROR                   = "oldPasswordError";
    public static final String OLD_PASSWORD_ERROR_MSG               = "原密码错误";

    public static final String ADD_USER_FAILED                      = "addUserFailed";
    public static final String ADD_USER_FAILED_MSG                  = "新增用户失败";

    public static final String EDIT_USER_FAILED                     = "editUserFailed";
    public static final String EDIT_USER_FAILED_MSG                 = "编辑用户失败";

    public static final String DEL_USER_FAILED                      = "delUserFailed";
    public static final String DEL_USER_FAILED_MSG                  = "批量删除用户失败";

    public static final String FREEZE_USER_FAILED                   = "freezeUserFailed";
    public static final String FREEZE_USER_FAILED_MSG               = "批量冻结用户失败";

    public static final String UPDATE_PSW_FAILED                    = "updatePswFailed";
    public static final String UPDATE_PSW_FAILED_MSG                = "修改密码失败";

    public static final String RESET_PSW_FAILED                     = "resetPswFailed";
    public static final String RESET_PSW_FAILED_MSG                 = "重置密码失败";

    public static final String SELECT_USER_FAILED                   = "selectUserFailed";
    public static final String SELECT_USER_FAILED_MSG               = "根据用户获取用户信息失败失败";

    public static final String FIND_AUTHORITIES_FAILED              = "findAuthoritiesByUserIdFailed";
    public static final String FIND_AUTHORITIES_FAILED_MSG          = "查找用户的权限编码集合失败";

    public static final String SEND_REGISTER_EMAIL_VCODE_FAILED     = "sendRegisterEmailVcodeFailed";
    public static final String SEND_REGISTER_EMAIL_VCODE_FAILED_MSG = "发送邮件验证码失败";

    public static final String EMAIL_VCODE_EXPIRED                  = "emailVcodeExpired";
    public static final String EMAIL_VCODE_EXPIRED_MSG              = "验证码已失效";

    public static final String ORG_ROLE_ERROR                       = "orgRoleError";
    public static final String ORG_ROLE_ERROR_MSG                   = "机构角色未配置";

}
