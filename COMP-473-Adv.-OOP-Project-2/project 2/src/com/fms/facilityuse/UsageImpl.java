package com.fms.facilityuse;

import com.fms.facility.IUnit;


public class UsageImpl implements IUsage {
	
	private int usageId;
    private String startDate;
    private String endDate;
    private String owner;
    private IUnit unit;
    private ITenant tenant;  

    /* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#getUsageId()
	 */
    public int getUsageId() {
		return usageId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#setUsageId(int)
	 */
	public void setUsageId(int usageId) {
		this.usageId = usageId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#getStartDate()
	 */
	public String getStartDate() {
		return startDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#setStartDate(java.lang.String)
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#getEndDate()
	 */
	public String getEndDate() {
		return endDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#setEndDate(java.lang.String)
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#getOwner()
	 */
	public String getOwner() {
		return owner;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IUsage#setOwner(java.lang.String)
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ITenant getTenant() {
		return tenant;
	}

	public void setTenant(ITenant tenant) {
		this.tenant = tenant;
	}
	public IUnit getUnit() {
		// TODO Auto-generated method stub
		return unit;
	}
	public void setUnit(IUnit unit) {
		// TODO Auto-generated method stub
		this.unit = unit;
	}

}
