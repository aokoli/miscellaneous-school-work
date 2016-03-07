package com.fms.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.fms.facilityuse.IUsage;


public class ConcreteIntervalMgrService implements IntervalMgrService {
	
	//IUsage 
	UsageMgrService usageManager;
	
	
	public ConcreteIntervalMgrService() {
		this.usageManager = new ConcreteUsageMgrService();
	}

    /* 
     * TODO Try a more efficient method e.g. a "mysql compare dates" function to implement this method.
     * Also, for usage table, date changed from VAR to DATE. Change all other dates in db
	*/
	@Override
	public boolean isInUseDuringInterval(int unitId, String startDate, String endDate) {
		// Make sure sensible time interval is entered, otherwise returns false
		if (endDate.compareTo(startDate) < 0){
			System.out.println("Wrong input. Please make sure startDate is BEFORE endDate, in the format YYYY-MM-DD");
			return false;
		} // TODO Put in other checks to ensure sensible time interval is entered! e.g. if random String is entered, "Please enter date in the form YYYY-MM-DD"
		
		ArrayList<IUsage> usages = usageManager.listUsagesByUnitId(unitId);
		boolean isInUse = false;
		
		IUsage use;
        Iterator<IUsage> itr = usages.iterator();
        int count = 0;
        while (itr.hasNext()) {
            count++;
            use = itr.next();
            String dbStartDate = use.getStartDate(); 
            String dbEndDate = use.getEndDate(); 
           // System.out.println("Comparing dbSD: " + dbStartDate + " and dbED: " + dbEndDate); // TODO Debuggging
           // System.out.println("with        SD: " + startDate + " and   ED: " + endDate);  // TODO Debuggging
            
            // date1.compareTo(date2) > 0, means date1 is AFTER date2
            // date1.compareTo(date2) = 0, means date1 is THE SAME as date2
            // date1.compareTo(date2) < 0, means date1 is BEFORE as date2
            // Therefore, 
	        // If startDate is BEFORE DB startDate AND endDate is AFTER DB endDate, return true
	        if (startDate.compareTo(dbStartDate) < 0 && endDate.compareTo(dbEndDate) > 0) {
	        //   System.out.println("If startDate is BEFORE DB startDate AND endDate is AFTER DB endDate"); // TODO Remove. For debugging
	     	   return isInUse = true;
	        }
            
           // If startDate is BEFORE DB endDate AND endDate is AFTER DB endDate, return true
           if (startDate.compareTo(dbEndDate) < 0 && endDate.compareTo(dbEndDate) > 0) {
	        //   System.out.println("If startDate is BEFORE DB endDate AND endDate is AFTER DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is BEFORE DB endDate AND endDate is THE SAME AS DB endDate, return true
           if (startDate.compareTo(dbEndDate) < 0 && endDate.compareTo(dbEndDate) == 0) {
	       //    System.out.println("If startDate is BEFORE DB endDate AND endDate is THE SAME AS DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is BEFORE DB startDate AND endDate is BEFORE DB endDate AND
           // endDate is AFTER DB startDate, return true
           if (startDate.compareTo(dbStartDate) < 0 && endDate.compareTo(dbEndDate) < 0 && endDate.compareTo(dbStartDate) > 0) {
	       //    System.out.println("If startDate is BEFORE DB startDate AND endDate is BEFORE DB endDate AND endDate is AFTER DB startDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is BEFORE DB startDate AND endDate is THE SAME AS DB endDate, return true
           if (startDate.compareTo(dbStartDate) < 0 && endDate.compareTo(dbEndDate) == 0) {
	       //    System.out.println("If startDate is BEFORE DB startDate AND endDate is THE SAME AS DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is AFTER DB startDate AND endDate is BEFORE DB endDate, return true
           if (startDate.compareTo(dbStartDate) > 0 && endDate.compareTo(dbEndDate) < 0) {
	       //    System.out.println("If startDate is AFTER DB startDate AND endDate is BEFORE DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is AFTER DB startDate AND endDate is THE SAME AS DB endDate, return true
           if (startDate.compareTo(dbStartDate) > 0 && endDate.compareTo(dbEndDate) == 0) {
	       //    System.out.println("If startDate is AFTER DB startDate AND endDate is THE SAME AS DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
           // If startDate is THE SAME AS DB startDate AND endDate is THE SAME AS DB endDate, return true
           if (startDate.compareTo(dbStartDate) == 0 && endDate.compareTo(dbEndDate) == 0) {
	       //    System.out.println("If startDate is THE SAME AS DB startDate AND endDate is THE SAME AS DB endDate, return true"); // TODO Remove. For debugging
        	   return isInUse = true;
           }
           
        }
		
		return isInUse;
	}
}
