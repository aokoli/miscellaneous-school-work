package com.fms.dal;

import com.fms.facility.IFacility;
import com.fms.facility.IUnit;
import com.fms.facility.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UnitDAO {

    //Note: you can create a facility without a unit but not a unit without facility
    public static int createUnit(IUnit unit, IFacility fac) throws SQLException{
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into Unit (facilityId, isVacant) Values ('" +
                                            fac.getFacilityId() + "', '" +
                                            unit.isVacant() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from Unit where Id = (Select Max(Id) from Unit);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("UnitDAO.createUnit() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e) {
            System.out.println("UnitDAO: createUnit() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }

    public static IUnit readUnit(int id) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Unit where Id = " + id + ";";
        IUnit u = new Unit();

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try {
                while (resultSet.next()) {
                     u.setUnitId(resultSet.getInt("Id"));
                     u.setVacancy(resultSet.getBoolean("IsVacant"));
                }
            }catch (Exception e){
                System.out.println("UnitDAO.readUnit() resultset msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("UnitDAO.readUnit() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return u;
    }

    //to be completed
    public static void editUnit(IUnit u) throws SQLException{
        //Statement statement = DBHelper.getLocalConnection().createStatement();
        try{

        }catch (Exception e){
            System.out.println("UnitDAO: editUnit() msg: " + e.getMessage());
        }
    }

    //to be completed
    public static void deleteUnit(IUnit u) throws SQLException{
        //Statement statement = DBHelper.getLocalConnection().createStatement();
        try{

        }catch (Exception e){
            System.out.println("UnitDAO: deleteUnit() msg: " + e.getMessage());
        }
    }


}
