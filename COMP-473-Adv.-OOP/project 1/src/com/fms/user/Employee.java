package com.fms.user;


import java.sql.Date;

public class Employee extends ConcreteUser {

    private int employeeId;
    private String joinDate;
    private boolean isManager;
    private boolean isActive;
    private double salary;

    public Employee(){}

    public Employee (String fname, String lname, String address, String phone, String email,
                     String joinDate, boolean isManager, boolean isActive, double salary){
        super(fname, lname, address, phone, email);
        this.joinDate = joinDate;
        this.isManager = isManager;
        this.isActive = isActive;
        this.salary = salary;
    }

    public int getEmployeeId(){
        return employeeId;
    }

    public void setEmployeeId(int id){
        this.employeeId = id;
    }

    public String getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(String joinDate){
        this.joinDate = joinDate;
    }

    public boolean getManager(){
        return isManager;
    }

    public void setManager(boolean isManager){
        this.isManager = isManager;
    }

    public boolean getActive(){
        return isActive;
    }

    public void setActive(boolean active){
        this.isActive = isActive;
    }

    public Double getSalary(){
        return salary;
    }

    public void setSalary(Double salary){
        this.salary = salary;
    }
}
