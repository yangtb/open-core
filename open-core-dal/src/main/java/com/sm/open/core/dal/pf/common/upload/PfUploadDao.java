package com.sm.open.core.dal.pf.common.upload;

import com.sm.open.core.model.entity.BasMedia;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfUploadDao {

    /**
     * 新增多媒体信息
     *
     * @param dto
     * @return
     */
    Integer addBasMedia(BasMedia dto);

    /**
     * 根据主键集合查询多媒体信息
     *
     * @param list
     * @return
     */
    List<BasMedia> selectBaseMediaByIds(@Param("list") List<Long> list);
}
