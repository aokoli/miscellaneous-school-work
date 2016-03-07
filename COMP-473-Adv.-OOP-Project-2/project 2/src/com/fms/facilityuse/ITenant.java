package com.fms.facilityuse;

import java.util.Set;

public interface ITenant {

	public double getRent();

	public void setRent(double rent);

	public int getTenantId();

	public void setTenantId(int tenantId);

	public double getIncome();

	public void setIncome(double income);
	
	public void setPerson(IPerson person);

	public IPerson getPerson();
	
	public Set<IUsage> getUsages();
	
	public void setUsages(Set<IUsage> usages);


}