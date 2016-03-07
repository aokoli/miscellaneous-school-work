package com.fms.service;


import com.fms.user.Employee;
import com.fms.user.Tenant;

public interface UserMgrService {

    public int createTenant(Tenant tenant);

    public int createEmployee(Employee employee);

}
