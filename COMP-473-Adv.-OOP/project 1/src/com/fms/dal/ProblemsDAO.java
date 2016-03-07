package com.fms.dal;


import com.fms.facility.IUnit;
import com.fms.problem.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProblemsDAO {

    public static int createProblem(IProblem problem, IUnit unit) throws SQLException {
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into Problem (unitId, probStartDate, resolveDate, probDesc, IsResolved) Values ('" +
                                            unit.getUnitId() + "' , '" +
                                            problem.getProbStartDate() + "' , '" +
                                            problem.getResolveDate() + "' , '" +
                                            problem.getProbDesc() + "' , '" +
                                            problem.getIsResolved() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from Problem where Id = (Select Max(Id) from Problem);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("ProblemDAO.createProblem() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e){
            System.out.println("ProblemDAO.createProblem() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }
    
    

    public static int createProblemByUnitId(IProblem problem, int unitId) throws SQLException {
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into Problem (unitId, probStartDate, resolveDate, probDesc, IsResolved) Values ('" +
                                            unitId + "' , '" +
                                            problem.getProbStartDate() + "' , '" +
                                            problem.getResolveDate() + "' , '" +
                                            problem.getProbDesc() + "' , '" +
                                            problem.getIsResolved() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from Problem where Id = (Select Max(Id) from Problem);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("ProblemDAO.createProblemByUnitId() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e){
            System.out.println("ProblemDAO.createProblemByUnitId() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }
    
    

    public static ArrayList<IProblem> listProblemsForUnit(IUnit unit) throws SQLException{
        ArrayList<IProblem> problemList = new ArrayList<IProblem>();
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Problem where unitId = " + unit.getUnitId() + ";";
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while(resultSet.next()){
                    IProblem p = new Problem();
                    p.setProblemId(resultSet.getInt("Id"));
                    p.setProbStartDate(resultSet.getString("probStartDate"));
                    p.setResolveDate(resultSet.getString("resolveDate"));
                    p.setProbDesc(resultSet.getString("probDesc"));
                    p.setIsResolved(resultSet.getBoolean("IsResolved"));
                    problemList.add(p);
                }
            }catch (Exception e){
                System.out.println("ProblemDAO.listProblemsForUnit() resultSet : " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch(Exception e){
            System.out.println("ProblemDAO.listProblemsForUnit()) : " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return problemList;
    }
}
