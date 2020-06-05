package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.assess.PfAssessCommonDto;
import com.sm.open.core.model.dto.pf.biz.kb.assess.PfAssessEffciencyDto;
import com.sm.open.core.model.dto.pf.biz.kb.assess.PfEvaCaseDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfKbAssessDao
 * @Description: 评估组件用例
 * @Author yangtongbin
 * @Date 2018/10/22
 */
@Repository
public interface PfKbAssessDao {

    /**
     * 评估组件用例总数
     *
     * @param dto
     * @return
     */
    Long countKbAssess(PfEvaCaseDto dto);

    /**
     * 评估组件用例列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCase> listKbAssess(PfEvaCaseDto dto);

    /**
     * 新增评估组件用例
     *
     * @param dto
     * @return
     */
    Integer addKbAssess(FaqEvaCase dto);

    /**
     * 新增评估组件用例
     *
     * @param dto
     * @return
     */
    Integer editKbAssess(FaqEvaCase dto);

    /**
     * 删除评估组件用例
     *
     * @param dto
     * @return
     */
    Integer delKbAssess(PfBachChangeStatusDto dto);

    /**
     * 评估项_拟诊_列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbReferral(PfAssessCommonDto dto);

    /**
     * 拟诊 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemReferral> listReferral(PfAssessCommonDto dto);

    /**
     * 拟诊 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delReferral(PfBachChangeStatusDto dto);

    /**
     * 拟诊 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long addReferral(FaqEvaCaseItemReferral dto);

    /**
     * 拟诊 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long editReferral(FaqEvaCaseItemReferral dto);

    /**
     * 拟诊 - 保存评估项
     *
     * @param dto
     * @return
     */
    Long addItem(FaqEvaCaseItem dto);

    /**
     * 拟诊 - 保存评估项
     *
     * @param dto
     * @return
     */
    Long editItem(FaqEvaCaseItem dto);

    /**
     * 拟诊 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delItem(PfBachChangeStatusDto dto);

    /**
     * 确诊 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbDiagnosis(PfAssessCommonDto dto);

    /**
     * 确诊 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemDiagnosis> listDiagnosisAnswer(PfAssessCommonDto dto);

    /**
     * 确诊 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delDiagnosis(PfBachChangeStatusDto dto);

    /**
     * 确诊 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Integer addDiagnosis(FaqEvaCaseItemDiagnosis dto);

    /**
     * 确诊 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Integer editDiagnosis(FaqEvaCaseItemDiagnosis dto);

    /**
     * 确诊理由 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbReason(PfAssessCommonDto dto);

    /**
     * 确诊理由 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemReason> listReasonAnswer(PfAssessCommonDto dto);

    /**
     * 确诊理由 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delReason(PfBachChangeStatusDto dto);

    /**
     * 确诊理由 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveReason(FaqEvaCaseItemReason dto);

    /**
     * 确诊理由 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editReason(FaqEvaCaseItemReason dto);


    /**
     * 鉴定检查 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbCover(PfAssessCommonDto dto);

    /**
     * 鉴定检查 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemCover> listCoverAnswer(PfAssessCommonDto dto);

    /**
     * 鉴定检查 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delCover(PfBachChangeStatusDto dto);

    /**
     * 鉴定检查 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveCover(FaqEvaCaseItemCover dto);

    /**
     * 鉴定检查 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editCover(FaqEvaCaseItemCover dto);


    /**
     * 覆盖必须检查 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbMust(PfAssessCommonDto dto);

    /**
     * 覆盖必须检查 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemMust> listMustAnswer(PfAssessCommonDto dto);

    /**
     * 覆盖必须检查 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delMust(PfBachChangeStatusDto dto);

    /**
     * 覆盖必须检查 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveMust(FaqEvaCaseItemMust dto);

    /**
     * 覆盖必须检查 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editMust(FaqEvaCaseItemMust dto);

    /**
     * 全面检查估表 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbThorough(PfAssessCommonDto dto);

    /**
     * 全面检查估表 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemThorough> listThoroughAnswer(PfAssessCommonDto dto);

    /**
     * 全面检查估表 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delThorough(PfBachChangeStatusDto dto);

    /**
     * 全面检查估表 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveThorough(FaqEvaCaseItemThorough dto);

    /**
     * 全面检查估表 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editThorough(FaqEvaCaseItemThorough dto);

    /**
     * 检查效率 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbEffciency(PfAssessCommonDto dto);

    /**
     * 检查效率 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delEffciency(PfBachChangeStatusDto dto);

    /**
     * 检查效率 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveEffciency(PfAssessEffciencyDto dto);

    /**
     * 检查效率 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editEffciency(PfAssessEffciencyDto dto);

    /**
     * 临时医嘱用药 - 评估项 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItem> listKbOrder(PfAssessCommonDto dto);

    /**
     * 临时医嘱用药 - 等效答案列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCaseItemOrder> listOrderAnswer(PfAssessCommonDto dto);

    /**
     * 临时医嘱用药 - 删除等效答案
     *
     * @param dto
     * @return
     */
    Integer delOrder(PfBachChangeStatusDto dto);

