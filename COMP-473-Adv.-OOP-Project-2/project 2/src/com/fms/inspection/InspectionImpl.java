package com.fms.inspection;

import com.fms.facilityuse.IEmployee;
import com.fms.problem.IProblem;
import com.fms.schedule.ISchedule;

public class InspectionImpl implements IInspection  {
	
	 private int inspectionId;
	 private String inspectionDetails;
	 private IProblem problem;
	 private IEmployee employee;
	 // Person inspectedBy;
	 
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#getProblem()
	 */
	public IProblem getProblem() {
		return problem;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#setProblem(com.fms.problem.IProblem)
	 */
	public void setProblem(IProblem problem) {
		this.problem = problem;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#getInspectionId()
	 */
	public int getInspectionId() {
		return inspectionId;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#getInspectionDetails()
	 */
	public String getInspectionDetails() {
		return inspectionDetails;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#setInspectionDetails(java.lang.String)
	 */
	public void setInspectionDetails(String inspectionDetails) {
		this.inspectionDetails = inspectionDetails;
	}
	/* (non-Javadoc)
	 * @see com.fms.inspection.IInspection#setInspectionId(int)
	 */
	public void setInspectionId(int inspectionId) {
		this.inspectionId = inspectionId;
	}
	public IEmployee getEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}
	public void setEmployee(IEmployee emp) {
		// TODO Auto-generated method stub
		this.employee = emp;
	}

}
