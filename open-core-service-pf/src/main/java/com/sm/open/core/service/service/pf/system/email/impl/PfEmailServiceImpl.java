package com.sm.open.core.service.service.pf.system.email.impl;

import com.sm.open.core.service.service.pf.system.email.PfEmailService;
import org.springframework.stereotype.Service;

@Service("pfEmailService")
public class PfEmailServiceImpl implements PfEmailService {

    @Override
    public boolean sendEmail() {
        return false;
    }
}
