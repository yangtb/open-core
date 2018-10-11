package com.sm.open.core.dal.pf.biz.clinic;

import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDemo;
import com.sm.open.core.model.entity.BasDemoCa;
import com.sm.open.core.model.entity.BasDemoTag;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfClinicTemplateDao
 * @Description: 临床模板定义dao
 * @Author yangtongbin
 * @Date 2018/10/8
 */
@Repository
public interface PfClinicTemplateDao {

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
    Integer editClassify(BasDemoCa dto);

    /**
     * 删除分类信息
     *
     * @param dto
     * @return
     */
    Integer delClassify(PfBachChangeStatusDto dto);

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
    Integer addTemplate(BasDemo dto);

    /**
     * 编辑模板
     *
     * @param dto
     * @return
     */
    Integer editTemplate(BasDemo dto);

    /**
     * 删除模板
     *
     * @param dto
     * @return
     */
    Integer delTemplate(PfBachChangeStatusDto dto);

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
    Integer delTag(PfBachChangeStatusDto dto);

    /**
     * 保存标签信息
     *
     * @param dto
     * @return
     */
    Integer saveTag(BasDemoTag dto);

    /**
     * 编辑标签信息
     *
     * @param dto
     * @return
     */
    Integer editTag(BasDemoTag dto);

    /**
     * 获取所有病历模板
     * @return
     */
    List<BasDemo> listAllBasDemo();
}
