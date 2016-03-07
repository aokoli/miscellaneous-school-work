package com.fms.dal;

import com.fms.facility.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FacilityDAO {

    public static int createFacility(IFacility f) throws SQLException{
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into Facility (name, managerId, officeaddress, size, numParkingSpaces) Values ('"
                                                + f.getName() + "', '"
                                                + f.getManager().getEmployeeId() + "', '"
                                                + f.getOfficeAddress() + "', '"
                                                + f.getSize() + "', '"
                                                + f.getNumParkingSpace() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from Facility where Id = (Select Max(Id) from Facility);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception ex){
                System.out.println("FacilityDAO.createFacility() returnId : " + ex.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e) {
            System.out.println("FacilityDAO: createFacility() msg: " + e.getMessage());
        } finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }

    //Problem in this function: IFacility = new HouseFacility! if Facility is abstract.. shouldnt be this way
    //to fix this, made Facility as concrete class.
    public static IFacility readFacility(int id) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery =  "Select * from Facility where Id = " + id +";";
        IFacility f = new Facility();
        int managerid = 0;
        //IFacility f = new HouseFacility();    //not a good idea?
        // if a database has House and Apt facilities as two different tables,
        // then we shouldnt have to create readFacility() for both of them.
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    f.setFacilityId(resultSet.getInt("Id"));
                    f.setName(resultSet.getString("name"));
                    managerid = resultSet.getInt("managerId");
                    f.setManager(EmployeeDAO.readEmployee(managerid));
                    f.setOfficeAddress(resultSet.getString("officeaddress"));
                    f.setSize(resultSet.getInt("size"));
                    f.setNumParkingSpace(resultSet.getInt("numParkingSpaces"));
                }
            }catch (Exception e){
                System.out.println("FacilityDAO.readFacility(): resultSet msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("FacilityDAO: readFacility() connection msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return f;
    }

    //to be completed
    public static void updateFacility(IFacility f) throws SQLException{
        //Statement statement = DBHelper.getLocalConnection().createStatement();
        try{

        }catch (Exception e){
            System.out.println("FacilityDAO: editFacility() msg: " + e.getMessage());
        }finally {

        }
    }

    //to be completed
    public static void deleteFacility(IFacility f) throws SQLException{
        //Statement statement = DBHelper.getLocalConnection().createStatement();
        try{

        }catch (Exception e){
            System.out.println("FacilityDAO: deleteFacility() msg: " + e.getMessage());
        }finally {

        }
    }

    public static ArrayList<IFacility> listFacilities() throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Facility;";
        ArrayList<IFacility> facilityList = new ArrayList<IFacility>();
        int managerid = 0;

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    IFacility f = new Facility();
                    f.setFacilityId(resultSet.getInt("Id"));
                    f.setName(resultSet.getString("name"));
                    managerid = resultSet.getInt("managerId");
                    f.setManager(EmployeeDAO.readEmployee(managerid));
                    f.setOfficeAddress(resultSet.getString("officeaddress"));
                    f.setSize(resultSet.getInt("size"));
                    f.setNumParkingSpace(resultSet.getInt("numParkingSpaces"));
                    facilityList.add(f);
                }
            }catch (Exception e){
                System.out.println("FacilityDAO.listFacilities() resultSet msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("FacilityDAO: listFacilities() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return facilityList;
    }

    public static ArrayList<IUnit> listUnits(IFacility facility) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Unit where facilityId = " + facility.getFacilityId() + ";";
        ArrayList<IUnit> unitList = new ArrayList<IUnit>();
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    IUnit u = new Unit();
                    u.setUnitId(resultSet.getInt("Id"));
                    u.setVacancy(resultSet.getBoolean("isVacant"));
                    unitList.add(u);
                }
            }catch (Exception e){
                System.out.println("FacilityDAO.listUnits() resultSet " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("FacilityDAO: listUnits() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return unitList;
    }

    /* *
     *  Initially, we designed- passing Object as a parameter.
     *  Here, I'm passing id instead of object.
     *  In Play documentation, it says passing complex type (type: Object, or List)
     *  is not a good idea. If in case, we have implicitly build QueryStringBindable.
     *  Since, we're not going to use Play and we're not sure how Spring handles this,
     *  I'm adding this function here.
     *  */
    public static ArrayList<IUnit> listUnitsForFacilityId(int f) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Unit where facilityId = " + f + ";";
        ArrayList<IUnit> unitList = new ArrayList<IUnit>();
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    IUnit u = new Unit();
                    u.setUnitId(resultSet.getInt("Id"));
                    u.setVacancy(resultSet.getBoolean("isVacant"));
                    unitList.add(u);
                }
            }catch (Exception e){
                System.out.println("FacilityDAO.listUnits() resultSet " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("FacilityDAO: listUnits() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return unitList;
    }
}
