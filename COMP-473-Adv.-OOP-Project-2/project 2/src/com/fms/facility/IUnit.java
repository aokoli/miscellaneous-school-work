package com.fms.facility;

import java.util.*;

import com.fms.facilityuse.ITenant;
import com.fms.facilityuse.IUsage;
import com.fms.inspection.IInspectionMgr;
import com.fms.maintenance.IMaintenanceMgr;

public interface IUnit {

	/*
	private List<IRequest> requestList = new ArrayList<IRequest>();
	private List<ISchedule> scheduleList =new ArrayList<ISchedule>();
	private List<IMaintenance> maintenanceList =new ArrayList<IMaintenance>();
	private List<IInspection> inspectionList =new ArrayList<IInspection>();
	private List<IProblem> problemList = new ArrayList<IProblem>();
	 */
	public abstract int getUnitId();

	public abstract void setUnitId(int unitId);

	public abstract boolean isVacant();

	public abstract void setVacant(boolean isVacant);

	public Set<IUsage> getUsages();

	public void setUsages(Set<IUsage> usages);

	public IFacility getFacility();
	
	public void setFacility(IFacility fac);
	
	public Set<IMaintenanceMgr> getMaintenanceMgr();
	
	public void setMaintenanceMgr(Set<IMaintenanceMgr> maintmgr);
	
	public Set<IInspectionMgr> getInspectionMgr();
	
	public void setInspectionMgr(Set<IInspectionMgr> inspecmgr);
	
}