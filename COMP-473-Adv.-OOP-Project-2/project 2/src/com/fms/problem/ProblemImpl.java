package com.fms.problem;

public class ProblemImpl implements IProblem {

	int problemId;
	// Problem start/end dates
	boolean isResolved;
	String problemDetails;
	String resolveDetails;
	/* (non-Javadoc)
	 * @see com.fms.problem.IProblem#getProblemId()
	 */
	public int getProblemId() {
		return problemId;
	}
	public boolean isResolved() {
		return isResolved;
	}
	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}
	public String getResolveDetails() {
		return resolveDetails;
	}
	public void setResolveDetails(String resolveDetails) {
		this.resolveDetails = resolveDetails;
	}
	/* (non-Javadoc)
	 * @see com.fms.problem.IProblem#setProblemId(int)
	 */
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	/* (non-Javadoc)
	 * @see com.fms.problem.IProblem#getProblemDetails()
	 */
	public String getProblemDetails() {
		return problemDetails;
	}
	/* (non-Javadoc)
	 * @see com.fms.problem.IProblem#setProblemDetails(java.lang.String)
	 */
	public void setProblemDetails(String problemDetails) {
		this.problemDetails = problemDetails;
	}
	
	
	

}
