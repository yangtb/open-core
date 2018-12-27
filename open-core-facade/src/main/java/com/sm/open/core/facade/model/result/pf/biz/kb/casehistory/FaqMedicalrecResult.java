package com.sm.open.core.facade.model.result.pf.biz.kb.casehistory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识库_病例
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class FaqMedicalrecResult implements Serializable {

    private static final long serialVersionUID = 1539141898553L;

    /**
     * 主键
     * 病例id
     */
    private Long idMedicalrec;

    /**
     * 病例名称
     */
    private String name;

    /**
     * 模板id
     */
    private Long idDemo;

    /**
     * 所属分类
     */
    private Long idMedicalrecCa;

    /**
     * 病例归属
     */
    private Long idOrg;

    /**
     * 病例级别 : 1 初级 2 中级 3 高级
     */
    private String sdLevel;

    /**
     * 病例用途 : 1 训练病例 2 测试病例
     */
    private String sdUse;

    /**
     * 使用次数
     */
    private Integer count;

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

    /**
     * 分类名称
     */
    private String idMedicalrecCaText;

    /**
     * 病例模板名称
     */
    private String chtName;

    /**
     * 机构名称
     */
    private String orgName;

}
