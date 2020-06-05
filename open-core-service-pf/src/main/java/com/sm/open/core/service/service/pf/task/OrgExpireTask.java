package com.sm.open.core.service.service.pf.task;

import com.sm.open.core.dal.pf.system.org.PfOrgDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: OrgExpireTask
 * @Description: 机构试用过期处理
 * @Author yangtongbin
 * @Date 2018/10/1
 */
@Component("orgExpireTask")
public class OrgExpireTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrgExpireTask.class);

    @Resource
    private PfOrgDao pfOrgDao;

    /**
     * 每晚12点处理
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void task() {
        Integer num = pfOrgDao.updateExpireOrg();
        if (num >= 1) {
            LOGGER.info("【OrgExpireTask-task】机构试用过期处理【{}】家机构", num);
        }
    }

}
