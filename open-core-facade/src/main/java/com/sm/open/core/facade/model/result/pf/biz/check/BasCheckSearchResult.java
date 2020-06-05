package com.sm.open.core.facade.model.result.pf.biz.check;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 问诊问题
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasCheckSearchResult implements Serializable {

    private static final long serialVersionUID = 1538569588154L;

    /**
     * 主键
     * 部位ID
     */
    private Long idBody;

    /**
     * 部位描述
     */
    private String desBody;

    /**
     * 1. 正面 2. 背面 3. 胸部 4. 腹部 5. 头部
     */
    private String sdBody;

    /**
     * 部位分类ID
     */
    private Long idBodyCa;

    /**
     * 1. 触诊 2. 听诊 3. 视诊 4. 叩诊 5. 设备
     */
    private String cdCheck;

    private String idBodyText;

    /**
     * 问题答案
     */
    private List<BasCheckSearchItemResult> checkList;

}
