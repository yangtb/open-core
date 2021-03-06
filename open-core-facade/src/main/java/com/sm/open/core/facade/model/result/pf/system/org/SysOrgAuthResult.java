package com.sm.open.core.facade.model.result.pf.system.org;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysOrg
 * @Description: 机构实体
 * @Author yangtongbin
 * @Date 2018/9/20 15:30
 */
@Setter
@Getter
@ToString
public class SysOrgAuthResult extends SysOrgResult implements Serializable {

    private static final long serialVersionUID = -2762831637365775307L;
    /**
     * 申请ID
     */
    private Long idReg;
    /**
     * 状态 0=未处理, 1=审核通过, 2=审核驳回
     */
    private String sdReg;

    /**
     * 申请人
     */
    private String applyor;

    /**
     * 申请时间
     */
    private Date gmtApply;
    /**
     * 处理人
     */
    private String confirmor;

    /**
     * 处理时间
     */
    private Date gmtConfirm;

}
