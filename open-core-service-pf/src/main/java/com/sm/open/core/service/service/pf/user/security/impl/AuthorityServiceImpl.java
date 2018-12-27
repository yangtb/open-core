package com.sm.open.core.service.service.pf.user.security.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.system.authority.AuthorityDao;
import com.sm.open.core.service.service.pf.user.security.AuthorityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Resource
	private AuthorityDao authorityDao;

	@Override
	public List<String> findAuthoritiesByUserId(Long userId, String roleType) {
		List<String> functionCodes;
		if (roleType.equals(YesOrNoNum.YES.getCode())) {
			functionCodes = authorityDao.findFunctionCodesByRoot();
		} else {
			functionCodes = authorityDao.findFunctionCodesByUserId(userId);
		}
		List<String> authorities = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(functionCodes)){
			authorities.addAll(functionCodes);
		}
		return authorities;
	}

}
