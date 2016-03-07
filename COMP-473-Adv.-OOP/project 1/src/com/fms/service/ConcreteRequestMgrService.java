package com.fms.service;

import com.fms.dal.RequestDAO;
import com.fms.facility.IUnit;
import com.fms.facilityrequest.IRequest;

import java.sql.SQLException;
import java.util.*;


public class ConcreteRequestMgrService implements RequestMgrService {
    @Override
    public int createRequest(IRequest request, IUnit unit) {
        int newId = 0;
        try{
            newId = RequestDAO.createRequest(request, unit);
        }catch (Exception e){
            System.out.println("ConcreteRequestMgrService.createRequest() ");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<IRequest> listRequestsForUnit(IUnit unit) {
        ArrayList<IRequest> r = new ArrayList<IRequest>();
        try{
            r = RequestDAO.listRequestsForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteRequestMgrService.listRequestsForUnit() ");
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public ArrayList<IRequest> listRequestsByUnitID(int unitId) {
        ArrayList<IRequest> r = new ArrayList<IRequest>();
        try{
            r = RequestDAO.listRequestsByUnitID(unitId);
        }catch (SQLException e){
            System.out.println("ConcreteRequestMgrService.listRequestsForUnit() ");
            e.printStackTrace();
        }
        return r;
    }
}
