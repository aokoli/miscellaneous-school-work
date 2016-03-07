package com.fms.facility;

import java.util.*;

import com.fms.facilityuse.*;

public class Facility implements IFacility {

	// property with historical value = Schedule + Collection<property> ??
    private int facilityId;
    private String name;
    private IEmployee manager;  // users? Employees (Managers, Technicians) etc. 
    private String officeAddress;
    private int size;
    private int numParkingSpace;
    private Set<IUnit> units;
	
    
	public int getFacilityId() {
		return facilityId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setFacilityId(int)
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getName()
	 */
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getManager()
	 */
	public IEmployee getManager() {
		return manager;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setManager(com.fms.facilityuse.IEmployee)
	 */
	public void setManager(IEmployee manager) {
		this.manager = manager;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getOfficeAddress()
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setOfficeAddress(java.lang.String)
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getSize()
	 */
	public int getSize() {
		return size;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setSize(int)
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getNumParkingSpace()
	 */
	public int getNumParkingSpace() {
		return numParkingSpace;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setNumParkingSpace(int)
	 */
	public void setNumParkingSpace(int numParkingSpace) {
		this.numParkingSpace = numParkingSpace;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#getUnits()
	 */
	public Set<IUnit> getUnits() {
		return units;
	}
	/* (non-Javadoc)
	 * @see com.fms.facility.IFacility#setUnits(java.util.Set)
	 */
	public void setUnits(Set<IUnit> units) {
		this.units = units;
	}

    
    

}
