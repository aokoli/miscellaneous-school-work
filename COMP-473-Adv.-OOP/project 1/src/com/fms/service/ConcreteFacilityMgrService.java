package com.fms.service;

import com.fms.dal.FacilityDAO;
import com.fms.dal.InspectionDAO;
import com.fms.dal.ProblemsDAO;
import com.fms.dal.RequestDAO;
import com.fms.facility.*;
import com.fms.facilityrequest.IRequest;
import com.fms.facilityrequest.Request;
import com.fms.inspection.IInspection;
import com.fms.inspection.Inspection;
import com.fms.problem.IProblem;
import com.fms.problem.Problem;

import java.sql.SQLException;
import java.util.*;

public class ConcreteFacilityMgrService implements FacilityMgrService {

    @Override
    public int createFacility(IFacility facility) {
        int newId = 0;
        try {
            newId = FacilityDAO.createFacility(facility);
        } catch (SQLException e) {
            System.out.println("ConcreteFacilityManagerService: createFacility()");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public IFacility readFacility(int id) {
        IFacility f = new Facility();
        try {
            f = FacilityDAO.readFacility(id);
        } catch (SQLException e) {
            System.out.println("ConcreteFacilityManagerService: readFacility()");
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public void updateFacility(IFacility facility) {

    }

    @Override
    public void deleteFacility(IFacility facility) {

    }

    @Override
    public ArrayList<IFacility> listFacilities() {
        ArrayList<IFacility> f = new ArrayList<IFacility>();
        try{
            f = FacilityDAO.listFacilities();
        }catch (SQLException e){
            System.out.println("ConcreteFacilityManagerService: readFacility()");
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public ArrayList<IUnit> listUnits(IFacility f) {
        ArrayList<IUnit> u = new ArrayList<IUnit>();
        try{
            u = FacilityDAO.listUnits(f);
        }catch (SQLException e){
            System.out.println("ConcreteFacilityManagerService: readFacility()");
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public ArrayList<IUnit> listUnitsForFacilityId(int facId) {
        ArrayList<IUnit> u = new ArrayList<IUnit>();
        try{
            u = FacilityDAO.listUnitsForFacilityId(facId);
        }catch (SQLException e){
            System.out.println("ConcreteFacilityManagerService: readFacility()");
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public double calcProbRate(int facilityId) {
        int count = 0;
        ArrayList<IUnit> unitsList = new ArrayList<IUnit>();
        ArrayList<IProblem> problemsList = new ArrayList<IProblem>();
        try{
            unitsList = FacilityDAO.listUnitsForFacilityId(facilityId);

            IUnit unit = new Unit();
            Iterator<IUnit> unitIterator = unitsList.iterator();

            while (unitIterator.hasNext()){
                unit = unitIterator.next();

                try{
                    problemsList = ProblemsDAO.listProblemsForUnit(unit);

                    IProblem prob = new Problem();
                    Iterator<IProblem> problemsIterator = problemsList.iterator();
                    while(problemsIterator.hasNext()){
                        prob = problemsIterator.next();
                        if(prob.getIsResolved() == false){
                            count++;
                        }
                    }
                }catch (SQLException e){
                    System.out.println("ConcreteFacilityManagerService: calcProbRate()");
                    e.printStackTrace();
                }
            }

        }catch (SQLException e){
            System.out.println("ConcreteFacilityManagerService: calcProbRate()");
            e.printStackTrace();
        }
        return count;
    }

    //Problem is listed in request and inspection
    //The idea is either user will request maintenance when there is a problem..
    //a problem is found during inspection.

    /*
    @Override
    public double calcProbRate(int facId) {
        ArrayList<IUnit> unitsList = new ArrayList<IUnit>();
        ArrayList<IInspection> inspectionList = new ArrayList<IInspection>();
        ArrayList<IRequest> requestList = new ArrayList<IRequest>();
        int count = 0;
        //int total
        try{
            unitsList = FacilityDAO.listUnitsForFacilityId(facId);

            IUnit unit = new Unit();
            Iterator<IUnit> unitIterator = unitsList.iterator();

            while (unitIterator.hasNext()){
                unit = unitIterator.next();

                try{
                    inspectionList = InspectionDAO.listInspectionsForUnit(unit);

                    IInspection insp = new Inspection();
                    Iterator<IInspection> inspIterator = inspectionList.iterator();
                    while (inspIterator.hasNext()){
                        insp = inspIterator.next();
                        if (insp.getIsMaintenanceReqd() == true){
                            count++;
                        }
                    }

                    requestList = RequestDAO.listRequestsForUnit(unit);

                    IRequest req = new Request();
                    Iterator<IRequest> reqIterator = requestList.iterator();
                    while (reqIterator.hasNext()){
                        req = reqIterator.next();
                        if (req.getIsMaintenanceReqd() == true){
                            count++;
                        }
                    }

                }catch (SQLException e){
                    System.out.println("ConcreteFacilityManagerService: calcProbRate()");
                    e.printStackTrace();
                }
            }

        }catch (SQLException e){
            System.out.println("ConcreteFacilityManagerService: calcProbRate()");
            e.printStackTrace();
        }
        return count;
    }
   */

}
