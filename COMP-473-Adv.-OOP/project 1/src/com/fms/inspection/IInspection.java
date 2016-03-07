package com.fms.inspection;

import com.fms.user.Employee;

public interface IInspection {
	
	public int getInspectionId();
	public void setInspectionId(int inspectionId);
    public String getActualStartDate();
    public void setActualStartDate(String date);
    public String getActualEndDate();
    public void setActualEndDate(String date);
    public Employee getTechnician();
    public void setTechnician(Employee technician);
    public boolean getIsMaintenanceReqd();
    public void setIsMaintenanceReqd(boolean isMaintenanceReqd);
    public String getProbDesc();
    public void setProbDesc(String desc);

}
