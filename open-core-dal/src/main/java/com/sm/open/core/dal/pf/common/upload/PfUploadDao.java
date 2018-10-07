package com.sm.open.core.dal.pf.common.upload;

import com.sm.open.core.model.entity.BasMedia;
import org.springframework.stereotype.Repository;

@Repository
public interface PfUploadDao {

    /**
     * 新增多媒体信息
     *
     * @param dto
     * @return
     */
    Integer addBasMedia(BasMedia dto);
}
