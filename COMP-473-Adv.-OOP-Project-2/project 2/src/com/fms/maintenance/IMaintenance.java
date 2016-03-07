package com.fms.maintenance;

import com.fms.facilityuse.IEmployee;

public interface IMaintenance {

	public int getMaintenanceId();

	public void setMaintenanceId(int maintenanceId);

	public double getServiceCost();

	public void setServiceCost(double serviceCost);

	public boolean isCostPaid();

	public void setCostPaid(boolean isCostPaid);
	
	public IEmployee getEmployee();
	
	public void setEmployee(IEmployee emp);

}