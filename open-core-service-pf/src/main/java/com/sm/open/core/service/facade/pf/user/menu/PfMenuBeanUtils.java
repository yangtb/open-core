package com.sm.open.core.service.facade.pf.user.menu;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.user.menu.PfBaseMenuResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuResult;
import com.sm.open.core.model.vo.pf.user.menu.PfMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfMenuBeanUtils {

    public static List<PfMenuResult> convertMyMenuList(List<PfMenuVo> reqList) {
        List<PfMenuResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        PfMenuResult res;
        for (PfMenuVo pfMenuVo : reqList) {
            res = new PfMenuResult();
            BeanUtils.copyProperties(pfMenuVo, res);
            res.setGroupList(BeanUtil.convertList(pfMenuVo.getGroupList(), PfBaseMenuResult.class));
            resList.add(res);
        }
        return resList;
    }
}
