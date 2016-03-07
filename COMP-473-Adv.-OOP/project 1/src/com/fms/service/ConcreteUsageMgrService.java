package com.fms.service;

import com.fms.dal.UsageDAO;
import com.fms.facility.IUnit;
import com.fms.facilityuse.IUsage;

import java.util.*;
import java.sql.SQLException;

public class ConcreteUsageMgrService implements UsageMgrService {


    @Override
    public int createUsage(IUsage usage, IUnit unit) {
        int newId = 0;
        try{
            newId = UsageDAO.createUsage(usage, unit);
        }catch(SQLException e){
            System.out.println("ConcreteUsageMgrService.createUsage() msg");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<IUsage> listUsagesForUnit(IUnit unit) {
        ArrayList<IUsage> u = new ArrayList<IUsage>();
        try{
            u = UsageDAO.listUsagesForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteUsageMgrService.listUsagesForUnit() ");
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public ArrayList<IUsage> listUsagesByUnitId(int unitId) {
        ArrayList<IUsage> u = new ArrayList<IUsage>();
        try{
            u = UsageDAO.listUsagesByUnitId(unitId);
        }catch (SQLException e){
            System.out.println("ConcreteUsageMgrService.listUsagesForUnit() ");
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void assignUnitToUse(IUnit unit, IUsage usage) {
        //Insert into Usage table- usage.startdate, usage.enddate , unitId, usage.owner



        unit.setVacancy(false);
       unit.addUsage(usage);
    }

    @Override
    public void vacateUnit(IUnit unit, IUsage usage, String date) {
        unit.setVacancy(true);
        usage.setEndDate(date);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isInUseDuringInterval(String startDate, String endDate) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
