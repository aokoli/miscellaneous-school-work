package com.fms.inspection;

import com.fms.facility.IUnit;
import com.fms.schedule.ISchedule;

public class InspectionMgrImpl implements IInspectionMgr {
	
	 private int inspectionMgrId;
	 private ISchedule inspectionSchedule;
	 private IInspection inspection;
	 private IUnit unit;
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspectionMgr#getInspectionMgrId()
	 */
	public int getInspectionMgrId() {
		return inspectionMgrId;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspectionMgr#setInspectionMgrId(int)
	 */
	public void setInspectionMgrId(int inspectionMgrId) {
		this.inspectionMgrId = inspectionMgrId;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspectionMgr#getInspectionSchedule()
	 */
	public ISchedule getInspectionSchedule() {
		return inspectionSchedule;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspectionMgr#setInspectionSchedule(com.fms.schedule.ISchedule)
	 */
	public void setInspectionSchedule(ISchedule inspectionSchedule) {
		this.inspectionSchedule = inspectionSchedule;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspectionMgr#getInspection()
	 */
	public IInspection getInspection() {
		return inspection;
	}

	public void setInspection(IInspection inspection) {
		this.inspection = inspection;
	}
	
	public IUnit getUnit() {
		return unit;
	}
	
	public void setUnit(IUnit unit) {
		this.unit = unit;
	}

}
