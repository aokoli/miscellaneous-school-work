package com.fms.schedule;

import com.fms.facilityuse.IEmployee;

public class ScheduleImpl implements ISchedule {

	int scheduleId;
	String startDate;
	String endDate;
	private IEmployee employee;
	
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	/* (non-Javadoc)
	 * @see com.fms.schedule.ISchedule#getStartDate()
	 */
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public IEmployee getEmployee() {
		return employee;
	}
	
	public void setEmployee(IEmployee emp) {
		this.employee = emp;
	}
	
}
