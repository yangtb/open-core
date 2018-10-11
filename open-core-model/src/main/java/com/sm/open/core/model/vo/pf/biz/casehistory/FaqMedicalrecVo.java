package com.sm.open.core.model.vo.pf.biz.casehistory;

import com.sm.open.core.model.entity.FaqMedicalrec;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 知识库_病历
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class FaqMedicalrecVo extends FaqMedicalrec implements Serializable {

    private static final long serialVersionUID = 1539141898553L;


    /**
     * 分类名称
     */
    private String idMedicalrecCaText;

    /**
     * 病历模板名称
     */
    private String chtName;

    /**
     * 机构名称
     */
    private String orgName;


}
