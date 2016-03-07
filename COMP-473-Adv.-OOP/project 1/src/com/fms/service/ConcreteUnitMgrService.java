package com.fms.service;

import com.fms.dal.UnitDAO;
import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.facility.Unit;

import java.sql.SQLException;

public class ConcreteUnitMgrService implements UnitMgrService {

    @Override
    public int createUnit(IUnit unit, IFacility fac) {
        int newId = 0;
        try{
             newId = UnitDAO.createUnit(unit, fac);
         }catch(SQLException e){
             System.out.println("ConcreteUnitMgrService.createUnit() msg");
             e.printStackTrace();
         }
        return newId;
    }

    @Override
    public IUnit readUnit(int id) {
        IUnit u = new Unit();
        try{
            u = UnitDAO.readUnit(id);
        }catch(SQLException e){
            System.out.println("ConcreteUnitMgrService.readUnit() msg");
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void updateUnit(IUnit unit) {

    }

    @Override
    public void deleteUnit(IUnit unit) {

    }
}
