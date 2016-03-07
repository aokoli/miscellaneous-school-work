package com.fms.service;

import com.fms.dal.InspectionDAO;
import com.fms.facility.IUnit;
import com.fms.facilityschedule.ISchedule;
import com.fms.inspection.IInspection;

import java.sql.SQLException;
import java.util.*;

public class ConcreteInspectionMgrService implements InspectionMgrService {

    @Override
    public int createInspection(IInspection inspection, IUnit unit, ISchedule schedule) {
        int newId = 0;
        try{
            newId = InspectionDAO.createInspection(inspection, unit, schedule);
        }catch (Exception e){
            System.out.println("ConcreteInspectionMgrService.createInspection() ");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<IInspection> listInspectionsForUnit(IUnit unit) {
        ArrayList<IInspection> l = new ArrayList<IInspection>();
        try{
            l = InspectionDAO.listInspectionsForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteInspectionMgrService.listInspectionsForUnit() ");
            e.printStackTrace();
        }
        return l;
    }
}
