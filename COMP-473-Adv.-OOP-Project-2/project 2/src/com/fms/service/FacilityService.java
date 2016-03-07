package com.fms.service;

import com.fms.dao.FacilityHibernateDAO;
import com.fms.facility.IFacility;

public class FacilityService {
	
private FacilityHibernateDAO facilityDAO = new FacilityHibernateDAO();
	
	
	//SEARCH (RETRIEVE/SELECT) facility by ID from the DB
	public IFacility findFacilityById(int facilityId) {
			
		try {
			IFacility facility = facilityDAO.retrieveFacility(facilityId);
	    	return facility;
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw a Exception retrieving facility.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	//INSERT a new facility in the DB
	public void addNewFacility(IFacility facility) {
		
		try {
			facilityDAO.addFacility(facility);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw a Exception retrieving facility.");
	      System.err.println(se.getMessage());
	    }
	}
	
	//DELETE a facility from DB
	public void removeFacility(IFacility facility) {
			
			try {
				facilityDAO.deleteFacility(facility);
		    } catch (Exception se) {
		      System.err.println("FacilityService: Threw a Exception retrieving facility.");
		      System.err.println(se.getMessage());
		    }
	}

}
