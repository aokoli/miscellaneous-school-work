package com.fms.facilityuse;

import com.fms.facility.IUnit;

public interface IUsage {

	public int getUsageId();

	public void setUsageId(int usageId);

	public String getStartDate();

	public void setStartDate(String startDate);

	public String getEndDate();

	public void setEndDate(String endDate);

	public String getOwner();

	public void setOwner(String owner);

	public IUnit getUnit();
	
	public void setUnit(IUnit unit);
	
	public ITenant getTenant();

	public void setTenant(ITenant tenant);

}