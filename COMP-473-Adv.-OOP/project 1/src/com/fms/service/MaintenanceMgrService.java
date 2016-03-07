package com.fms.service;


import com.fms.facility.IUnit;

import com.fms.facilitymaintenance.*;
import com.fms.facilityschedule.ISchedule;

import java.util.*;


/** This interface is responsible for persisting <code>Maintenance</code> objects within the database,
 * as well as their retrievals. 
 * */
public interface MaintenanceMgrService {

    public int createMaintenance(IMaintenance maintenance, IUnit unit, ISchedule schedule);

    public ArrayList<IMaintenance> listMaintenanceForUnit(IUnit unit);
	
}
