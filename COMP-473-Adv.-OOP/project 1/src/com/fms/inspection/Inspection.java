package com.fms.inspection;


import com.fms.user.Employee;

public class Inspection implements IInspection{

    private int inspectionId;
    private String actualStartDate;
    private String actualEndDate;
    private Employee technician;
    private boolean isMaintenanceReqd;
    private String probDesc;


    @Override
    public int getInspectionId() {
        return inspectionId;
    }

    @Override
    public void setInspectionId(int inspectionId) {
       this.inspectionId = inspectionId;
    }

    @Override
    public boolean getIsMaintenanceReqd() {
        return isMaintenanceReqd;
    }

    @Override
    public void setIsMaintenanceReqd(boolean isMaintenanceReqd) {
        this.isMaintenanceReqd = isMaintenanceReqd;
    }

    @Override
    public String getActualStartDate() {
        return actualStartDate;
    }

    @Override
    public void setActualStartDate(String date) {
        this.actualStartDate = date;
    }

    @Override
    public String getActualEndDate() {
        return actualEndDate;
    }

    @Override
    public void setActualEndDate(String date) {
        this.actualEndDate = date;
    }

    @Override
    public Employee getTechnician() {
        return technician;
    }

    @Override
    public void setTechnician(Employee technician) {
          this.technician = technician;
    }

    @Override
    public String getProbDesc() {
        return probDesc;
    }

    @Override
    public void setProbDesc(String desc) {
        this.probDesc = desc;
    }

}
