package com.sm.open.core.service.service.pf.user.login.impl;

import com.sm.open.core.dal.pf.user.login.PfUserDao;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.dto.pf.user.login.RegisterDto;
import com.sm.open.core.model.dto.pf.user.login.UpdatePswDto;
import com.sm.open.core.model.entity.UserInfo;
import com.sm.open.core.model.vo.pf.user.login.PfUsersVo;
import com.sm.open.core.service.service.pf.user.login.PfUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
        if (CollectionUtils.isNotEmpty(dto.getRoles())) {
            pfUserDao.saveUserRole(dto.getRoles(), user.getUserId());
        }
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
    public boolean delUser(List<Long> users) {
        return pfUserDao.delUser(users) >= 1 ? true : false;
    }

    @Override
    public boolean freezeUser(List<Long> users) {
        return pfUserDao.freezeUser(users) >= 1 ? true : false;
    }

    @Override
    public UserInfo selectUser(String userName) {
        return pfUserDao.selectUser(userName);
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
    public String genEncriptPwd(String rawPwd, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = "";
        }
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder(salt);
        String encodedPassword = passwordEncoder.encode(rawPwd);
        return encodedPassword;
    }

}
