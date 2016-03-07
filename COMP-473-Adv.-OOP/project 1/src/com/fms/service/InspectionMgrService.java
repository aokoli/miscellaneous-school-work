package com.fms.service;


import com.fms.facility.IUnit;
import com.fms.facilityschedule.ISchedule;
import com.fms.inspection.IInspection;

import java.util.*;

public interface InspectionMgrService {

    public int createInspection(IInspection inspection, IUnit unit, ISchedule schedule);

    public ArrayList<IInspection> listInspectionsForUnit(IUnit unit);
}
