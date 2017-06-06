package com.sm.open.core.facade.model.rpc;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
* @ClassName: ContextParam
* @Description: 上下文参数，比如用户信息
*/
public class ContextBean implements Serializable  {
	private static final long serialVersionUID = -304146729740921774L;

	/** 支付宝用户id，最大长度32 */
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
