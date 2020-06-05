package com.sm.open.core.dal.pf.biz.check;

import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasBody;
import com.sm.open.core.model.entity.BasBodyCa;
import com.sm.open.core.model.entity.BasBodyResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.PfTreeSelectVo;
import com.sm.open.core.model.vo.pf.biz.check.BasCheckSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfCheckDao {

    /**
     * 题库分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listQuestionClassifyTree();

    /**
     * 新增题库分类信息
     *
     * @param dto
     * @return
     */
    Long addQuestionClassify(BasBodyCa dto);

    /**
     * 编辑题库分类信息
     *
     * @param dto
     * @return
     */
    Integer editQuestionClassify(BasBodyCa dto);

    /**
     * 删除题库分类信息
     *
     * @param dto
     * @return
     */
    Integer delQuestionClassify(PfBachChangeStatusDto dto);

    /**
     * 问题总数
     *
     * @param dto
     * @return
     */
    Long countQuestion(PfCheckQuestionDto dto);

    /**
     * 问题列表
     *
     * @param dto
     * @return
     */
    List<BasBody> listQuestion(PfCheckQuestionDto dto);

    /**
     * 新增问题
     *
     * @param dto
     * @return
     */
    Integer addQuestion(BasBody dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    Integer editQuestion(BasBody dto);

    /**
     * 删除问题
     *
     * @param dto
     * @return
     */
    Integer delQuestion(PfBachChangeStatusDto dto);

    /**
     * 问题答案列表
     *
     * @param dto
     * @return
     */
    List<BasBodyResult> listAnswer(PfCheckQuestionDto dto);

    /**
     * 删除答案信息
     *
     * @param dto
     * @return
     */
    Integer delAnswer(PfBachChangeStatusDto dto);

    /**
     * 保存答案信息
     *
     * @param dto
     * @return
     */
    Integer saveAnswer(BasBodyResult dto);

    /**
     * 编辑答案信息
     *
     * @param dto
     * @return
     */
    Integer editAnswer(BasBodyResult dto);

    /**
     * 有无默认答案
     *
     * @param idBody 部位ID
     * @return
     */
    boolean isExistDefaultAnswer(@Param("idBody") Long idBody);

    /**
     * 设定默认答案
     *
     * @param idBody 部位ID
     * @return
     */
    Integer setDefaultAnswer(@Param("idBody") Long idBody);

    /**
     * 更新默认答案
     *
     * @param idBody 部位ID
     * @return
     */
    Integer updateDefaultAnswer(@Param("idBody") Long idBody);

    /**
     * 根据ID查询体检部位
     *
     * @param idBody
     * @return
     */
    BasBody selectBasBodyById(@Param("idBody") Long idBody);

    /**
     * 根据ID查询体检结果
     *
     * @param idResult
     * @return
     */
    BasBodyResult selectBasBodyResultById(@Param("idResult") Long idResult);

    /**
     * 检查项目总数
     *
     * @param dto
     * @return
     */
    Long countSearchCheck(PfCommonSearchDto dto);

    /**
     * 检查项目列表
     *
     * @param dto
     * @return
     */
    List<BasCheckSearchVo> searchCheck(PfCommonSearchDto dto);

    /**
     * treeSelect
     *
     * @return
     */
    List<PfTreeSelectVo> listQuestionClassifyTreeSelect();
}
