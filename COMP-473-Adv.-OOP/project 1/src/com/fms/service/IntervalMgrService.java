package com.fms.service;

public interface IntervalMgrService {
	
	/** Date has to be entered in YYYY-MM-DD format */
	public boolean isInUseDuringInterval(int unitId, String startDate, String endDate); 
}
