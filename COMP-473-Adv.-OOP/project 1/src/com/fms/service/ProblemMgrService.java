package com.fms.service;


import com.fms.facility.IUnit;
import com.fms.problem.IProblem;

import java.util.ArrayList;

public interface ProblemMgrService {

    public int createProblem(IProblem problem, IUnit unit);

    public int createProblemByUnitId(IProblem problem, int unitId);

    public ArrayList<IProblem> listProblemsForUnit(IUnit unit);
}