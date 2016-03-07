package com.fms.facilityuse;

import java.util.Set;

public class TenantImpl implements ITenant {
	
	private int tenantId;
    private double income;
    private double rent;
    private IPerson person;
    private Set<IUsage> usages;
	
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#getRent()
	 */
	public double getRent() {
		return rent;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#setRent(double)
	 */
	public void setRent(double rent) {
		this.rent = rent;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#getTenantId()
	 */
	public int getTenantId() {
		return tenantId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#setTenantId(int)
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#getIncome()
	 */
	public double getIncome() {
		return income;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.ITenant#setIncome(double)
	 */
	public void setIncome(double income) {
		this.income = income;
	}
	
	public IPerson getPerson() {
		return person;
	}
	public void setPerson(IPerson person) {
		this.person = person;
	}
	
	public Set<IUsage> getUsages() {
		return usages;
	}

	public void setUsages(Set<IUsage> usages) {
		this.usages = usages;
	}

}
