package com.fms.service;

import java.util.ArrayList;

import com.fms.facility.IUnit;
import com.fms.facilitymaintenance.IMaintenance;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;

public class ConcreteAdministrativeMgrService implements AdministrativeMgrService {
	
	RequestMgrService reqMgrSvc;
	ScheduleMgrService schMgrSvc;
	MaintenanceMgrService maintMgrSvc;
	
	public ConcreteAdministrativeMgrService () {
		this.reqMgrSvc = new ConcreteRequestMgrService();
		this.schMgrSvc = new ConcreteScheduleMgrService();
		this.maintMgrSvc = new ConcreteMaintenanceMgrService();
	}
	
	@Override
	public int makeFacilityMaintRequest(IRequest request, IUnit unit) {
		return reqMgrSvc.createRequest(request, unit);
	}

	@Override
	public int scheduleMaintenance(ISchedule schedule, IRequest request, IUnit unit) {
		return schMgrSvc.createSchedule(schedule, request, unit);
	}

	@Override
	public ArrayList<IRequest> listMaintRequests(IUnit unit) {
		return reqMgrSvc.listRequestsForUnit(unit);
	}

	@Override
	public ArrayList<IMaintenance> listMaintenance(IUnit unit) {
		return maintMgrSvc.listMaintenanceForUnit(unit);
	}

}
