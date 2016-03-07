package com.fms.problem;


/* *
 * This table will need to be manipulated with business rules and automation
 *
 * */
public interface IProblem {

    public int getProblemId();
    public void setProblemId(int problemId);
    public String getProbStartDate();
    public void setProbStartDate(String date);
    public String getProbDesc();
    public void setProbDesc(String probDesc);
    public boolean getIsResolved();
    public void setIsResolved(boolean isResolved);
    public String getResolveDate();
    public void setResolveDate(String date);
}
