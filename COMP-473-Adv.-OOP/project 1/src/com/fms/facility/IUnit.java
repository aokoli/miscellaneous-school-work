package com.fms.facility;

import com.fms.facilitymaintenance.IMaintenance;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;
import com.fms.facilityuse.IUsage;
import com.fms.inspection.IInspection;
import com.fms.problem.IProblem;

import java.util.*;

/*Renamed interface IUnit to just IUnit for clarity*/
public interface IUnit {

    public int getUnitId();
	public void setUnitId(int unitId);
    public boolean isVacant();
    public void setVacancy(boolean vacant);

    //for list of unit usages
    public List<IUsage> getUsageList();
    public void addUsage(IUsage usage);

    //list of unit service requests
    public List<IRequest> getRequestList();
    public void addRequest(IRequest request);

    //list of schedules
    public List<ISchedule> getScheduleList();
    public void addSchedule(ISchedule schedule);

    //list of maintenances
    public List<IMaintenance> getMaintenanceList();
    public void addMaintenance(IMaintenance maintenance);

    //list of inspections
    public List<IInspection> getInspectionList();
    public void addInspection(IInspection inspection);
    
    // list of problems
    public List<IProblem> getProblemList();
    public void addProblem(IProblem problem);
}
