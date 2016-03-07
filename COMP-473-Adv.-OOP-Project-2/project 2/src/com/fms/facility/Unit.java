package com.fms.facility;


import java.util.*;

import com.fms.facilityuse.ITenant;
import com.fms.facilityuse.IUsage;
import com.fms.inspection.IInspectionMgr;
import com.fms.maintenance.IMaintenanceMgr;


/*import com.fms.facilitymaintenance.IMaintenance;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;
import com.fms.inspection.IInspection;
import com.fms.problem.IProblem;*/

//making this abstract affects the persistence.
//check FacilityDAO.readFacility for details.
public class Unit implements IUnit {

    private int unitId;
    private boolean isVacant;
    private Set<IUsage> usages;
    private IFacility facility;
    private Set<IMaintenanceMgr> maintenanceMgr;
    private Set<IInspectionMgr> inspectionMgr;
   // private List<IUsage> usageList = new ArrayList<IUsage>();
    
    /*
    private List<IRequest> requestList = new ArrayList<IRequest>();
    private List<ISchedule> scheduleList =new ArrayList<ISchedule>();
    private List<IMaintenance> maintenanceList =new ArrayList<IMaintenance>();
    private List<IInspection> inspectionList =new ArrayList<IInspection>();
    private List<IProblem> problemList = new ArrayList<IProblem>();
    */
	/* (non-Javadoc)
	 * @see com.fms.facility.IUnit2#getUnitId()
	 */
    public IFacility getFacility(){
    	return facility;
    }
    public void setFacility(IFacility fac){
    	this.facility = fac;
    }
	public int getUnitId() {
		return unitId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IUnit2#setUnitId(int)
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IUnit2#isVacant()
	 */
	public boolean isVacant() {
		return isVacant;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IUnit2#setVacant(boolean)
	 */
	public void setVacant(boolean isVacant) {
		this.isVacant = isVacant;
	}
	
	public Set<IUsage> getUsages() {
		return usages;
	}

	public void setUsages(Set<IUsage> usages) {
		this.usages = usages;
	}
	
	public Set<IMaintenanceMgr> getMaintenanceMgr() {		
		return maintenanceMgr;
	}
	
	public void setMaintenanceMgr(Set<IMaintenanceMgr> maintmgr) {		
		this.maintenanceMgr = maintmgr;
	}
	
	public Set<IInspectionMgr> getInspectionMgr() {
		return inspectionMgr;
	}
	
	public void setInspectionMgr(Set<IInspectionMgr> inspecmgr) {
		this.inspectionMgr = inspecmgr;		
	}	

}
