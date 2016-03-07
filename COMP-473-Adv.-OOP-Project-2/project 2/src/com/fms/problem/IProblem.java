package com.fms.problem;

public interface IProblem {
	
	public boolean isResolved();
	
	public void setResolved(boolean isResolved);
	
	public String getResolveDetails();
	
	public void setResolveDetails(String resolveDetails);

	public int getProblemId();

	public void setProblemId(int problemId);

	public String getProblemDetails();

	public void setProblemDetails(String problemDetails);
	

}