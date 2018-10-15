package com.sm.open.core.service.service.pf.biz.clinic;

import com.sm.open.core.model.dto.pf.biz.clinic.parts.PfClinicPartsDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasAlgorithm;
import com.sm.open.core.model.entity.BasEvaAsse;
import com.sm.open.core.model.entity.BasMedAsse;

import java.util.List;

/**
 * @ClassName: PfClinicPartsService
 * @Description: 临床模板组件定义
 * @Author yangtongbin
 * @Date 2018/10/12
 */
public interface PfClinicPartsService {

    /**
     * 组件总数
     *
     * @param dto
     * @return
     */
    Long countParts(PfClinicPartsDto dto);

    /**
     * 组件列表
     *
     * @param dto
     * @return
     */
    List<BasMedAsse> listParts(PfClinicPartsDto dto);

    /**
     * 新增组件定义
     *
     * @param dto
     * @return
     */
    boolean addPart(BasMedAsse dto);

    /**
     * 编辑组件定义
     *
     * @param dto
     * @return
     */
    boolean editPart(BasMedAsse dto);

    /**
     * 删除组件定义
     *
     * @param dto
     * @return
     */
    boolean delPart(PfBachChangeStatusDto dto);

    /**
     * 评估表总数
     *
     * @param dto
     * @return
     */
    Long countSheet(PfClinicPartsDto dto);

    /**
     * 评估表列表
     *
     * @param dto
     * @return
     */
    List<BasEvaAsse> listSheet(PfClinicPartsDto dto);

    /**
     * 新增评估表定义
     *
     * @param dto
     * @return
     */
    boolean addSheet(BasEvaAsse dto);

    /**
     * 编辑评估表定义
     *
     * @param dto
     * @return
     */
    boolean editSheet(BasEvaAsse dto);

    /**
     * 删除评估表定义
     *
     * @param dto
     * @return
     */
    boolean delSheet(PfBachChangeStatusDto dto);

    /**
     * 算法总数
     *
     * @param dto
     * @return
     */
    Long countAlgorithm(PfClinicPartsDto dto);

    /**
     * 算法列表
     *
     * @param dto
     * @return
     */
    List<BasAlgorithm> listAlgorithm(PfClinicPartsDto dto);

    /**
     * 新增评估表定义
     *
     * @param dto
     * @return
     */
    boolean addAlgorithm(BasAlgorithm dto);

    /**
     * 编辑算法定义
     *
     * @param dto
     * @return
     */
    boolean editAlgorithm(BasAlgorithm dto);

    /**
     * 删除算法定义
     *
     * @param dto
     * @return
     */
    boolean delAlgorithm(PfBachChangeStatusDto dto);

    /**
     * all所有组件
     *
     * @return
     */
    List<BasMedAsse> listAllPart();

    /**
     * all评估表列表
     *
     * @return
     */
    List<BasEvaAsse> listAllSheet();

    /**
     * all算法
     *
     * @return
     */
    List<BasAlgorithm> listAllAlgorithm();
}