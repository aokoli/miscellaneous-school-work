package com.fms.maintenance;

import com.fms.facility.IUnit;
import com.fms.schedule.ISchedule;

public class MaintenanceMgrImpl implements IMaintenanceMgr {
	
	private int maintenanceMgrId;
	private IRequest maintenanceRequest;
    private ISchedule maintenanceSchedule;
    private IMaintenance maintenance;
    private IUnit unit;
    
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#getMaintenanceMgrId()
	 */
	public int getMaintenanceMgrId() {
		return maintenanceMgrId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#setMaintenanceMgrId(int)
	 */
	public void setMaintenanceMgrId(int maintenanceMgrId) {
		this.maintenanceMgrId = maintenanceMgrId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#getMaintenanceRequest()
	 */
	public IRequest getMaintenanceRequest() {
		return maintenanceRequest;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#setMaintenanceRequest(com.fms.maintenance.IRequest)
	 */
	public void setMaintenanceRequest(IRequest maintenanceRequest) {
		this.maintenanceRequest = maintenanceRequest;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#getMaintenanceSchedule()
	 */
	public ISchedule getMaintenanceSchedule() {
		return maintenanceSchedule;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#setMaintenanceSchedule(com.fms.schedule.ISchedule)
	 */
	public void setMaintenanceSchedule(ISchedule maintenanceSchedule) {
		this.maintenanceSchedule = maintenanceSchedule;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#getMaintenance()
	 */
	public IMaintenance getMaintenance() {
		return maintenance;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceMgr#setMaintenance(com.fms.maintenance.IMaintenance)
	 */
	public void setMaintenance(IMaintenance maintenance) {
		this.maintenance = maintenance;
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
