package com.fms.facility;

import java.util.Set;

import com.fms.facilityuse.IEmployee;

public interface IFacility {

	public int getFacilityId();

	public void setFacilityId(int facilityId);

	public String getName();

	public void setName(String name);

	public IEmployee getManager();

	public void setManager(IEmployee manager);

	public String getOfficeAddress();

	public void setOfficeAddress(String officeAddress);

	public int getSize();

	public void setSize(int size);

	public int getNumParkingSpace();

	public void setNumParkingSpace(int numParkingSpace);

	public Set<IUnit> getUnits();

	public void setUnits(Set<IUnit> units);

}