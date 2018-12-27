package com.sm.open.core.facade.model.param.pf.biz.tests.paper;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestPaperParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -8510412322594347408L;

    /**
     * 试卷ID
     */
    private Long idTestpaper;

    /**
     * 试卷名称
     */
    private String naTestpaper;

    /**
     * 所属分类
     */
    private Long idTestpaperca;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 搜索关键字
     */
    private String keywords;

}
