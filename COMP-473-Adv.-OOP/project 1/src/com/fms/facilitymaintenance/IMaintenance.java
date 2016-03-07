package com.fms.facilitymaintenance;

import com.fms.user.Employee;

public interface IMaintenance {
	
	public int getMaintenanceId();
	public void setMaintenanceId(int maintId);
    public String getActualStartDate();
    public void setActualStartDate(String date);
    public String getActualEndDate();
    public void setActualEndDate(String date);
	public String getResolveDesc();
	public void setResolveDesc(String maintSerivceOffered);
    public Employee getTechnician();
    public void setTechnician(Employee technician);
	public double getServiceCost();
	public void setServiceCost(double maintServiceCost);
	public void setIsCostPaid(boolean isCostPaid);
	public boolean getIsCostPaid();

}
