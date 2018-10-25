package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfKbPartService
 * @Description: 病例组件用例
 * @Author yangtongbin
 * @Date 2018/10/17
 */
@Repository
public interface PfKbPartDao {

    /**
     * 病例组件用例总数
     *
     * @param dto
     * @return
     */
    Long countKbPart(PfMedCaseDto dto);

    /**
     * 病例组件用例列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCase> listKbPart(PfMedCaseDto dto);

    /**
     * 新增病例组件用例
     *
     * @param dto
     * @return
     */
    Long addKbPart(FaqMedCase dto);

    /**
     * 编辑病例组件用例
     *
     * @param dto
     * @return
     */
    Integer editKbPart(FaqMedCase dto);

    /**
     * 删除病例组件用例
     *
     * @param dto
     * @return
     */
    Integer delKbPart(PfBachChangeStatusDto dto);

    /**
     * 问诊_问题明细count
     *
     * @param dto
     * @return
     */
    Long countFaqMedCaseInques(PfPartCommonDto dto);

    /**
     * 问诊_问题明细
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInquesList> listFaqMedCaseInques(PfPartCommonDto dto);

    /**
     * 保存问诊问题
     *
     * @param dto
     * @return
     */
    Integer saveFaqMedCaseInques(FaqMedCaseInquesList dto);

    /**
     * 保存问诊问题
     *
     * @param dto
     * @return
     */
    Integer editFaqMedCaseInques(FaqMedCaseInquesList dto);

    /**
     * 删除问诊问题
     *
     * @param dto
     * @return
     */
    Integer delFaqMedCaseInques(PfBachChangeStatusDto dto);

    /**
     * 根据id查询
     *
     * @param dto
     * @return
     */
    FaqMedCaseInquesList selectConsById(FaqMedCaseInquesList dto);

    /**
     * 组件 - add文本
     *
     * @param dto
     * @return
     */
    Integer saveKbText(FaqMedCaseText dto);

    /**
     * 查询文本信息
     *
     * @param idMedCase
     * @return
     */
    FaqMedCaseText selectKbText(@Param("idMedCase") Long idMedCase);

    /**
     * 组件 - add图片
     *
     * @param dto
     * @return
     */
    Integer saveKbPic(FaqMedCasePic dto);

    /**
     * 查询图片信息
     *
     * @param idMedCase
     * @return
     */
    FaqMedCasePic selectKbPic(@Param("idMedCase") Long idMedCase);

    /**
     * 组件 - add患者
     *
     * @param dto
     * @return
     */
    Integer saveKbPat(FaqMedCasePatient dto);

    /**
     * 查询患者信息
     *
     * @param idMedCase
     * @return
     */
    FaqMedCasePatient selectKbPat(@Param("idMedCase") Long idMedCase);

    /**
     * 检验列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInspectList> listExams(PfPartCommonDto dto);

    /**
     * 保存检验问题
     *
     * @param dto
     * @return
     */
    Integer addExam(FaqMedCaseInspectList dto);

    /**
     * 保存检验问题
     *
     * @param dto
     * @return
     */
    Integer editExam(FaqMedCaseInspectList dto);

    /**
     * 删除检验项目
     *
     * @param dto
     * @return
     */
    Integer delKbExam(PfBachChangeStatusDto dto);

    /**
     * 根据id查询
     *
     * @param dto
     * @return
     */
    FaqMedCaseInspectList selectExamById(FaqMedCaseInspectList dto);

    /**
     * 检查列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseBodyList> listChecks(PfPartCommonDto dto);

    /**
     * 保存检查
     *
     * @param dto
     * @return
     */
    Integer addCheck(FaqMedCaseBodyList dto);

    /**
     * 修改检查
     *
     * @param dto
     * @return
     */
    Integer editCheck(FaqMedCaseBodyList dto);

    /**
     * 删除检查
     *
     * @param dto
     * @return
     */
    Integer delKbCheck(PfBachChangeStatusDto dto);

    /**
     * 重载检查
     *
     * @param dto
     * @return
     */
    FaqMedCaseBodyList selectCheckById(FaqMedCaseBodyList dto);

    /**
     * 保存检查图片
     *
     * @param dto
     * @return
     */
    Integer saveFaqMedCaseBody(FaqMedCaseBody dto);

    /**
     * 查询检查图片
     *
     * @param idMedCase
     * @return
     */
    FaqMedCaseBody selectFaqMedCaseBody(Long idMedCase);
}
