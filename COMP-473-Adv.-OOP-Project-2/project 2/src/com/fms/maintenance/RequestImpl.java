package com.fms.maintenance;

import com.fms.problem.IProblem;
import com.fms.schedule.ISchedule;

public class RequestImpl implements IRequest {
	
	int requestId;
	String requestDate;
	IProblem problem;
	private String requestedBy;
	
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#getMaintenanceRequestId()
	 */
	public int getRequestId() {
		return requestId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#setMaintenanceRequestId(int)
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#getRequestDate()
	 */
	public String getRequestDate() {
		return requestDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#setRequestDate(java.lang.String)
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#getProblem()
	 */
	public IProblem getProblem() {
		return problem;
	}
	/* (non-Javadoc)
	 * @see com.fms.maintenance.IMaintenanceReques#setProblem(com.fms.problem.IProblem)
	 */
	public void setProblem(IProblem problem) {
		this.problem = problem;
	}
	
	public String getRequestedBy() {
		// TODO Auto-generated method stub
		return requestedBy;
	}
	public void setRequestedBy(String RequestedBy) {
		// TODO Auto-generated method stub
		this.requestedBy = RequestedBy;
	}
	
	

}
