package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.PfSaveAsMedDto;
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
     * 问诊_问题明细总数
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
     * 组件 - add评估指南
     *
     * @param dto
     * @return
     */
    Integer saveKbGuide(FaqMedCaseGuide dto);

    /**
     * 查询评估指南
     *
     * @param idMedCase
     * @return
     */
    FaqMedCaseGuide selectKbGuide(@Param("idMedCase") Long idMedCase);

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
     * 检验列表总数
     *
     * @param dto
     * @return
     */
    Long countExams(PfPartCommonDto dto);

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
     * 检查列表总数
     *
     * @param dto
     * @return
     */
    Long countChecks(PfPartCommonDto dto);

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

    /**
     * 批量添加问诊
     *
     * @param dto
     * @return
     */
    Integer bachAddCons(PfSaveAsMedDto dto);

    /**
     * 批量添加问诊
     *
     * @param list
     * @return
     */
    Integer bachAddAllCons(List<FaqMedCaseInquesList> list);

    /**
     * 查询原有问诊记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseInquesList> selectOldConsRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 查询原有问诊记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseInquesList> selectAllConsRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 批量添加体格检查
     *
     * @param dto
     * @return
     */
    Integer bachAddCheck(PfSaveAsMedDto dto);

    /**
     * 批量添加检查
     *
     * @param list
     * @return
     */
    Integer bachAddAllCheck(List<FaqMedCaseBodyList> list);

    /**
     * 查询原有检查记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseBodyList> selectOldCheckRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 查询原有检查记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseBodyList> selectAllCheckRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 批量添加辅助检查
     *
     * @param dto
     * @return
     */
    Integer bachAddExam(PfSaveAsMedDto dto);

    /**
     * 批量添加辅助检查
     *
     * @param list
     * @return
     */
    Integer bachAddAllExam(List<FaqMedCaseInspectList> list);

    /**
     * 查询原有辅助检查记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseInspectList> selectOldExamRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 查询原有辅助检查记录
     *
     * @param idMedCase
     * @return
     */
    List<FaqMedCaseInspectList> selectAllExamRecord(@Param("idMedCase") Long idMedCase);

    /**
     * 复制组件主表数据
     *
     * @param dto
     * @return
     */
    Integer copyKbPart(FaqMedCase dto);

    /**
     * 复制文本
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbText(@Param("oldIdMedCase") Long oldIdMedCase,
                       @Param("idMedCase") Long idMedCase);

    /**
     * 复制文本
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbGuide(@Param("oldIdMedCase") Long oldIdMedCase,
                        @Param("idMedCase") Long idMedCase);

    /**
     * 复制图片
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbPic(@Param("oldIdMedCase") Long oldIdMedCase,
                      @Param("idMedCase") Long idMedCase);

    /**
     * 复制患者信息
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbPat(@Param("oldIdMedCase") Long oldIdMedCase,
                      @Param("idMedCase") Long idMedCase);

    /**
     * 复制问诊
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbCons(@Param("oldIdMedCase") Long oldIdMedCase,
                       @Param("idMedCase") Long idMedCase);

    /**
     * 复制体格检查
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbCheck(@Param("oldIdMedCase") Long oldIdMedCase,
                        @Param("idMedCase") Long idMedCase);

    /**
     * copy检查图片
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyCheckPic(@Param("oldIdMedCase") Long oldIdMedCase,
                         @Param("idMedCase") Long idMedCase);

    /**
     * 复制辅助检查
     *
     * @param oldIdMedCase
     * @param idMedCase
     * @return
     */
    Integer copyKbExam(@Param("oldIdMedCase") Long oldIdMedCase,
                       @Param("idMedCase") Long idMedCase);

    /**
     * 查询预设问题id
     *
     * @param idMedCaseList
     * @return
     */
    FaqMedCaseInquesList selectPreIds(@Param("idMedCaseList") Long idMedCaseList);

    /**
     * 问诊预设列表
     *
     * @param ids
     * @param idMedCaseList
     * @return
     */
    List<FaqMedCaseInquesList> listPreQuestion(@Param("ids") List<Long> ids,
                                               @Param("idMedCase") Long idMedCase);
}
