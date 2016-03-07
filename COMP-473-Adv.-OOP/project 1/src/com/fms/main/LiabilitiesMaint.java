package com.fms.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.problem.IProblem;

/**
 * This interface is responsible for handling maintenance-related liabilities
 * for the facilities/units. It provides the client with facility problems, 
 * maintenance costs, down time, and problem rate. This interface retrieves
 * the necessary information needed to conduct its functions from the database.
 * */

public interface LiabilitiesMaint {
	
	public ArrayList<IProblem> listFacilityProblems(IUnit unit);  /* Delegates work to ReqMgrSvc*/
	int addProblem(IProblem problem, IUnit unit);
	public int addProblemByUnitId(IProblem problem, int unitId);
	public double calcMaintenanceCostForFacility(IFacility facility);
	public double calcProblemRateForFacility(IFacility facility);
	public double calcDownTimeForFacility(IFacility facility) throws SQLException;


}
