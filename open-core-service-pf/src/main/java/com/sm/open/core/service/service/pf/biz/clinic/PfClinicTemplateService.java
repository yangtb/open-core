package com.sm.open.core.service.service.pf.biz.clinic;

import com.sm.open.core.facade.model.result.pf.biz.clinic.BasDemoResult;
import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDemo;
import com.sm.open.core.model.entity.BasDemoCa;
import com.sm.open.core.model.entity.BasDemoTag;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;

import java.util.List;

/**
 * @ClassName: PfClinicTemplateService
 * @Description: 临床模板定义
 * @Author yangtongbin
 * @Date 2018/10/8
 */
public interface PfClinicTemplateService {

    /**
     * 分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listClassifyTree();

    /**
     * 新增分类信息
     *
     * @param dto
     * @return
     */
    Long addClassify(BasDemoCa dto);

    /**
     * 编辑分类信息
     *
     * @param dto
     * @return
     */
    boolean editClassify(BasDemoCa dto);

    /**
     * 删除分类信息
     *
     * @param dto
     * @return
     */
    boolean delClassify(PfBachChangeStatusDto dto);

    /**
     * 模板总数
     *
     * @param dto
     * @return
     */
    Long countTemplate(PfClinicTemplateDto dto);

    /**
     * 模板列表
     *
     * @param dto
     * @return
     */
    List<BasDemo> listTemplate(PfClinicTemplateDto dto);

    /**
     * 新增模板
     *
     * @param dto
     * @return
     */
    boolean addTemplate(BasDemo dto);

    /**
     * 编辑模板
     *
     * @param dto
     * @return
     */
    boolean editTemplate(BasDemo dto);

    /**
     * 删除模板
     *
     * @param dto
     * @return
     */
    boolean delTemplate(PfBachChangeStatusDto dto);

    /**
     * 标签列表
     *
     * @param dto
     * @return
     */
    List<BasDemoTag> listTag(PfClinicTemplateDto dto);

    /**
     * 删除标签信息
     *
     * @param dto
     * @return
     */
    boolean delTag(PfBachChangeStatusDto dto);

    /**
     * 保存标签信息
     *
     * @param dto
     * @return
     */
    Long saveTag(BasDemoTag dto);

    /**
     * 获取所有病历模板
     *
     * @return
     */
    List<BasDemo> listAllBasDemo();
}
