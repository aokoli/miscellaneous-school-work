package com.fms.service;

import com.fms.facility.IUnit;
import com.fms.facilityuse.IUsage;
import java.util.*;

public interface UsageMgrService {

    public int createUsage(IUsage usage, IUnit unit);

    public ArrayList<IUsage> listUsagesForUnit(IUnit unitId);

    public ArrayList<IUsage> listUsagesByUnitId(int unitId);

    public void assignUnitToUse(IUnit unitId, IUsage usage);
    public void vacateUnit(IUnit unit, IUsage usage, String date);
    public boolean isInUseDuringInterval(String startDate, String endDate);

}
