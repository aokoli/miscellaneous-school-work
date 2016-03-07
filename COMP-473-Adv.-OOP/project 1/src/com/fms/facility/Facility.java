package com.fms.facility;

import com.fms.user.Employee;

import java.util.*;

//making this abstract affects the persistence.
//check FacilityDAO.readFacility for details.
public class Facility implements IFacility {

    private int facilityId;
    private String name;
    private Employee manager;
    private String officeAddress;
    private int size;
    private int numParkingSpace;
    private List<IUnit> units = new ArrayList<IUnit>();

    @Override
    public int getFacilityId(){
        return facilityId;
    }

    @Override
    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Employee getManager() {
        return manager;
    }

    @Override
    public void setManager(Employee manager){
        this.manager = manager;
    }

    @Override
    public String getOfficeAddress() {
        return officeAddress;
    }

    @Override
    public void setOfficeAddress(String officeAddr) {
        this.officeAddress = officeAddr;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getNumParkingSpace() {
        return numParkingSpace;
    }

    @Override
    public void setNumParkingSpace(int numParkingSpace) {
        this.numParkingSpace = numParkingSpace;
    }

    @Override
    public void addUnits(IUnit unit) {
        units.add(unit);
    }

    @Override
    public List<IUnit> listUnit() {
        return units;
    }

}
