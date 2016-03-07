package com.fms.inspection;

import com.fms.facility.IUnit;
import com.fms.schedule.ISchedule;

public interface IInspectionMgr {

	public int getInspectionMgrId();

	public void setInspectionMgrId(int inspectionMgrId);

	public ISchedule getInspectionSchedule();

	public void setInspectionSchedule(ISchedule inspectionSchedule);

	public IInspection getInspection();

	public void setInspection(IInspection inspection);

	public IUnit getUnit();
	
	public void setUnit(IUnit unit);
}