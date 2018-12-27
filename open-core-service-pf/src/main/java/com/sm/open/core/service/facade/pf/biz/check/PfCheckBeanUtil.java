package com.sm.open.core.service.facade.pf.biz.check;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.check.BasCheckSearchItemResult;
import com.sm.open.core.facade.model.result.pf.biz.check.BasCheckSearchResult;
import com.sm.open.core.model.vo.pf.biz.check.BasCheckSearchVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfCheckBeanUtil {

    public static List<BasCheckSearchResult> convertList(List<BasCheckSearchVo> reqList) {
        List<BasCheckSearchResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        BasCheckSearchResult res;
        for (BasCheckSearchVo basCheckSearchVo : reqList) {
            res = new BasCheckSearchResult();
            BeanUtils.copyProperties(basCheckSearchVo, res);
            res.setCheckList(BeanUtil.convertList(basCheckSearchVo.getCheckList(), BasCheckSearchItemResult.class));
            resList.add(res);
        }
        return resList;
    }
}
