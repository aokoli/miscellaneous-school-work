package com.fms.user;


public class Tenant extends ConcreteUser {

    private int tenantId;
    private double income;
    private String previousLocation;
    private String currentEmployer;

    public Tenant(){}

    public Tenant(String fname, String lname, String address, String phone, String email, double income,
                                            String previousLocation, String currentEmployer){
        super(fname, lname, address, phone, email);
        this.income = income;
        this.previousLocation = previousLocation;
        this.currentEmployer = currentEmployer;
    }

    public int getTenantId(){
        return tenantId;
    }

    public void setTenantId(int id){
        this.tenantId = id;
    }

    public Double getIncome(){
        return income;
    }

    public void setIncome(Double income){
        this.income = income;
    }

    public String getPreviousLocation(){
        return previousLocation;
    }

    public void setPreviousLocation(String previousLocation){
        this.previousLocation = previousLocation;
    }

    public String getCurrentEmployer(){
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer){
        this.currentEmployer = currentEmployer;
    }
}
