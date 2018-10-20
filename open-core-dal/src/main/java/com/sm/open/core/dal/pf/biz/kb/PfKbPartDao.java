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
}
