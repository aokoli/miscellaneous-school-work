package com.fms.facilityuse;

import java.util.Set;

import com.fms.inspection.IInspection;
import com.fms.maintenance.IMaintenance;
import com.fms.schedule.*;

public class EmployeeImpl implements IEmployee {
	
	private int employeeId;
    private boolean isManager;
    private boolean isTechnician;
    private double salary;
    private IPerson person;
    private Set<IMaintenance> maintenances;
    private Set<IInspection> inspections;
    
    private Set<ISchedule> schedules;
	
    /* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#getEmployeeId()
	 */
    public int getEmployeeId() {
		return employeeId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#setEmployeeId(int)
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#isManager()
	 */
	public boolean isManager() {
		return isManager;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#setManager(boolean)
	 */
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#isTechnician()
	 */
	public boolean isTechnician() {
		return isTechnician;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#setTechnician(boolean)
	 */
	public void setTechnician(boolean isTechnician) {
		this.isTechnician = isTechnician;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#getSalary()
	 */
	public double getSalary() {
		return salary;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IEmployee#setSalary(double)
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public IPerson getPerson() {
		return person;
	}

	public void setPerson(IPerson person) {
		this.person = person;
	}
	
	public Set<ISchedule> getSchedules() {
		return schedules;
	}
	
	public void setSchedules(Set<ISchedule> schedules) {
		this.schedules = schedules;
	}
	
	public Set<IMaintenance> getMaintenances() {
		return maintenances;
	}
	
	public void setMaintenances(Set<IMaintenance> maintenances) {
		this.maintenances = maintenances;
	}
	
	public Set<IInspection> getInspections() {
		// TODO Auto-generated method stub
		return inspections;
	}
	
	public void setInspections(Set<IInspection> inspections) {
		// TODO Auto-generated method stub
		this.inspections = inspections;
	}


}
