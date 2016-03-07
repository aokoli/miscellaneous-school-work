package com.fms.service;


import com.fms.dal.ProblemsDAO;
import com.fms.facility.IUnit;
import com.fms.problem.IProblem;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConcreteProblemMgrService implements ProblemMgrService{

    @Override
    public int createProblem(IProblem problem, IUnit unit) {
        int newId = 0;
        try{
            newId = ProblemsDAO.createProblem(problem, unit);
        }catch (Exception e){
            System.out.println("ConcreteProblemMgrService.createProblem() ");
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public ArrayList<IProblem> listProblemsForUnit(IUnit unit) {
        ArrayList<IProblem> p = new ArrayList<IProblem>();
        try{
            p = ProblemsDAO.listProblemsForUnit(unit);
        }catch (SQLException e){
            System.out.println("ConcreteInspectionMgrService.listProblemsForUnit() ");
            e.printStackTrace();
        }
        return p;
    }

	@Override
	public int createProblemByUnitId(IProblem problem, int unitId) {
		int newId = 0;
        try{
            newId = ProblemsDAO.createProblemByUnitId(problem, unitId);
        }catch (Exception e){
            System.out.println("ConcreteProblemMgrService.createProblemByUnitId() ");
            e.printStackTrace();
        }
        return newId;
	}
}
