package com.fms.service;

import com.fms.dal.MaintenanceDAO;
import com.fms.facility.IUnit;
import com.fms.facilitymaintenance.IMaintenance;
import com.fms.facilityschedule.ISchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcreteMaintenanceMgrService implements MaintenanceMgrService {
    @Override
    public int createMaintenance(IMaintenance maintenance, IUnit unit, ISchedule schedule) {
        int newId = 0;
        try{
            newId = MaintenanceDAO.createMaintenance(maintenance, unit, schedule);
        }catch (Exception e){
            System.out.println("ConcreteMaintenanceMgrService.createMaintenance() ");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<IMaintenance> listMaintenanceForUnit(IUnit unit) {
        ArrayList<IMaintenance> m = new ArrayList<IMaintenance>();
        try{
            m = MaintenanceDAO.listMaintenanceForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteMaintenanceMgrService.listMaintenanceForUnit() ");
            e.printStackTrace();
        }
        return m;
    }
}
