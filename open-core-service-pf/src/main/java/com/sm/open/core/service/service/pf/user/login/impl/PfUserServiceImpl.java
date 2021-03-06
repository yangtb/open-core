package com.sm.open.core.service.service.pf.user.login.impl;

import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.core.dal.pf.user.login.PfUserDao;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.dto.pf.user.login.RegisterDto;
import com.sm.open.core.model.dto.pf.user.login.UpdatePswDto;
import com.sm.open.core.model.entity.UserInfo;
import com.sm.open.core.model.vo.pf.user.login.PfStudentVo;
import com.sm.open.core.model.vo.pf.user.login.PfUsersVo;
import com.sm.open.core.service.service.pf.user.login.PfUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("pfUserService")
public class PfUserServiceImpl implements PfUserService {

    @Resource
    private PfUserDao pfUserDao;

    @Override
    public List<PfUsersVo> listUsers(PfUserDto dto) {
        return pfUserDao.listUsers(dto);
    }

    @Override
    public Long countUsers(PfUserDto dto) {
        return pfUserDao.countUsers();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(RegisterDto dto) {
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(dto, user);
        // 密码加密
        String salt = UUID.randomUUID().toString().replace("-", "");
        user.setSalt(salt);
        user.setPassword(genEncriptPwd(dto.getPassword(), salt));
        // 新增用户
        pfUserDao.saveUser(user);
        // 插入用户角色
        pfUserDao.saveUserRole(dto.getRoles(), user.getUserId());
        return true;
    }

    @Override
    public boolean isExistUser(String userName) {
        return pfUserDao.isExistUser(userName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(RegisterDto dto) {
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(dto, user);
        // 修改用户信息
        pfUserDao.updateUser(user);
        // 删除用户角色
        pfUserDao.delUserRole(dto.getUserId());
        // 插入用户角色
        pfUserDao.saveUserRole(dto.getRoles(), dto.getUserId());
        return true;
    }

    @Override
    public boolean delUser(PfCommonListDto dto) {
        if (!dto.isPlatOrSuper()) {
            // 校验用户机构
            List<Long> orgList = pfUserDao.selectOrgId(dto.getList()).stream()
                    .filter(idOrg -> !idOrg.equals(dto.getCurrentUserOrgId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orgList)) {
                throw new BizRuntimeException(ErrorCode.USER_AUTH_EXCEPTION, ErrorMessage.USER_AUTH_EXCEPTION_MSG);
            }
        }
        return pfUserDao.delUser(dto.getList()) >= 1 ? true : false;
    }

    @Override
    public boolean freezeUser(PfCommonListDto dto) {
        if (!dto.isPlatOrSuper()) {
            // 校验用户机构
            List<Long> orgList = pfUserDao.selectOrgId(dto.getList()).stream()
                    .filter(idOrg -> !idOrg.equals(dto.getCurrentUserOrgId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orgList)) {
                throw new BizRuntimeException(ErrorCode.USER_AUTH_EXCEPTION, ErrorMessage.USER_AUTH_EXCEPTION_MSG);
            }
        }
        return pfUserDao.freezeUser(dto.getList()) >= 1 ? true : false;
    }

    @Override
    public UserInfo selectUser(String userName) {
        return pfUserDao.selectUser(userName);
    }

    @Override
    public UserInfo selectUserById(Long userId) {
        return pfUserDao.selectUserById(userId);
    }

    @Override
    public boolean updatePsw(UpdatePswDto dto) {
        // 密码加密
        UserInfo userInfo = new UserInfo();
        String salt = UUID.randomUUID().toString().replace("-", "");
        userInfo.setSalt(salt);
        userInfo.setUserId(dto.getUserId());
        userInfo.setPassword(genEncriptPwd(dto.getNewPassword(), salt));
        return pfUserDao.updatePsw(userInfo) == 1 ? true : false;
    }

    @Override
    public boolean matchPassword(String rawPwd, String salt, String encriptPwd) {
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder(salt);
        return passwordEncoder.matches(rawPwd, encriptPwd);
    }

    @Override
    public PfStudentVo selectStudentInfo(Long idStudent) {
        return pfUserDao.selectStudentInfo(idStudent);
    }

    @Override
    public String genEncriptPwd(String rawPwd, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = "";
        }
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder(salt);
        String encodedPassword = passwordEncoder.encode(rawPwd);
        return encodedPassword;
    }

}
