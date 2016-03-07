package com.fms.problem;


import java.sql.Date;

public class Problem implements IProblem {

    private int problemId;
    private String probStartDate;
    private String probDesc;
    private String resolveDate;
    private boolean IsResolved;

    @Override
    public int getProblemId() {
        return problemId;
    }

    @Override
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    @Override
    public String getProbStartDate() {
        return probStartDate;
    }

    @Override
    public void setProbStartDate(String probStartDate) {
         this.probStartDate = probStartDate;
    }

    @Override
    public String getProbDesc() {
        return probDesc;
    }

    @Override
    public void setProbDesc(String probDesc) {
        this.probDesc = probDesc;
    }

    @Override
    public boolean getIsResolved() {
        return IsResolved;
    }

    @Override
    public void setIsResolved(boolean IsResolved) {
        this.IsResolved = IsResolved;
    }

    @Override
    public String getResolveDate() {
        return resolveDate;
    }

    @Override
    public void setResolveDate(String resolveDate) {
         this.resolveDate = resolveDate;
    }
}
