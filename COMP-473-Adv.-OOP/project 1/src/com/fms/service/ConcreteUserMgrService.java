package com.fms.service;

import com.fms.dal.EmployeeDAO;
import com.fms.dal.TenantDAO;
import com.fms.user.Employee;
import com.fms.user.Tenant;

import java.sql.SQLException;


public class ConcreteUserMgrService implements UserMgrService {
    @Override
    public int createTenant(Tenant tenant) {
        int newId = 0;
        try{
            newId = TenantDAO.createTenant(tenant);
        }catch(SQLException e){
            System.out.println("ConcreteUserMgrService.createTenant() msg");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public int createEmployee(Employee employee) {
        int newId = 0;
        try{
            newId = EmployeeDAO.createEmployee(employee);
        }catch(SQLException e){
            System.out.println("ConcreteUserMgrService.createEmployee() msg");
            e.printStackTrace();
        }
        return newId;
    }
}
