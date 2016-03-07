package com.fms.inspection;

import com.fms.facilityuse.IEmployee;
import com.fms.problem.IProblem;

public interface IInspection {

	public IProblem getProblem();

	public void setProblem(IProblem problem);

	public int getInspectionId();

	public String getInspectionDetails();

	public void setInspectionDetails(String inspectionDetails);

	public void setInspectionId(int inspectionId);
	
	public IEmployee getEmployee();
	
	public void setEmployee(IEmployee emp);

}