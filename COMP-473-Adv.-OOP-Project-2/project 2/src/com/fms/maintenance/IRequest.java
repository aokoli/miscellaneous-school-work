package com.fms.maintenance;

import com.fms.facilityuse.ITenant;
import com.fms.problem.IProblem;

public interface IRequest {

	// Person requestedBy
	public int getRequestId();

	public void setRequestId(int requestId);

	public String getRequestDate();

	public void setRequestDate(String requestDate);

	public IProblem getProblem();

	public void setProblem(IProblem problem);
	
	public String getRequestedBy();
	
	public void setRequestedBy(String RequestedBy);

}