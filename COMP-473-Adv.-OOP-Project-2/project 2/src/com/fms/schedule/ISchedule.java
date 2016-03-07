package com.fms.schedule;

import com.fms.facilityuse.IEmployee;

public interface ISchedule {
	
	public int getScheduleId();
	
	public void setScheduleId(int scheduleId);
	
	public String getStartDate();

	public void setStartDate(String startDate);

	public String getEndDate();

	public void setEndDate(String endDate);
	
	public IEmployee getEmployee();
	
	public void setEmployee(IEmployee emp);

}