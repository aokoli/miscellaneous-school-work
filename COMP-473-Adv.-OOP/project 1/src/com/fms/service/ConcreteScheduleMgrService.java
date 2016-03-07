package com.fms.service;

import com.fms.dal.ScheduleDAO;
import com.fms.facility.IUnit;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcreteScheduleMgrService implements ScheduleMgrService {
    @Override
    public int createSchedule(ISchedule schedule, IRequest request, IUnit unit) {
        int newId = 0;
        try{
            newId = ScheduleDAO.createSchedule(schedule, request, unit);
        }catch (Exception e){
            System.out.println("ConcreteScheduleMgrService.createSchedule() ");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<ISchedule> listSchedulesForUnit(IUnit unit) {
        ArrayList<ISchedule> s = new ArrayList<ISchedule>();
        try{
            s = ScheduleDAO.listSchedulesForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteScheduleMgrService.listSchedulesForUnit() ");
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public ArrayList<ISchedule> listSchedulesByUnitId(int unitId) {
        ArrayList<ISchedule> s = new ArrayList<ISchedule>();
        try{
            s = ScheduleDAO.listSchedulesByUnitId(unitId);
        }catch (SQLException e){
            System.out.println("ConcreteScheduleMgrService.listSchedulesForUnit() ");
            e.printStackTrace();
        }
        return s;
    }
}
