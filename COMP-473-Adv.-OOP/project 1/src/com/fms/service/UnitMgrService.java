package com.fms.service;

import com.fms.facility.IFacility;
import com.fms.facility.IUnit;

public interface UnitMgrService {

    public int createUnit(IUnit unit, IFacility fac);
    public IUnit readUnit(int id);
    public void updateUnit(IUnit unit);
    public void deleteUnit(IUnit unit);


}
