package com.fms.facilityuse;


import com.fms.user.Tenant;

public class Usage implements IUsage {

    private int usageId;
    private String startDate;
    private String endDate;
    private Tenant owner;

    @Override
    public int getUsageId() {
        return usageId;
    }

    @Override
    public void setUsageId(int id) {
        this.usageId = id;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(String date) {
        this.startDate = date;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(String date) {
        this.endDate = date;
    }

    @Override
    public Tenant getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Tenant owner) {
        this.owner = owner;
    }
}
