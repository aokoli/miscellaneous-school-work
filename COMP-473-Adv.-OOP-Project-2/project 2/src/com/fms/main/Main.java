package com.fms.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fms.dao.HibernateMySQLHelper;
import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.facilityuse.IEmployee;
import com.fms.facilityuse.IPerson;
import com.fms.facilityuse.ITenant;
import com.fms.facilityuse.IUsage;
import com.fms.facilityuse.UsageImpl;
import com.fms.inspection.IInspection;
import com.fms.inspection.IInspectionMgr;
import com.fms.maintenance.IMaintenance;
import com.fms.maintenance.IMaintenanceMgr;
import com.fms.maintenance.IRequest;
import com.fms.problem.IProblem;
import com.fms.schedule.ISchedule;
import com.fms.service.AdminService;
import com.fms.service.FacilityService;
import com.fms.service.LiabilityService;

public class Main {
	
	public static void main(String[] args){
		
		ApplicationContext context = new FileSystemXmlApplicationContext("app-content.xml");
	 // ApplicationContext context = new ClassPathXmlApplicationContext("app-content.xml"); // TODO Refac to make Spring file structure like Bookstore examples.

		
		//Create Person
		IPerson person1 = (IPerson) context.getBean("person");
		//person1.setPersonId(1);
		person1.setFirstName("Joe");
		person1.setLastName("Bobby");
		person1.setAddress("493 W California, Chicago");
		person1.setEmail("johnb@somemail.com");
		person1.setPhone("773 555 9999");
		
		// Create Employee
		IEmployee employee1 = (IEmployee) context.getBean("employee");
		//employee1.setEmployeeId(1);
		employee1.setManager(true);
		employee1.setSalary(75000);
		employee1.setPerson(person1); // Associate Person with Employee. 

		
        // Create Problem 1
        IProblem problem1 = (IProblem) context.getBean("problem");
        //problem1.setProblemId(1);
        problem1.setProblemDetails("Broken sink.");
        problem1.setResolved(true);
        problem1.setResolveDetails("Fixed broken sink, using spare parts.");
    
        
        // Associate Problem with MaintenanceRequest
        // Create maintenance request
        IRequest maintRequest = (IRequest) context.getBean("request");
        //maintRequest.setRequestId(1);
        maintRequest.setProblem(problem1);  // Associate problem with problem request
        maintRequest.setRequestDate("12-12-2011");
        maintRequest.setRequestedBy("Alex");
        
        
        // Create maintenance manager
        IMaintenanceMgr maintMgr = (IMaintenanceMgr) context.getBean("maintenanceMgr");
       //maintMgr.setMaintenanceMgrId(1);
        
        // Assign maintenance request to maintenance manager
        maintMgr.setMaintenanceRequest(maintRequest);
        
     // Create Facility 
        IFacility facility1 = (IFacility) context.getBean("facility");
        //facility1.setFacilityId(1);
        facility1.setName("Housing Facility 1");
        facility1.setManager(employee1);
        facility1.setOfficeAddress("101 Foreman Rd, Chicago");
        facility1.setNumParkingSpace(24);
        facility1.setSize(8000);
        
        
        // Create Units and associate with facility 1
    	IUnit unit1 = (IUnit) context.getBean("unit");
		unit1.setVacant(false);
		unit1.setFacility(facility1);
		
		IUnit unit2 = (IUnit) context.getBean("unit");
		//unit2.setUnitId(2);
		unit2.setVacant(false);
		unit2.setFacility(facility1);
		
		Set<IUnit> units = new HashSet<IUnit>();
		units.add(unit1);
		units.add(unit2);
		
		// Associate facility with Units
		facility1.setUnits(units);
		
		IUsage usage1 = (UsageImpl) context.getBean("usage");
		//usage1.setUsageId(1);
		usage1.setStartDate("2004-12-21");
		//usage1.setOwner("Emp");
		usage1.setEndDate("2006-12-20");
		
		usage1.setUnit(unit1);
		
		IUsage usage2 = (UsageImpl) context.getBean("usage");
		//usage2.setUsageId(2);
		usage2.setStartDate("2007-2-1");
		usage2.setEndDate("2008-1-31");
		//usage2.setOwner("Me2");
		usage2.setUnit(unit1);
		
		Set<IUsage> usages = new HashSet<IUsage>();
		usages.add(usage1);
		usages.add(usage2);
		
		// unit1.setUsages(usages); // TODO 
		
		Set<IMaintenanceMgr> maintenances = new HashSet<IMaintenanceMgr>();
		maintenances.add(maintMgr);
		
		unit1.setMaintenanceMgr(maintenances);
		
		maintMgr.setUnit(unit1);
		
		// Create inspection schedules
		ISchedule inspectionSchedule1 = (ISchedule) context.getBean("schedule");
		//inspectionSchedule1.setScheduleId(1);
		inspectionSchedule1.setStartDate("01-05-2012");
		inspectionSchedule1.setEndDate("06-06-2012");
		inspectionSchedule1.setEmployee(employee1);
		
		ISchedule inspectionSchedule2 = (ISchedule) context.getBean("schedule");
		//inspectionSchedule1.setScheduleId(1);
		inspectionSchedule2.setStartDate("07-05-2012");
		inspectionSchedule2.setEndDate("07-05-2012");
		inspectionSchedule2.setEmployee(employee1);
		
		// Create inspection 1
		IInspection inspection1 = (IInspection) context.getBean("inspection"); 
		//inspection1.setInspectionId(1);
		inspection1.setInspectionDetails("Checked bathroom and bedroom.");
		inspection1.setEmployee(employee1);
		
		// Create problem 2
	    IProblem problem2 = (IProblem) context.getBean("problem");
       // problem2.setProblemId(2);
        problem2.setProblemDetails("Wiring in kitchen is bad.");
        problem2.setResolved(false);
        
		
		// Create inspection 2
		IInspection inspection2 = (IInspection) context.getBean("inspection"); 
		//inspection2.setInspectionId(2);
		inspection2.setInspectionDetails("Checked kitchen.");
		inspection2.setProblem(problem2); // Associate problem 2 with inspection 2
		inspection2.setEmployee(employee1);
		
		// Create inspection manager 1
		IInspectionMgr inspectMgr1 = (IInspectionMgr) context.getBean("inspectionMgr");
		// Populate inspection manager
		inspectMgr1.setInspection(inspection1);
		inspectMgr1.setInspectionSchedule(inspectionSchedule1);
		//inspectMgr1.setInspectionMgrId(1);

		
		// Create inspection manager 2
		IInspectionMgr inspectMgr2 = (IInspectionMgr) context.getBean("inspectionMgr");
		// Populate inspection manager 2
		inspectMgr2.setInspection(inspection2);
		
		Set<IInspectionMgr> inspections = new HashSet<IInspectionMgr>();
		inspections.add(inspectMgr1);
		inspections.add(inspectMgr2);
		
		unit1.setInspectionMgr(inspections);
		
		inspectMgr1.setUnit(unit1);
		inspectMgr2.setUnit(unit1);
		
		IPerson person2 = (IPerson) context.getBean("person");
		//person2.setPersonId(2);
		person2.setFirstName("Joe2");
		person2.setLastName("Bobby2");
		person2.setAddress("493 W California, Chicago");
		person2.setEmail("johnb2@somemail.com");
		person2.setPhone("773 555 9999");
		
		/*
		Set<IUsage> usageForTenant1 = new HashSet<IUsage>();
		usageForTenant1.add(usage1);
		Set<IUsage> usageForTenant3 = new HashSet<IUsage>();
		usageForTenant3.add(usage2);
		*/
		ITenant tenant2 = (ITenant) context.getBean("tenant");
		tenant2.setIncome(42000);
		tenant2.setRent(1500);
		tenant2.setPerson(person2);
		// tenant2.setUsages(usages);
				
		usage1.setTenant(tenant2);
		
		IPerson person3 = (IPerson) context.getBean("person");
		//person3.setPersonId(3);
		person3.setFirstName("Joe3");
		person3.setLastName("Bobby3");
		person3.setAddress("493 W California, Chicago");
		person3.setEmail("johnb3@somemail.com");
		person3.setPhone("773 555 9999");
		
		ITenant tenant3 = (ITenant) context.getBean("tenant");
		tenant3.setIncome(42000);
		tenant3.setRent(1500);
		tenant3.setPerson(person3);
				
		//usage1.setTenant(tenant2);
		usage2.setTenant(tenant3);
		
		
		// Create maintenance and associate with maintenance manager
		IMaintenance maint1 = (IMaintenance) context.getBean("maintenance");
		maint1.setServiceCost(1220.0);
		maint1.setCostPaid(false);
		maint1.setEmployee(employee1);			
		maintMgr.setMaintenance(maint1);
		

		
		System.out.println("********************* ADDING NEW FACILITY ************************");
	//  	/*
	// 	FacilityService fs = (FacilityService) context.getBean("facilityService");
	//	fs.addNewFacility(facility1);
		
	   // Save objects manually
		Session session = HibernateMySQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(facility1);
	//	/*
		session.save(tenant2);
		session.save(usage1);
		session.save(tenant3);
		session.save(usage2);
	//			
		session.getTransaction().commit();
	//	*/
		
		
		System.out.println("********************* UTILITY METHODS ************************");
		AdminService admin = (AdminService) context.getBean("adminService");
		
	//	/*
		// Create problem 3
	    IProblem problem3 = (IProblem) context.getBean("problem");
        problem3.setProblemDetails("Air filter needs replacement.");
        problem3.setResolved(false);
		
	      // Create maintenance request 2
        IRequest maintRequest2 = (IRequest) context.getBean("request");
        //maintRequest.setRequestId(1);
        maintRequest2.setProblem(problem3);  // Associate problem with problem request
        maintRequest2.setRequestDate("04-06-2013");
        maintRequest2.setRequestedBy("Somto");  
        
    	
		System.out.println("********************* MAKING MAINTENANCE REQUEST ************************");
    	admin.makeFacilityMaintRequest(1, maintRequest2); // TODO Change to makeUnitMaint...
    	
    	ISchedule schedule = (ISchedule) context.getBean("schedule");
    	schedule.setStartDate("05-09-2005");
    	schedule.setEndDate("05-09-2007");
    	
    	
		System.out.println("********************* MAKING MAINTENANCE SCHEDULE ************************");
		admin.scheduleMaintenance(1, schedule);

		
		System.out.println("********************* LISTING MAINTENANCE REQUEST FOR UNIT 1 ************************");
		List<IRequest> maintRequests = admin.listMaintRequests(1);
		for (IRequest maintReq : maintRequests){
			System.out.println( "Maintenance Request Id: "	+ maintReq.getRequestId() +
								" | Request Date: " 	    + maintReq.getRequestDate() +
								" | Request By: " 			+ maintReq.getRequestedBy() +			
								" | Problem Details: " 		+ maintReq.getProblem().getProblemDetails() +
								" | Resolved?: " 			+ maintReq.getProblem().isResolved() +
								" | Resolve Details: " 		+ maintReq.getProblem().getResolveDetails());
		}
		
		
		
		System.out.println("********************* LISTING MAINTENANCE FOR UNIT 1 ************************");
		List<IMaintenance> maints = admin.listMaintenance(1);
		for (IMaintenance maint : maints){
			System.out.println( "Maintenance Id: "						+ maint.getMaintenanceId() +
								" | Service Cost : " 	    			+ maint.getServiceCost() +
								" | Employee Id (Problem Resolver): "	+ maint.getEmployee().getEmployeeId());  // TODO Problem solver should be in Problem. Or pick from Schedule.
		}
		
		
		
		System.out.println("********************* LISTING INSPECTION FOR UNIT 1 ************************");
		List<IInspection> inspects = admin.listInspections(1);
		for (IInspection inspection : inspects){
			
			// If inspection has a problem, print this:
			if (inspection.getProblem() != null){
				
				System.out.println( "Inspection Id: "	+ inspection.getInspectionId() +
						" | Inspection Details: " 	    + inspection.getInspectionDetails() +
						" | Employee Id (Inspector): "	+ inspection.getEmployee().getEmployeeId() +  // TODO Inspector or Problem Solver? Why Employee Id in Schedule?
						" | Problem Details: " 			+ inspection.getProblem().getProblemDetails() +
						" | Resolved?: " 				+ inspection.getProblem().isResolved() +
						" | Resolve Details: " 			+ inspection.getProblem().getResolveDetails());
			
			// If inspection has no problem, print this:
			} else {
				
				System.out.println( "Inspection Id: "	+ inspection.getInspectionId() +
						" | Inspection Details: " 	    + inspection.getInspectionDetails() +
						" | Employee Id (Inspector): "	+ inspection.getEmployee().getEmployeeId()); // TODO Inspector or Problem Solver?
			}
		}
		
		
		LiabilityService liabService = (LiabilityService) context.getBean("liabilityService");
		System.out.println("********************* LISTING PROBLEMS FOR UNIT 1 ************************");
		List<IProblem> problems = liabService.listFacilityProblems(1);
		for (IProblem problem : problems){
			System.out.println(	"Problem Id: "				+ problem.getProblemId() +
								" | Problem Details: " 		+ problem.getProblemDetails() +
								" | Resolved?: " 			+ problem.isResolved() +
								" | Resolve Details: " 		+ problem.getResolveDetails());
		}  // TODO Problem table should have unitId. 1. Fast retrieval. 2. Incase problem is put in DB without Inspection or MaintenanceReq
		
		
		
		
		System.out.println("********************* CALCULATING MAINTENANCE COST FOR UNIT 1 ************************");
		// double maintCost = liabService.calcMaintenanceCostForFacility(1);
		 double maintCost = liabService.calcMaintenanceCostForUnit(1);
		
		System.out.println("Unit 1's Maintenance Cost = " + maintCost);
		
		
		
		System.out.println("********************* CALCULATING PROBLEM RATE FOR UNIT 1 ************************");
		double problemRate = liabService.calcProblemRateForUnit(1);
		
		System.out.println("Unit 1's Problem Rate = " + problemRate * 100 + "%");
		
		
		
		
		
		System.out.println("********************* LISTING USAGES FOR UNIT 1 ************************");
		List<IUsage> usagesDB = admin.listActualUsage(1);
		for (IUsage usage : usagesDB){
			System.out.println( "Usage Id: "		+ usage.getUsageId() +
								" | Tenant Id: " 	+ usage.getTenant().getTenantId() +
								" | Start Date: " 	+ usage.getStartDate() +
								" | End Date: "		+ usage.getEndDate());  
		}
		
		
		System.out.println("********************* VACATING UNIT 1 ************************");
		boolean vacancy = admin.getVacancy(1);
		System.out.println("Is Unit 1 currently vacant? " + vacancy);
		System.out.println("Vacating facility...");
		vacancy = admin.vacateFacility(1, true);
		System.out.println("Is Unit 1 currently vacant? " + vacancy);
		
		
		
		System.out.println("********************* ASSIGNING UNIT 1 TO USE ************************");
		
		IUsage usage3 = (IUsage) context.getBean("usage");
		usage3.setStartDate("01-01-2005");
		usage3.setEndDate("12-12-2005");
		usage3.setOwner("Bo");
	//	usage3.setTenant(tenant3);
		admin.assignFacilityToUse(1, usage3);
		
	//	/*
		System.out.println("Re-listing usages: ");
		usagesDB = admin.listActualUsage(1);
		for (IUsage usage : usagesDB){
			System.out.println( "Usage Id: "		+ usage.getUsageId() +
	//							" | Tenant Id: " 	+ usage.getTenant().getTenantId() +
								" | Start Date: " 	+ usage.getStartDate() +
								" | End Date: "		+ usage.getEndDate());  
		}
	//	*/

	//	*/
		
		
		
		
		System.out.println("Done!");
		((FileSystemXmlApplicationContext) context).close(); 
	}
	
	

}
