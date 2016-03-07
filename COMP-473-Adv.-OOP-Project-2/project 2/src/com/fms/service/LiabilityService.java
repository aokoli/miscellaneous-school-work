package com.fms.service;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fms.dao.HibernateMySQLHelper;
import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.inspection.IInspection;
import com.fms.maintenance.IMaintenance;
import com.fms.maintenance.IMaintenanceMgr;
import com.fms.maintenance.IRequest;
import com.fms.problem.IProblem;

public class LiabilityService  {
	// TODO Put unitId in problem table? For quicker retrievals?
	private ApplicationContext context = new FileSystemXmlApplicationContext("app-content.xml");
	private AdminService admin = (AdminService) context.getBean("adminService");
	private FacilityService facilityService = (FacilityService) context.getBean("facilityService");
	
	public LiabilityService() {	}

	public List<IProblem> listFacilityProblems(int unitId) {
		List<IProblem> problems = new ArrayList<IProblem>();
		
		// Return a sum of problems from Inspection and Maintenance
		
		// 1. Get problems from Inspection
		List<IInspection> inspections = admin.listInspections(unitId);
		
		// For each inspection, get associated problem
			for (IInspection inspection : inspections){
				// If inspection has problem, add it. If not, skip.
				if (inspection.getProblem() != null) {
					problems.add(inspection.getProblem());
				}
			}
		
		// 2. Get problems from maintenance
		List<IRequest> maintRequests = admin.listMaintRequests(unitId);
		
		// For each maintenance request, get associated problem
		for (IRequest maintReq : maintRequests){
			// Add maintenance request problem
			problems.add(maintReq.getProblem());
		}
		
		// 3. Return the combination of problems, stemming from maintenance and inspection
		return problems;
	}
	
	public int addProblem(IProblem problem, IUnit unit) {
		return 0;
	}
	
	public int addProblemByUnitId(IProblem problem, int unitId) {
		return 0;
	}
	
	
	
	public double calcMaintenanceCostForUnit(int unitId) {
		
		// Add up all the maintenance costs of the units; only units with isCostPaid = false
		double unitMaintenanceCost = 0;
		
		// For unit, get the maintenance cost (totaling all costs that isn't "isCostPaid" = true)
			
		// Get the list of maintenances in unit
		List<IMaintenance> maints = admin.listMaintenance(unitId);
		
		// For each maintenance, where isCostPaid = false, get the maintenance cost 
		for (IMaintenance maint : maints){
			
			// If maint cost isn't paid, then
			if (maint.isCostPaid() == false){
				 unitMaintenanceCost += maint.getServiceCost();
			}
		}
			
		
		return unitMaintenanceCost;
	}
	
	
	
	
	

	public double calcMaintenanceCostForFacility(int facilityId) {
		
		// Add up all the maintenance costs of the units; only units with isCostPaid = false
		double facilityMaintenanceCost = 0;
		
		// Get facility from DB 
		IFacility facility = facilityService.findFacilityById(facilityId);
		
		// Get units from facility
		Set<IUnit> units = facility.getUnits();
		
		// For each unit, get the maintenance cost (totaling all costs that isn't "isCostPaid" = true)
		for (IUnit unit : units) {
			
			// Compute maintCostForUnit
			
			/*
			
			// Get the list of maintenances in unit
			List<IMaintenance> maints = admin.listMaintenance(unit.getUnitId());
			
			// For each maintenance, where isCostPaid = false, get the maintenance cost 
			for (IMaintenance maint : maints){
				
				// If maint cost isn't paid, then
				if (!maint.isCostPaid()){
					 facilityMaintenanceCost += maint.getServiceCost();
				}
			}
			*/
			
		}
		
		return facilityMaintenanceCost;
	}

	public double calcProblemRateForFacility(IFacility facility) {
		// Algorithm: This method gets the problem rate for each unit (this calculation uses current & historical 
		// problems), adds it all up, the divides by total # of units to get facility problem rate.
		// E.g. Unit 1 prob rate = 1 +
		//		Unit 2 prob rate = 0 /  
		//		total # Units = 2
		/* TODO Alternatively, we can get the CURRENT occupant problem rate for a unit (calculated using only current problems)
		 * So, we would ignore historical problems, and only on the current occupant in 
		 * the facility (perhaps by using date intervals to conduct the same computation below) 
		 * */
		return 0;
	}
	
	
	
	public double calcProblemRateForUnit(int unitId) {
		
		
		// Add up all the maintenance costs of the units; only units with isCostPaid = false
		double unitProblemRate = 0, totalUnresolvedUnitProblems = 0, totalUnitProblemCases = 0;
		
		// Get the list of problems in unit
		List<IProblem> problems = listFacilityProblems(unitId);
		
		totalUnitProblemCases = problems.size();
		
		for (IProblem problem : problems) {
			
			// If problem is not resolved, then count
			if (problem.isResolved() == false){
				 totalUnresolvedUnitProblems++;
			}
		}
				
		unitProblemRate = totalUnresolvedUnitProblems/totalUnitProblemCases;
		
		
		
		
		return unitProblemRate;
		
		
	}
	
	
	
	public double calcDownTimeForUnit (int unitId) {
		
		
		
		return 0;
	}
	
	

	public double calcDownTimeForFacility(IFacility facility) throws SQLException {
		/*


		SELECT probDesc, SUM(DATEDIFF(problem.resolveDate, problem.probStartDate)) as TotalUnitDownTime
		FROM problem, unit, facility
		WHERE (facility.Id = 1 AND problem.unitId = unit.Id AND unit.facilityId = facility.Id);
		 
		 */
		
		
		
		/*
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
        
        return facilityDowntime; */
		
		return 0;
    }
	

}