    /**
     * 临时医嘱用药 - 保存等效答案
     *
     * @param dto
     * @return
     */
    Long saveOrder(FaqEvaCaseItemOrder dto);

    /**
     * 临时医嘱用药 - 编辑等效答案
     *
     * @param dto
     * @return
     */
    Long editOrder(FaqEvaCaseItemOrder dto);

    /**
     * 删除评估项目
     *
     * @param dto
     * @return
     */
    Integer delCommonAssess(PfBachChangeStatusDto dto);

    /**
     * 复制组件主表数据
     *
     * @param dto
     * @return
     */
    Integer copyKbAssess(FaqEvaCase dto);

    /**
     * 查询评估项目
     *
     * @param idEvaCase
     * @return
     */
    List<FaqEvaCaseItem> selectFaqEvaCaseItem(@Param("idEvaCase") Long idEvaCase);

    /**
     * 复制评估项
     *
     * @param dto
     * @param oldIdEvaCase
     * @return
     */
    Integer copyAssessItem(FaqEvaCaseItem dto, @Param("oldIdEvaCase") Long oldIdEvaCase);

    /**
     * 复制拟诊
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess001(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);

    /**
     * 复制确诊项清单
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess002(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);


    /**
     * 复制确诊理由
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess003(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);


    /**
     * 复制鉴定检查
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess004(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);

    /**
     * 复制必须检查
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess005(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);

    /**
     * 复制检查效率
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess006(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);

    /**
     * 复制医嘱
     *
     * @param oldIdEvaCaseItem
     * @param newIdEvaCaseItem
     * @return
     */
    Integer copyKbAssess007(@Param("oldIdEvaCaseItem") Long oldIdEvaCaseItem,
                            @Param("newIdEvaCaseItem") Long newIdEvaCaseItem);


    boolean isExistFromCaseMust(FaqEvaCaseItem dto);


    /**
     * 删除无效的等效答案数据（从病例引入的数据）
     *
     * @param idEvaCaseItem
     * @return
     */
    Integer delFromCaseDefaultMust(@Param("idEvaCaseItem") Long idEvaCaseItem);


    /**
     * 删除无效的等效答案数据（从病例引入的数据）
     *
     * @param idEvaCaseItem
     * @return
     */
    Integer delFromCaseDefaultReason(@Param("idEvaCaseItem") Long idEvaCaseItem);

    /**
     * 删除无效的等效答案数据（从病例引入的数据）
     *
     * @param idEvaCaseItem
     * @return
     */
    Integer delFromCaseDefaultCover(@Param("idEvaCaseItem") Long idEvaCaseItem);


    /**
     * 删除无效的等效答案数据（从病例引入的数据）
     *
     * @param idEvaCaseItem
     * @return
     */
    Integer delFromCaseDefaultThorough(@Param("idEvaCaseItem") Long idEvaCaseItem);
}
