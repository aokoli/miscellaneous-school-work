package com.fms.service;


import com.fms.facility.IUnit;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;

import java.util.*;

public interface ScheduleMgrService {

    public int createSchedule(ISchedule schedule, IRequest request, IUnit unit);

    public ArrayList<ISchedule> listSchedulesForUnit(IUnit unit);

    public ArrayList<ISchedule> listSchedulesByUnitId(int unitId);
}
