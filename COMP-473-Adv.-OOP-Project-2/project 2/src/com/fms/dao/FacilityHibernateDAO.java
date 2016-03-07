package com.fms.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.maintenance.IMaintenanceMgr;

public class FacilityHibernateDAO {
	
	
	public void addFacility(IFacility facility) {
		System.out.println("*************** Adding facility information in DB with ID ...  " + facility.getFacilityId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(facility);
		session.getTransaction().commit();
	}
	
	public void deleteFacility(IFacility facility) {
		System.out.println("*************** Deleteing facility information in DB with ID ...  " + facility.getFacilityId());
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(facility);
		session.getTransaction().commit();
	}
	
	public IFacility retrieveFacility(int facilityId) {
		try {
		System.out.println("*************** Searcing for facility information with ID ...  " + facilityId);
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		Query getFacilityQuery = session.createQuery("From Facility where facilityId=:facilityId");		
		getFacilityQuery.setInteger("facilityId", facilityId);
		
		System.out.println("*************** Retrieve Query is ....>>\n" + getFacilityQuery.toString()); 
		
		List facilities = getFacilityQuery.list();
		
		IFacility retrievedFacility = (IFacility) facilities.get(0);
		
	
		// Initializes each unit associated with the facility
		Hibernate.initialize(retrievedFacility.getUnits());
		
		/*	
		// Get all actual facility units
		Set<IUnit> units = retrievedFacility.getUnits();
		
		// For each unit in facility, initialize maintenance manager
		for (IUnit unit : units){
			Hibernate.initialize(unit.getMaintenanceMgr());
			
			// Get actual maintenance mgr for unit
			IMaintenanceMgr maintMgr = (IMaintenanceMgr) unit.getMaintenanceMgr();
			
			// Initialize maintenance in maintenance mgr
			Hibernate.initialize(maintMgr.getMaintenance());
			
		}
		*/
		
		session.getTransaction().commit();
		return retrievedFacility;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
