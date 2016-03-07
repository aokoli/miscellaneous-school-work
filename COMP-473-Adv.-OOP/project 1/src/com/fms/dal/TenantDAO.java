package com.fms.dal;

import com.fms.user.ConcreteUser;
import com.fms.user.IUser;
import com.fms.user.Tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TenantDAO {

    public static int createTenant(Tenant tenant) throws SQLException{
        int returnId = 0;
        int userId = 0;
        userId = createUser(tenant);

        if(userId == 0){
            System.out.println("UserId couldn't be retrieved from TenantDAO.createUser() ");
        }
        else{
            Statement statement = DBHelper.getLocalConnection().createStatement();
            String sqlQuery = "Insert into Tenant (userId, income, previousLocation, currentEmployer) Values ('" +
                    userId + "', '" +
                    tenant.getIncome() + "', '" +
                    tenant.getPreviousLocation() + "', '" +
                    tenant.getCurrentEmployer() + "');";

            try{
                statement.execute(sqlQuery);
                String getNewId = "Select * from Tenant where Id = (Select Max(Id) from Tenant);";
                ResultSet rs = statement.executeQuery(getNewId);
                try{
                    while (rs.next()){
                        returnId = rs.getInt("Id");
                        tenant.setTenantId(returnId);
                    }
                }catch (Exception e){
                    System.out.println("TenantDAO.createTenant() returnId : " + e.getMessage());
                }finally {
                    rs.close();
                }
            }catch (Exception e) {
                System.out.println("TenantDAO.createTenant() msg: " + e.getMessage());
            }finally {
                DBHelper.closeConnection();
                statement.close();
            }
        }
        return returnId;
    }

    public static int createUser(Tenant tenant) throws SQLException{
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into User (firstName, lastName, address, phone, email) Values ('" +
                tenant.getFirstName() + "', '" +
                tenant.getLastName() + "', '" +
                tenant.getAddress() + "', '" +
                tenant.getPhone() + "', '" +
                tenant.getEmail() + "');";

        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from User where Id = (Select Max(Id) from User);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("TenantDAO.createUser() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e) {
            System.out.println("TenantDAO.createUser() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }

    public static IUser readUser(int id) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from User where Id = " + id + ";";
        IUser u = new ConcreteUser();

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try {
                while (resultSet.next()) {
                    u.setId(resultSet.getInt("Id"));
                    u.setFirstName(resultSet.getString("firstName"));
                    u.setLastName(resultSet.getString("lastName"));
                    u.setAddress(resultSet.getString("address"));
                    u.setPhone(resultSet.getString("phone"));
                    u.setEmail(resultSet.getString("email"));
                }
            }catch (Exception e){
                System.out.println("TenantDAO.readUser() resultset msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("TenantDAO.readUser() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return u;
    }

    public static Tenant readTenant(int id) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from tenant where Id = " + id + ";";
        Tenant t = new Tenant();
        int userid = 0;
        IUser u = new ConcreteUser();

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try {
                while (resultSet.next()) {
                    t.setTenantId(resultSet.getInt("Id"));
                    userid = resultSet.getInt("userId");
                    u = readUser(userid);
                    t.setFirstName(u.getFirstName());
                    t.setLastName(u.getLastName());
                    t.setAddress(u.getAddress());
                    t.setPhone(u.getPhone());
                    t.setEmail(u.getEmail());
                    t.setIncome(resultSet.getDouble("income"));
                    t.setPreviousLocation(resultSet.getString("previousLocation"));
                    t.setCurrentEmployer(resultSet.getString("currentEmployer"));
                }
            }catch (Exception e){
                System.out.println("TenantDAO.readTenant() resultset msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("TenantDAO.readTenant() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return t;
    }
}
