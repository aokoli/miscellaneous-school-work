package com.fms.main;

import java.sql.SQLException;
import java.util.*;

import com.fms.dal.TruncateDAO;
import com.fms.facility.*;
import com.fms.facilitymaintenance.*;
import com.fms.facilityrequest.*;
import com.fms.facilityschedule.*;
import com.fms.facilityuse.*;
import com.fms.inspection.*;
import com.fms.problem.IProblem;
import com.fms.problem.Problem;
import com.fms.service.*;
import com.fms.user.Employee;
import com.fms.user.IUser;
import com.fms.user.Tenant;

public class Main {
	
	public static void main(String[] args) throws SQLException{

        /*
        IUser tenant1 = new Tenant("fname", "lname", "112 W Madison", "225-224-225", "abcc@test.com", 58000.0, "52 N Ave", "");
        List<IUser> list = new ArrayList<IUser>();
        list.add(tenant1);

        Tenant t1 = new Tenant("fname", "lname", "112 W Madison", "225-224-225", "abcc@test.com", 58000.0, "52 N Ave", "");
        t1.getFirstName();
        */


        //Persistence Managers:
        FacilityMgrService facManager = new ConcreteFacilityMgrService();
        UnitMgrService unitManager = new ConcreteUnitMgrService();
        RequestMgrService requestManager = new ConcreteRequestMgrService();
        ScheduleMgrService scheduleManager = new ConcreteScheduleMgrService();
        UsageMgrService usageManager = new ConcreteUsageMgrService();
        MaintenanceMgrService maintenanceManager = new ConcreteMaintenanceMgrService();
        InspectionMgrService inspectionManager = new ConcreteInspectionMgrService();
        UserMgrService userManager = new ConcreteUserMgrService();
        
        // AdministrativeMgrService is a wrapper. It was implemented SOLELY to explicitly show the methods
        // listMaintRequests(), listMaintenance(), makeFacilityMaintRequest(), and scheduleMaintenance()
        AdministrativeMgrService adminManager = new ConcreteAdministrativeMgrService();
        IntervalMgrService intervalManager = new ConcreteIntervalMgrService();
        LiabilitiesMaint liabManager = new ConcreteLiabilitiesMaint();



        //Create Many users in the DB first:

        Tenant tenant1 = new Tenant("Mary", "Jane", "254 Adler St Chicago", "225-241-5487", "mjane@test.com", 92000.0, "42 W Roosevelt", "Loyola");
        Tenant tenant2 = new Tenant("Phil", "Miser", "254 Adler St Chicago", "111-111-1111", "pmiser@test.com", 63000.0, "1004 prospect ave, Palatine", "Salesforce Org");
        Tenant tenant3 = new Tenant("Uj", "Franklin", "254 Adler St Chicago", "564-222-3333", "ufranklin@test.com", 85500.0, "67 Funny Pl, chicago", "Sheridan Mgmt");

        Employee employee1 = new Employee("John", "Smith", "254 Adler St Chicago", "124-214-1245", "jsmith@ourhousing.com", "02-04-2002", false, true, 40000.0);
        Employee manager1 = new Employee("John", "Doe", "254 Adler St Chicago", "123-425-1689", "jdoe@ourhousing.com", "05-01-1998", true, true, 88000.0);
        Employee manager2 = new Employee("Chris", "Cornell", "254 Adler St Chicago", "120-555-2547", "ccornell@ourhousing.com", "04-08-1992", true, true, 90000.0);
        Employee employee2 = new Employee("Bob", "Gomez", "254 Adler St Chicago", "558-578-9999", "bgomez@ourhousing.com", "06-22-1999", false, true, 52000.0);


        //Fill Database:
        userManager.createTenant(tenant1);
        userManager.createTenant(tenant2);
        userManager.createTenant(tenant3);

        userManager.createEmployee(employee1);
        userManager.createEmployee(employee2);
        userManager.createEmployee(manager1);
        userManager.createEmployee(manager2);



        // /*
        // Create Facility 1 object
        IFacility facility1 = new HouseFacility();
        facility1.setName("Housing Facility 1");
        facility1.setManager(manager1);
        facility1.setOfficeAddress("101 Foreman Rd, Chicago");
        facility1.setNumParkingSpace(24);
        facility1.setSize(8000);
  //    facility1.setFacilityId(1); // TODO <-- This is necessary here to print units during debugging
       




///*
        //Create Unit 1 object
        //Currently, field in House is not saved into database.
        //I think there would be a House table in db with unit Id as a foreign key
        //for this layer.
        IUnit unit1 = new House("houseSpecificField");
        unit1.setVacancy(true);

        //Create Unit 2 object
        IUnit unit2 = new House("houseSpecificField");
        unit2.setVacancy(true);

        // Add/inject Unit 1 and 2 into Facility 1 object
        facility1.addUnits(unit1);
        facility1.addUnits(unit2);


        // Persist Facility 1 object
        int facility1Id = facManager.createFacility(facility1);
        // Since ID's are auto-generated in database, obtain the ID for Facility 1 and associate with object
        facility1.setFacilityId(facility1Id);
        // Similar to facility 1 step above, persist Unit 1 and 2, obtain their IDs from database, and associate with 
        // their respective objects
        int unit1Id = unitManager.createUnit(unit1, facility1);
        unit1.setUnitId(unit1Id);
        int unit2Id = unitManager.createUnit(unit2, facility1);
        unit2.setUnitId(unit2Id);
        
        // Create Problem 1  
        IProblem problem1 = new Problem();
        problem1.setProbStartDate("2013-03-22");
        problem1.setProbDesc("Broken sink.");
        problem1.setIsResolved(true);
        problem1.setResolveDate("2013-03-27");
        
        // Create Problem 2
        IProblem problem2 = new Problem();
        problem2.setProbStartDate("2014-07-25");
        problem2.setProbDesc("Airconditioner is not cooling.");
        problem2.setIsResolved(true);
        problem2.setResolveDate("2014-08-27");
        
        // Associate Problems 1 and 2 with Unit 1 and persist them in database
        liabManager.addProblemByUnitId(problem1, 1);
        liabManager.addProblemByUnitId(problem2, 1);

        // Create Unit Usages
        IUsage usage1 = new Usage();
        usage1.setStartDate("2010-01-01");
        usage1.setEndDate("2011-12-31");
        usage1.setOwner(tenant1);

        IUsage usage2 = new Usage();
        usage2.setStartDate("2012-06-01");
        usage2.setEndDate("2013-05-31");
        usage2.setOwner(tenant2);

        IUsage usage3 = new Usage();
        usage3.setStartDate("2013-08-01");
        usage3.setEndDate("2014-07-01");
        usage3.setOwner(tenant2);


        // Associate the created Usages with Unit 1
        unit1.addUsage(usage1);
        unit1.addUsage(usage2);
        unit1.addUsage(usage3);

        // Persist the Usages 
        usageManager.createUsage(usage1, unit1);
        usageManager.createUsage(usage2, unit1);
        usageManager.createUsage(usage3, unit1);


        // Create Requests and associate them with Unit 1
        IRequest request1 = new Request();
        request1.setRequestDate("02-09-14");
        request1.setRequestedByTenant(tenant1);

        request1.setRequestType(RequestType.Maintenance);
        request1.setIsMaintenanceReqd(true);
        request1.setProblemDescription("Sink Maintenance");
        unit1.addRequest(request1);

        IRequest request2 = new Request();
        request2.setRequestDate("02-10-14");
        request2.setRequestedByEmployee(employee1);
        request2.setRequestType(RequestType.Inspection);
        request2.setIsMaintenanceReqd(false);
        request2.setProblemDescription("Plumbing Inspection");
        unit1.addRequest(request2);

        // Persist Requests
        int request1Id = requestManager.createRequest(request1, unit1);
        request1.setRequestId(request1Id);
        int request2Id = requestManager.createRequest(request2, unit1);
        request2.setRequestId(request2Id);

        // Create Schedules and persist - associating them with Unit 1
        ISchedule schedule1 = new Schedule();
        schedule1.setScheduleStartDate("2014-02-12");
        schedule1.setScheduleEndDate("2014-02-12");
        schedule1.setTechnician(employee1);
        
        unit1.addSchedule(schedule1);

        // Persist Schedule
        int schedule1Id = scheduleManager.createSchedule(schedule1, request1, unit1);
        schedule1.setScheduleId(schedule1Id);

        ISchedule schedule2 = new Schedule();
        schedule2.setScheduleStartDate("2014-02-05");
        schedule2.setScheduleEndDate("2014-02-05");
        schedule2.setTechnician(employee2);
        
        unit1.addSchedule(schedule2);

        int schedule2Id = scheduleManager.createSchedule(schedule2, request2, unit1);
        schedule2.setScheduleId(schedule2Id);

        // Create Maintenances and persist - associating them with Unit 1 
        IMaintenance maintenance1 = new Maintenance();
        maintenance1.setActualStartDate("2014-02-14");
        maintenance1.setActualEndDate("2014-02-14");
        maintenance1.setResolveDesc("Sink Clogged. Replaced with new.");
        maintenance1.setTechnician(employee1);
        maintenance1.setServiceCost(452.20);
        maintenance1.setIsCostPaid(true);
        
        IMaintenance maintenance2 = new Maintenance();
        maintenance2.setActualStartDate("2014-03-14");
        maintenance2.setActualEndDate("2014-03-18");
        maintenance2.setResolveDesc("Fixed airconditioner.");
        maintenance2.setTechnician(employee2);
        maintenance2.setServiceCost(350.70);
        maintenance2.setIsCostPaid(true);
        
        unit1.addMaintenance(maintenance1);
        unit1.addMaintenance(maintenance2);

        maintenanceManager.createMaintenance(maintenance1, unit1, schedule1);
        maintenanceManager.createMaintenance(maintenance2, unit1, schedule1);

        // Create Inspection and persist - associating it with Unit 1
        IInspection inspection1 = new Inspection();
        inspection1.setActualStartDate("2014-02-06");
        inspection1.setActualEndDate("2014-02-06");
        inspection1.setTechnician(employee1);
        inspection1.setIsMaintenanceReqd(true);
        inspection1.setProbDesc("Sink might need replacement");

        unit1.addInspection(inspection1);

        int inspection1Id = inspectionManager.createInspection(inspection1, unit1, schedule1);
        inspection1.setInspectionId(inspection1Id);

        // Create Facility 2 object
        IFacility facility2 = new HouseFacility();
        facility2.setName("Housing Facility 2");
        facility2.setManager(manager2);
        facility2.setOfficeAddress("101 Foreman Rd, Mobile");
        facility2.setNumParkingSpace(48);
        facility2.setSize(1500);

        // Persist Facility 2
        facManager.createFacility(facility2);
        
        
        /* ************************* PRINTING USING DATA FROM DATABASE ******************************************/

        System.out.println("******************************** PRINTING USING DATA FROM DATABASE ****************************************");
        
        // Facilities size
        System.out.println("NUMBER OF FACILITIES");
        List<IFacility> facilites = facManager.listFacilities();
        System.out.println("Facilities list size (# of facilities in database): " + facilites.size());
        System.out.println();

        // Display all facilities
        System.out.println("DISPLAY OF FACILITY 1");
        Iterator<IFacility> itr = facilites.iterator();
        IFacility facility;
        int count = 0;
        while(itr.hasNext()){
        	count++;
        	facility = itr.next();
            System.out.println("Facility " + count + " name: " + facility.getName() + " Manager: " + facility.getManager().getLastName() + ", " + facility.getManager().getFirstName());
        }
        System.out.println();
        
        // Display all units for a particular facility
        System.out.println("DISPLAY OF UNIT 1");
        List<IUnit> unitsForFacility1 = facManager.listUnits(facility1);
        IUnit unit;
        Iterator<IUnit> itr2 = unitsForFacility1.iterator();
        count = 0;
        while(itr2.hasNext()){
        	count++;
        	unit = itr2.next();
            System.out.println("Unit " + count + ": " + unit.getUnitId() + " Is it Vacant? " + unit.isVacant());
        }
        System.out.println();
        
        //Displays all requests for the Unit pulled from Database.
        System.out.println("DISPLAY REQUESTS FOR UNIT 1");
        List<IRequest> requestsForUnit1 = requestManager.listRequestsForUnit(unit1);
        IRequest req;
        Iterator<IRequest> itr3 = requestsForUnit1.iterator();
        count = 0;
        while(itr3.hasNext()){
            count++;
            req = itr3.next();
            System.out.println("Request " + count + ": " + req.getRequestId() + " Type? " + req.getRequestType() + " MaintReqd: " + req.getIsMaintenanceReqd() +
                                                " requestbyTenant: " + req.getRequestedByTenant().getFirstName() + " reqbyEmployee: " + req.getRequestedByEmployee().getFirstName());
        }
        System.out.println();

        //Displays all schedules for the Unit
        System.out.println("DISPLAY SCHEDULES FOR UNIT 1");
        List<ISchedule> schedulesForUnit1 = scheduleManager.listSchedulesForUnit(unit1);
        ISchedule sch;
        Iterator<ISchedule> itr4 = schedulesForUnit1.iterator();
        count = 0;
        while(itr4.hasNext()){
            count++;
            sch = itr4.next();
            System.out.println("Schedule " + count + ": " + sch.getScheduleId() + " starts at:  " + sch.getScheduleStartDate() +
                                     " Assigned to: " + sch.getTechnician().getFirstName() + " " + sch.getTechnician().getLastName());
        }
        System.out.println();

        //Displays all Usages for the Unit
        System.out.println("DISPLAY USAGE FOR UNIT 1");
        List<IUsage> usagesForUnit1 = usageManager.listUsagesForUnit(unit1);
        IUsage us;
        Iterator<IUsage> itr5 = usagesForUnit1.iterator();
        count = 0;
        while (itr5.hasNext()){
            count++;
            us = itr5.next();
            System.out.println("Usage " + count + ": " + us.getUsageId() + " started :  " + us.getStartDate() + " ended : " + us.getEndDate() + " owned by: " + us.getOwner().getFirstName());
        }
        System.out.println();

        //Displays all Maintenances for the Unit
        System.out.println("DISPLAY MAINTENACE FOR UNIT 1");
        List<IMaintenance> maintainancesForUnit1 = maintenanceManager.listMaintenanceForUnit(unit1);
        IMaintenance m;
        Iterator<IMaintenance> itr6 = maintainancesForUnit1.iterator();
        count = 0;
        while (itr6.hasNext()){
            count++;
            m = itr6.next();
            System.out.println("Maintenance " + count + ": " + m.getMaintenanceId() + " actualStartDate :  " + m.getActualStartDate() +
                                    " actualEndDate : " + m.getActualEndDate() + " resolveDesc: " + m.getResolveDesc() +
                                    " serviceCost: " + m.getServiceCost() + " technician: " + m.getTechnician().getFirstName());
        }
        System.out.println();


        //Displays all Inspections for the Unit
        System.out.println("DISPLAY INSPECTION FOR UNIT 1");
        List<IInspection> inspectionsForUnit1 = inspectionManager.listInspectionsForUnit(unit1);
        IInspection i;
        Iterator<IInspection> itr7 = inspectionsForUnit1.iterator();
        count = 0;
        while (itr7.hasNext()){
            count++;
            i = itr7.next();
            System.out.println("Inspection " + count + ": " + i.getInspectionId() + " actualStartDate :  " + i.getActualStartDate() +
                    " actualEndDate : " + i.getActualEndDate() + " probDesc: " + i.getProbDesc() +
                    " technician: " + i.getTechnician().getFirstName() + " MaintReqd? " + i.getIsMaintenanceReqd());
        }
        System.out.println();

        // Facility problem rate
        System.out.println("DISPLAY PROBLEM RATE FOR FACILITY 1");
        System.out.println("Problem Rate for facility 1: " + liabManager.calcProblemRateForFacility(facility1) * 100 + "%"); 
        System.out.println();
        
        // Facility downtime 
        System.out.println("DISPLAY DOWNTIME FOR FACILITY 1");
        System.out.println("Downtime for facility 1: " + liabManager.calcDownTimeForFacility(facility1) + " days");
        System.out.println();
        
        // Facility usage
        System.out.println("DISPLAY USAGE FOR UNIT 1");
        System.out.println("Is Unit 1 in use during 2009-01-01 through 2011-11-31? " + intervalManager.isInUseDuringInterval(1, "2009-01-01", "2011-11-31"));
        System.out.println("Is Unit 1 in use during 2012-01-01 through 2012-05-31? " + intervalManager.isInUseDuringInterval(1, "2012-01-01", "2012-05-31"));
        System.out.println();
        
        // Facility maintenance cost
        System.out.println("DISPLAY MAINTENANCE COST FOR FACILITY 1");
        System.out.println("Facility 1 total maintenance cost: " + liabManager.calcMaintenanceCostForFacility(facility1));
        System.out.println();
        
        System.out.println("************************************************ END *********************************************************");
        
//*/
	}

}
