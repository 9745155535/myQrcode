package com.lijj.common.supper;

import javax.annotation.Resource;

import com.lijj.common.session.FWSession;

@SuppressWarnings("restriction")
public abstract class FWSessionSupper {
	private FWSession FwSission;

	public FWSession getFwSission() {
		return FwSission;
	}
	@Resource
	public void setFwSission(FWSession fwSission) {
		FwSission = fwSission;
	}
	
}
