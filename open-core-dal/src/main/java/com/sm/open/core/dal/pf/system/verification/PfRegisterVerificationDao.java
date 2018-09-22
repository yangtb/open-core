package com.sm.open.core.dal.pf.system.verification;

import com.sm.open.core.model.entity.RegisterVerification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: RegisterVerificationDao
 * @Description: 验证码dao
 * @Author yangtongbin
 * @Date 2018/9/22 09:51
 */
@Repository
public interface PfRegisterVerificationDao {

    /**
     * 掺入验证码发送记录
     *
     * @param dto
     * @return
     */
    int insertRegisterVerification(RegisterVerification dto);

    /**
     * 更新验证码信息
     *
     * @param dto
     * @return
     */
    int updateRegisterVerification(RegisterVerification dto);


    /**
     * 获取验证码信息
     *
     * @param receiver 验证码接收地址
     * @return
     */
    RegisterVerification selectRvByReceiver(@Param("receiver") String receiver);
}
