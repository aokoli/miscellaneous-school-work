	package com.fms.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fms.dao.HibernateMySQLHelper;
import com.fms.facility.IUnit;
import com.fms.facilityuse.IUsage;
import com.fms.inspection.IInspection;
import com.fms.inspection.IInspectionMgr;
import com.fms.maintenance.IMaintenance;
import com.fms.maintenance.IMaintenanceMgr;
import com.fms.maintenance.IRequest;
import com.fms.schedule.ISchedule;

public class AdminService {
	
	private ApplicationContext context = new FileSystemXmlApplicationContext("app-content.xml");
	
	public AdminService () { }
	
	/** Creates maintenance request. Returns the associated maintenance manager id */
	public int makeFacilityMaintRequest(int unitId, IRequest maintenanceRequest) {
		
		// Create blank maintenance mgr object, persist to DB, and obtain auto-gen id from DB
		IMaintenanceMgr maintMgr = (IMaintenanceMgr) context.getBean("maintenanceMgr");
		// Associate maintenance request with maintenance manager
		maintMgr.setMaintenanceRequest(maintenanceRequest);
		
		
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("*************** Adding maintenance request to DB ************************** ");
		System.out.println("*************** Creating maintenance manager in DB ************************ ");
		session.save(maintMgr);
		
		// Obtain Auto-generated MaintMgr id
		int autoGenMaintMgrId = maintMgr.getMaintenanceMgrId();   
		
		// Update the unitId attribute for the MaintMgr within DB
		Query query = session.createQuery("update MaintenanceMgrImpl set unitId = :unitId" +
											" where maintenanceMgrId = :maintenanceMgrId");
		query.setParameter("maintenanceMgrId", autoGenMaintMgrId);
		query.setParameter("unitId", unitId);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		
		System.out.println("*************** Created maintenance manager in DB ************************ ");
		System.out.println("*************** Saved maintenance request in DB ************************** ");
		
		return autoGenMaintMgrId;
	}

	public int scheduleMaintenance(int maintenanceMgrId, ISchedule schedule) {
		
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("*************** Adding maintenance schedule to DB ************************** ");
		session.save(schedule);
		System.out.println("*************** Associate maintenance schedule with maintenance mgr in DB ************************** ");

		// Obtain Auto-generated maintenance schedule id
		int autoGenMaintScheduleId = schedule.getScheduleId();   
		
		// Update the maintenanceScheduleId attribute for the MaintMgr within DB
		Query query = session.createQuery("update MaintenanceMgrImpl set maintenanceScheduleId = :maintenanceScheduleId" +
											" where maintenanceMgrId = :maintenanceMgrId");
		query.setParameter("maintenanceMgrId", maintenanceMgrId);
		query.setParameter("maintenanceScheduleId", autoGenMaintScheduleId);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		
		System.out.println("*************** Saved Maintenance Schedule. Associated with Maintenace Manager in DB ************************** ");
		
		return autoGenMaintScheduleId;
		
	}

