package com.sm.open.core.model.entity;

import com.sm.open.core.model.vo.pf.common.media.BasMediaVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础_体检结果
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasBodyResult extends BasMediaVo implements Serializable {

    private static final long serialVersionUID = 1538920232797L;

    /**
     * 主键
     * 结果ID
     */
    private Long idResult;

    /**
     * 结果描述
     */
    private String desResult;

    /**
     * 部位ID
     */
    private Long idBody;

    /**
     * 多媒体ID
     */
    private Long idMedia;

    /**
     * 是否需要说明理由
     */
    private String fgReason;

    /**
     * 是否根据病人回答反馈
     */
    private String fgBack;

    /**
     * 专家解读
     */
    private String desExpert;

    /**
     * 标签
     */
    private String fgTag;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;

}
