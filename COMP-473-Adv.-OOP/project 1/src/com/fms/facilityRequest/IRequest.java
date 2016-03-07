package com.fms.facilityrequest;

public interface IRequest {

    public int getRequestId();
    public void setRequestId(int requestId);
    public boolean getIsMaintenanceReqd();
    public void setIsMaintenanceReqd(boolean isMaintenanceReqd);
	public String getProblemDescription();
    public	void setProblemDescription(String problemDescription);
    public String getRequestDate();
    public	void setRequestDate(String requestDate);
    public RequestType getRequestType();
    public void setRequestType(RequestType type);
    public String getRequestedBy();
    public void setRequestedBy(String requestedBy);


}
