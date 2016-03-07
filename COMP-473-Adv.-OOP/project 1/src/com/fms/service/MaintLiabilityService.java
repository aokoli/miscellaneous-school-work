package com.fms.service;


import com.fms.facility.FacilityUnitSelector;

public interface MaintLiabilityService extends FacilityUnitSelector {
	
	public String listFacilityProblems();
	public String calcProblemRateForFacility();
	public String calcMaintenanceCostForFacility();
	public String calcDownTimeForFacility();
}
