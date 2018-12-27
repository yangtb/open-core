package com.sm.open.core.dal.pf.biz.clinic;

import com.sm.open.core.model.dto.pf.biz.clinic.parts.PfClinicPartsDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasAlgorithm;
import com.sm.open.core.model.entity.BasEvaAsse;
import com.sm.open.core.model.entity.BasMedAsse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfClinicPartsDao
 * @Description: 临床模板组件定义dao
 * @Author yangtongbin
 * @Date 2018/10/12
 */
@Repository
public interface PfClinicPartsDao {
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
    Integer addPart(BasMedAsse dto);

    /**
     * 编辑组件定义
     *
     * @param dto
     * @return
     */
    Integer editPart(BasMedAsse dto);

    /**
     * 删除组件定义
     *
     * @param dto
     * @return
     */
    Integer delPart(PfBachChangeStatusDto dto);

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
    Integer addSheet(BasEvaAsse dto);

    /**
     * 编辑评估表定义
     *
     * @param dto
     * @return
     */
    Integer editSheet(BasEvaAsse dto);

    /**
     * 删除评估表定义
     *
     * @param dto
     * @return
     */
    Integer delSheet(PfBachChangeStatusDto dto);

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
    Integer addAlgorithm(BasAlgorithm dto);

    /**
     * 编辑算法定义
     *
     * @param dto
     * @return
     */
    Integer editAlgorithm(BasAlgorithm dto);

    /**
     * 删除算法定义
     *
     * @param dto
     * @return
     */
    Integer delAlgorithm(PfBachChangeStatusDto dto);

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

    /**
     * 是否存在该组件
     *
     * @param cdMedAsse 组件编码
     * @return
     */
    Integer isExistPart(@Param("cdMedAsse") String cdMedAsse);

    /**
     * 是否存在该评估表
     *
     * @param cdEvaAsse 组件编码
     * @return
     */
    Integer isExistSheet(@Param("cdEvaAsse") String cdEvaAsse);
}
