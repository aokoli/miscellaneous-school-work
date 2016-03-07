package com.fms.maintenance;

import com.fms.facilityuse.IEmployee;

public class MaintenanceImpl implements IMaintenance {

	private int maintenanceId;
//	private IRequest maintenanceRequest;
//  private ISchedule maintenanceSchedule;
    private double serviceCost;	// TODO Make Cost class?
	private boolean isCostPaid; 
    private IEmployee employee;
	
    /* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#getMaintenanceId()
	 */
    public int getMaintenanceId() {
		return maintenanceId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#setMaintenanceId(int)
	 */
	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#getServiceCost()
	 */
	public double getServiceCost() {
		return serviceCost;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#setServiceCost(double)
	 */
	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#isCostPaid()
	 */
	public boolean isCostPaid() {
		return isCostPaid;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenance#setCostPaid(boolean)
	 */
	public void setCostPaid(boolean isCostPaid) {
		this.isCostPaid = isCostPaid;
	}
	
	public IEmployee getEmployee() {
		return employee;
	}
	
	public void setEmployee(IEmployee emp) {
		this.employee = emp;
	}


}
