package com.fms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.fms.dal.DBHelper;
import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.facilitymaintenance.IMaintenance;
import com.fms.main.LiabilitiesMaint;
import com.fms.problem.IProblem;
import com.fms.problem.Problem;

public class ConcreteLiabilitiesMaint implements LiabilitiesMaint {
	
	// RequestMgrService reqMgrService;
	ProblemMgrService probMgrService;
	FacilityMgrService facMgrService;
	MaintenanceMgrService maintMgrService;
	
	public ConcreteLiabilitiesMaint() {
		this.probMgrService = new ConcreteProblemMgrService();
		this.facMgrService = new ConcreteFacilityMgrService();
		this.maintMgrService = new ConcreteMaintenanceMgrService();
	}

	@Override
	public ArrayList<IProblem> listFacilityProblems(IUnit unit) {
//		return reqMgrService;
		return probMgrService.listProblemsForUnit(unit);
	}
	
	@Override
	public int addProblem(IProblem problem, IUnit unit) {
		return probMgrService.createProblem(problem, unit);
	}
	
	@Override
	public int addProblemByUnitId(IProblem problem, int unitId) {
		return probMgrService.createProblemByUnitId(problem, unitId);
	}

	@Override
	public double calcMaintenanceCostForFacility(IFacility facility) {
		
		// Add up all the maintenance costs of the units; only units with isCostPaid = false
		
		double facilityMaintenanceCost = 0;
		
		// Get all facility units
		List<IUnit> units = facMgrService.listUnits(facility);
		
		// For each unit, get the total maintenance cost i.e. maintenance costs that isCostPaid = false
		for (IUnit unit : units){
			// Get maintenance cost for each unit // TODO Make more efficient by pulling ONLY cost & isCostPaid from DB
			List<IMaintenance> unitMaints = maintMgrService.listMaintenanceForUnit(unit);
			
			Iterator<IMaintenance> itr = unitMaints.iterator();
			IMaintenance m;
			while(itr.hasNext()){
				m = itr.next();
				
				System.out.println("Unit " + unit.getUnitId() + " maint cost: "+ m.getServiceCost());
				
				if (m.getIsCostPaid()){
					facilityMaintenanceCost += m.getServiceCost();
				}
			}
			
		}
		
		return facilityMaintenanceCost;
	}

	@Override
	public double calcProblemRateForFacility(IFacility facility) {
		// Algorithm: This method gets the problem rate for each unit (this calculation uses current & historical 
		// problems), adds it all up, the divides by total # of units to get facility problem rate.
		/* TODO Alternatively, we can get the CURRENT occupant problem rate for a unit (calculated using only current problems)
		 * So, we would ignore historical problems, and only on the current occupant in 
		 * the facility (perhaps by using date intervals to conduct the same computation below) 
		 * */
		
				double facilityProblemRate = 0;
				
				// Get all facility units
				List<IUnit> units = facMgrService.listUnits(facility);
				int totalNumOfUnits = units.size();
				double totalUnitProblemRate = 0;
				
				// For each unit, get the problem rate i.e. problems that isResolved = false
				
				double unitProblemRate = 0;
				
				for (IUnit unit : units){
					
					// Get problem cases for each unit 
					List<IProblem> unitProbCases = probMgrService.listProblemsForUnit(unit);
					//System.out.println("Unit ProbCasesObject: " + unitProbCases); // TODO Remove. debugging.
					int totalUnitProblemCases = unitProbCases.size(); // TODO As suggested above, Make for current unit usage time frame
					int totalUnresolvedUnitProblems = 0;
					
					if (totalUnitProblemCases > 0){
						
						// Compute problem rate for each unit, and sum it all up to get the total unit problem rate
						Iterator<IProblem> itr = unitProbCases.iterator();
						IProblem p;
						while(itr.hasNext()){
							p = itr.next();
							if (p.getIsResolved()){
								totalUnresolvedUnitProblems++;
							//	System.out.println("Unit " + unit.getUnitId() + " problem id: "+ p.getProblemId() + " totalUnresolved: " + totalUnresolvedUnitProblems); // TODO Remove. Debugging.
							}
						}
						
						unitProblemRate = totalUnresolvedUnitProblems/totalUnitProblemCases;
						totalUnitProblemRate += unitProblemRate;
						// facilityProblemRate += unitProblemRate/totalNumOfUnits;
					}
					
				}
				
		return facilityProblemRate = totalUnitProblemRate/totalNumOfUnits;
	}

	@Override
	public double calcDownTimeForFacility(IFacility facility) throws SQLException {
		/*


SELECT probDesc, SUM(DATEDIFF(problem.resolveDate, problem.probStartDate)) as TotalUnitDownTime
FROM problem, unit, facility
WHERE (facility.Id = 1 AND problem.unitId = unit.Id AND unit.facilityId = facility.Id);
		 
		 */
        double facilityDowntime = 0;

        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "SELECT SUM(DATEDIFF(problem.resolveDate, problem.probStartDate)) as TotalUnitDownTime " +
                          "FROM problem, unit, facility " + 
                          "WHERE (facility.Id = " + facility.getFacilityId() + " AND problem.unitId = unit.Id AND unit.facilityId = facility.Id);";
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while(resultSet.next()){
                    facilityDowntime = resultSet.getDouble("TotalUnitDownTime");
                }
            }catch (Exception e){
                System.out.println("ConcreteLiabilitiesMaint.calcDownTimeForFacility() resultSet : " + e.getMessage());
            }finally {
                resultSet.close();
            }
            
        }catch(Exception e){
            System.out.println("ConcreteLiabilitiesMaint.calcDownTimeForFacility()) : " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        
        return facilityDowntime;
    }
		
		
		


}