	public List<IRequest> listMaintRequests(int unitId) {
		
		List<IRequest> maintRequests = new ArrayList<IRequest>(); // TODO bean?
		
		try {
			System.out.println("*************** Searching for unit information with ID ...  " + unitId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			Query getMaintMgrRequestQuery = session.createQuery("From MaintenanceMgrImpl where unitId=:unitId");		
			getMaintMgrRequestQuery.setInteger("unitId", unitId);
			
			
			//System.out.println("*************** Retrieve Query is ....>>\n" + getMaintMgrRequestQuery.toString()); 
			
			// Obtain list of maintenance managers associated with unit id
			List<IMaintenanceMgr> maintMgrs = getMaintMgrRequestQuery.list();
			
		
			// For each maintenance mgr, obtain the maintenance request
			for (IMaintenanceMgr maintMgr : maintMgrs){
				
				// Initializes the maint req (and problem) associated with the maint mgr
				Hibernate.initialize(maintMgr.getMaintenanceRequest().getProblem());
				
				// Collect maint req
				maintRequests.add(maintMgr.getMaintenanceRequest());
				
			}
			
			session.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return maintRequests;
	}

	public List<IMaintenance> listMaintenance(int unitId) {
		
		List<IMaintenance> maints = new ArrayList<IMaintenance>(); // TODO bean?
		
		try {
			System.out.println("*************** Searching for unit information with ID ...  " + unitId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			Query getMaintMgrRequestQuery = session.createQuery("From MaintenanceMgrImpl where unitId=:unitId");		
			getMaintMgrRequestQuery.setInteger("unitId", unitId);
			
			
			// System.out.println("*************** Retrieve Query is ....>>\n" + getMaintMgrRequestQuery.toString()); 
			
			// Obtain list of maintenance managers associated with unit id
			List<IMaintenanceMgr> maintMgrs = getMaintMgrRequestQuery.list();
			
		
			// For each maintenance mgr, obtain the maintenance 
			for (IMaintenanceMgr maintMgr : maintMgrs){
				
				// If unit doesn't have any maintenance, SKIP initialization. Else, null error.
				if (maintMgr.getMaintenance() != null){  // TODO Perform these checks everywhere 
					// Initializes the maint (and employee) associated with the maint mgr
					Hibernate.initialize(maintMgr.getMaintenance().getEmployee());
					
					// Collect maint 
					maints.add(maintMgr.getMaintenance());
				}
				
				
				
				
				
			}
			
			session.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return maints;
	}
	
	
	public List<IInspection> listInspections(int unitId){
		

		List<IInspection> inspections = new ArrayList<IInspection>(); // TODO bean?
		
		try {
			System.out.println("*************** Searching for unit information with ID ...  " + unitId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			Query getInspectMgrRequestQuery = session.createQuery("From InspectionMgrImpl where unitId=:unitId");		
			getInspectMgrRequestQuery.setInteger("unitId", unitId);
			
			
			// System.out.println("*************** Retrieve Query is ....>>\n" + getMaintMgrRequestQuery.toString()); 
			
			// Obtain list of inspection managers associated with unit id
			List<IInspectionMgr> inspectMgrs = getInspectMgrRequestQuery.list();
			
		
			// For each inspection mgr, obtain the inspection 
			for (IInspectionMgr inspectMgr : inspectMgrs){
				
				// Initializes the inspection (and problem) associated with the inspection mgr
				Hibernate.initialize(inspectMgr.getInspection().getProblem());
				
				// Collect inspection 
				inspections.add(inspectMgr.getInspection());
				
			}
			
			session.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return inspections;
		
	}
	
	
	
	
	
	
	
public List<IUsage> listActualUsage(int unitId){
	
	List<IUsage> usages = new ArrayList<IUsage>(); 
		
		try {
			System.out.println("*************** Searching for unit information with ID ...  " + unitId);
			Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			Query getUsagesQuery = session.createQuery("From UsageImpl where unitId=:unitId");		
			getUsagesQuery.setInteger("unitId", unitId);
			
			
			// Obtain list of usages associated with unit id
			List<IUsage> usagesDB = getUsagesQuery.list();
			
		//	/*
			// For each usage from DB
			for (IUsage usage : usagesDB){
				
				Hibernate.initialize(usage.getTenant());
				
				// Collect usage
				usages.add(usage);
				
			}
		//	*/
			
			session.getTransaction().commit();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return usages;

	}


public boolean getVacancy(int unitId){
	
	IUnit unit = null;
	
	try {
		System.out.println("*************** Searching for unit information with ID ...  " + unitId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		Query getVacancyQuery = session.createQuery("From Unit where unitId=:unitId");		
		getVacancyQuery.setInteger("unitId", unitId);
		
		
		// Obtain unit associated with unit id
		unit = (IUnit) getVacancyQuery.list().get(0);
		
		session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	// System.out.println("The result from getVacancy is wrong.");
	return unit.isVacant();
	
	
}



public boolean vacateFacility(int unitId, boolean isVacant){
	
	try {
		System.out.println("*************** Searching for unit information with ID ...  " + unitId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query setVacancyQuery = session.createQuery("update Unit set isVacant=:isVacant where unitId=:unitId");		
		setVacancyQuery.setInteger("unitId", unitId);
		setVacancyQuery.setBoolean("isVacant", isVacant);
		
		int result = setVacancyQuery.executeUpdate();
		
		session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	 return getVacancy(unitId);
}


public void assignFacilityToUse(int unitId, IUsage usage) {
	
	Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	System.out.println("*************** Adding usage to DB ************************** ");
	session.save(usage);
	
	// Get auto-generated usage Id
	int autoGenUsageId = usage.getUsageId();
	
	// Update the unitId attribute for the Usage within DB
	Query query = session.createQuery("update UsageImpl set unitId = :unitId" +
										" where usageId = :usageId");
	query.setParameter("usageId", autoGenUsageId);
	query.setParameter("unitId", unitId);
	int result = query.executeUpdate();
	
	session.getTransaction().commit();
	System.out.println("*************** Created usage in DB and associated it with " +  unitId + " ************************ ");


	// For associated unit, remove vacancy
	vacateFacility(unitId, false);


}

	
	
	
	
	

}
