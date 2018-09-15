package com.sm.open.core.dal.pf.biz.media;

import com.sm.open.core.model.dto.pf.biz.media.PfMediaDto;
import com.sm.open.core.model.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfMediaDao
 * @Description: 咨媒dao
 * @Author yangtongbin
 * @Date 2018/8/28 10:38
 */
@Repository
public interface PfMediaDao {

    /**
     * 获取banner总数
     *
     * @param dto
     * @return
     */
    Long countBanner(PfMediaDto dto);

    /**
     * banner列表
     *
     * @param dto
     * @return
     */
    List<Notice> listBanners(PfMediaDto dto);
}
