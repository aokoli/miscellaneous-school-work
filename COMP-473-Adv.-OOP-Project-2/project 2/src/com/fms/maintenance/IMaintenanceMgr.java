package com.fms.maintenance;

import com.fms.facility.IUnit;
import com.fms.schedule.ISchedule;

/**
 * This interface serves as a glue for one maintenance activity. Maintenance 
 * takes shape in different stages; maintenance request, maintenance schedule,  
 * and the actual maintenance activity. This interface groups each of these 
 * stages together, making them associated with each other.
 * */
public interface IMaintenanceMgr {

	public int getMaintenanceMgrId();

	public void setMaintenanceMgrId(int maintenanceMgrId);

	public IRequest getMaintenanceRequest();

	public void setMaintenanceRequest(IRequest maintenanceRequest);

	public ISchedule getMaintenanceSchedule();

	public void setMaintenanceSchedule(ISchedule maintenanceSchedule);

	public IMaintenance getMaintenance();

	public void setMaintenance(IMaintenance maintenance);

	public IUnit getUnit();
	
	public void setUnit(IUnit unit);
}