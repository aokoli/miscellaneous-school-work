package com.fms.service;

import java.util.ArrayList;

import com.fms.facility.IUnit;
import com.fms.facilitymaintenance.IMaintenance;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;



/** This interface is responsible for handling all maintenance administrative duties for 
 * a facility, such as recording maintenance service request, maintenance invoices/orders,
 * and the scheduling for a particular maintenance job. This interface persists the administrative 
 * details to the database as well as retrieves them.
 * */

public interface AdministrativeMgrService {
	
	public int makeFacilityMaintRequest(IRequest request, IUnit unit); /* Delegates work to ReqMgrSvs*/
	public int scheduleMaintenance(ISchedule schedule, IRequest request, IUnit unit); /* Delegates work to SchMgrSvs*/
	public ArrayList<IRequest> listMaintRequests(IUnit unit); /* Delegates work to ReqMgrSvs*/
	public ArrayList<IMaintenance> listMaintenance(IUnit unit); /* Delegates work to MaintMgrSvs*/

}
