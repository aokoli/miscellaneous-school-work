package com.fms.service;

import com.fms.facility.IFacility;
import com.fms.facility.IUnit;

import java.util.*;

/**
 * Responsible for editing facilities from the database (adding 
 * and removing), as well as listing the entire facility list 
 * contained in database.
 * */

public interface FacilityMgrService {

    public int createFacility(IFacility facility);
    public IFacility readFacility(int id);
    public void updateFacility(IFacility facility);
    public void deleteFacility(IFacility facility);
	public ArrayList<IFacility> listFacilities();
    public ArrayList<IUnit> listUnits(IFacility facility);
    public ArrayList<IUnit> listUnitsForFacilityId(int facId);

    /* *
     *  Initially, we designed- passing Object as a parameter.
     *  Here, I'm passing id instead of object.
     *  In Play documentation, it says passing complex type (type: Object, or List)
     *  is not a good idea. If in case, we have implicitly build QueryStringBindable.
     *  Since, we're not going to use Play and we're not sure how Spring handles this,
     *  I'm adding this function here.
     *  */

    public double calcProbRate(int facilityId);

  }
