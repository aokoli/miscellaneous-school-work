package com.fms.facilityuse;

import com.fms.user.Tenant;

/* *
 *
 *  */
public interface IUsage {

    public int getUsageId();
    public void setUsageId(int id);
    public String getStartDate();
    public void setStartDate(String date);
    public String getEndDate();
    public void setEndDate(String date);
    public Tenant getOwner();
    public void setOwner(Tenant owner) ;
    /*
	public Object isInUseDuringInterval();
	public Object assignFacilityToUse();
	public Object vacateFacility();
	public Object listInspections();
	public Object listActualUsage();
	public Object calcUsageRate();
	*/

}
