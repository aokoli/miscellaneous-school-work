package com.fms.service;


import com.fms.facility.IUnit;
import com.fms.facilityrequest.IRequest;

import java.util.*;

public interface RequestMgrService {

    public int createRequest(IRequest request, IUnit unit);

    public ArrayList<IRequest> listRequestsForUnit(IUnit unit);

    public ArrayList<IRequest> listRequestsByUnitID(int unitId);
}
