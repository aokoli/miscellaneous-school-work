package com.fms.dal;


import com.fms.facility.IUnit;
import com.fms.facilityuse.IUsage;
import com.fms.facilityuse.Usage;
import com.fms.user.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UsageDAO {

    public static int createUsage(IUsage usage, IUnit unit) throws SQLException{
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into UnitUsage (unitId, startDate, endDate, ownerId) Values ('" +
                                                    unit.getUnitId() + "', '" +
                                                    usage.getStartDate() + "', '" +
                                                    usage.getEndDate() + "', '" +
                                                    usage.getOwner().getTenantId() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from UnitUsage where Id = (Select Max(Id) from UnitUsage);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("UsageDAO.createUsage() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e) {
            System.out.println("UsageDAO.createUsage() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }

    public static ArrayList<IUsage> listUsagesForUnit(IUnit unit) throws SQLException{
        ArrayList<IUsage> usageList = new ArrayList<IUsage>();
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from UnitUsage where unitId = " + unit.getUnitId() + ";";

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    IUsage u = new Usage();
                    u.setUsageId(resultSet.getInt("Id"));
                    u.setStartDate(resultSet.getString("startDate"));
                    u.setEndDate(resultSet.getString("endDate"));
                    u.setOwner(TenantDAO.readTenant(resultSet.getInt("ownerId")));
                    usageList.add(u);
                }
            }catch (Exception e){
                System.out.println("UsageDAO.listUsagesForUnit() resultSet " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("UsageDAO.listUsagesForUnit() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return usageList;
    }

    public static ArrayList<IUsage> listUsagesByUnitId(int unitId) throws SQLException{
        ArrayList<IUsage> usageList = new ArrayList<IUsage>();
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from UnitUsage where unitId = " + unitId + ";";

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while (resultSet.next()){
                    IUsage u = new Usage();
                    u.setUsageId(resultSet.getInt("Id"));
                    u.setStartDate(resultSet.getString("startDate"));
                    u.setEndDate(resultSet.getString("endDate"));
                    Tenant t = TenantDAO.readTenant(resultSet.getInt("ownerId"));
                    u.setOwner(t);
                    usageList.add(u);
                }
            }catch (Exception e){
                System.out.println("UsageDAO.listUsagesForUnit() resultSet " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("UsageDAO.listUsagesForUnit() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return usageList;
    }
}
