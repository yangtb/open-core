package com.sm.open.core.service.service.pf.biz.inquisition;

import com.sm.open.core.model.dto.pf.biz.inquisition.PfInquisitionQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasInques;
import com.sm.open.core.model.entity.BasInquesAnswer;
import com.sm.open.core.model.entity.BasInquesCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;

import java.util.List;

/**
 * @ClassName: PfInquisitionService
 * @Description: 问诊相关服务
 * @Author yangtongbin
 * @Date 2018/10/3
 */
public interface PfInquisitionService {

    /**
     * 问诊题库分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listQuestionClassifyTree();


    /**
     * 新增问诊题库分类信息
     *
     * @param dto
     * @return
     */
    Long addQuestionClassify(BasInquesCa dto);

    /**
     * 编辑问诊题库分类信息
     *
     * @param dto
     * @return
     */
    boolean editQuestionClassify(BasInquesCa dto);

    /**
     * 删除问诊题库分类信息
     *
     * @param dto
     * @return
     */
    boolean delQuestionClassify(PfBachChangeStatusDto dto);

    /**
     * 问题总数
     *
     * @param dto
     * @return
     */
    Long countQuestion(PfInquisitionQuestionDto dto);

    /**
     * 问题列表
     *
     * @param dto
     * @return
     */
    List<BasInques> listQuestion(PfInquisitionQuestionDto dto);

    /**
     * 新增问题
     *
     * @param dto
     * @return
     */
    boolean addQuestion(BasInques dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    boolean editQuestion(BasInques dto);

    /**
     * 删除问题
     *
     * @param dto
     * @return
     */
    boolean delQuestion(PfBachChangeStatusDto dto);

    /**
     * 问题答案列表
     *
     * @param dto
     * @return
     */
    List<BasInquesAnswer> listAnswer(PfInquisitionQuestionDto dto);

    /**
     * 删除答案信息
     *
     * @param dto
     * @return
     */
    boolean delAnswer(PfBachChangeStatusDto dto);

    /**
     * 保存答案信息
     *
     * @param dto
     * @return
     */
    Long saveAnswer(BasInquesAnswer dto);
}
