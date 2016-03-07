package com.fms.facilityuse;

import java.util.Set;

import com.fms.inspection.IInspection;
import com.fms.maintenance.IMaintenance;
import com.fms.schedule.*;

public interface IEmployee {

	public int getEmployeeId();

	public void setEmployeeId(int employeeId);

	public boolean isManager();

	public void setManager(boolean isManager);

	public boolean isTechnician();

	public void setTechnician(boolean isTechnician);

	public double getSalary();

	public void setSalary(double salary);

	public void setPerson(IPerson person);

	public IPerson getPerson();

	public Set<ISchedule> getSchedules();
	
	public void setSchedules(Set<ISchedule> schedules);
	
	public Set<IMaintenance> getMaintenances();
	
	public void setMaintenances(Set<IMaintenance> maintenances);
	
	public Set<IInspection> getInspections();
	
	public void setInspections(Set<IInspection> inspections);
}