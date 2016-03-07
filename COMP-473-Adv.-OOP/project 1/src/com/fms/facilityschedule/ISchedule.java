package com.fms.facilityschedule;

import com.fms.user.Employee;

public interface ISchedule {

    public int getScheduleId();
    public void setScheduleId(int scheduleId);
	public String getScheduleStartDate();
    public void setScheduleStartDate(String date);
    public String getScheduleEndDate();
	public void setScheduleEndDate(String date);
    public Employee getTechnician();
    public void setTechnician(Employee technician);

}
