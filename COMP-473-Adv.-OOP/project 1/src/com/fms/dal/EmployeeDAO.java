package com.fms.dal;


import com.fms.user.ConcreteUser;
import com.fms.user.Employee;
import com.fms.user.IUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {

    public static int createEmployee(Employee employee) throws SQLException {
        int returnId = 0;
        int userId = 0;
        userId = createUser(employee);

        if(userId == 0){
            System.out.println("UserId couldn't be retrieved from EmployeeDAO.createUser() ");
        }
        else{
            Statement statement = DBHelper.getLocalConnection().createStatement();
            String sqlQuery = "Insert into Employee (userId, joinDate, isActive, isManager, salary) Values ('" +
                    userId + "', '" +
                    employee.getJoinDate() + "', '" +
                    employee.getActive() + "', '" +
                    employee.getManager() + "', '" +
                    employee.getSalary() + "');";

            try{
                statement.execute(sqlQuery);
                String getNewId = "Select * from Employee where Id = (Select Max(Id) from Employee);";
                ResultSet rs = statement.executeQuery(getNewId);
                try{
                    while (rs.next()){
                        returnId = rs.getInt("Id");
                        employee.setEmployeeId(returnId);
                    }
                }catch (Exception e){
                    System.out.println("EmployeeDAO.createEmployee() returnId : " + e.getMessage());
                }finally {
                    rs.close();
                }
            }catch (Exception e) {
                System.out.println("EmployeeDAO.createEmployee() msg: " + e.getMessage());
            }finally {
                DBHelper.closeConnection();
                statement.close();
            }
        }
        return returnId;
    }

    public static int createUser(Employee employee) throws SQLException{
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Insert into User (firstName, lastName, address, phone, email) Values ('" +
                employee.getFirstName() + "', '" +
                employee.getLastName() + "', '" +
                employee.getAddress() + "', '" +
                employee.getPhone() + "', '" +
                employee.getEmail() + "');";

        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from User where Id = (Select Max(Id) from User);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                }
            }catch (Exception e){
                System.out.println("employeeDAO.createUser() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e) {
            System.out.println("employeeDAO.createUser() msg: " + e.getMessage());
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
                System.out.println("employeeDAO.readUser() resultset msg: " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception e) {
            System.out.println("employeeDAO.readUser() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return u;
    }

    public static Employee readEmployee(int id) throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Employee where Id = " + id + ";";
        Employee e = new Employee();
        int userid = 0;
        IUser u = new ConcreteUser();

        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try {
                while (resultSet.next()) {
                    e.setEmployeeId(resultSet.getInt("Id"));
                    userid = resultSet.getInt("userId");
                    u = readUser(userid);
                    e.setFirstName(u.getFirstName());
                    e.setLastName(u.getLastName());
                    e.setAddress(u.getAddress());
                    e.setPhone(u.getPhone());
                    e.setEmail(u.getEmail());
                    e.setActive(resultSet.getBoolean("isActive"));
                    e.setManager(resultSet.getBoolean("isManager"));
                    e.setSalary(resultSet.getDouble("salary"));
                }
            }catch (Exception ex){
                System.out.println("employeeDAO.readEmployee() resultset msg: " + ex.getMessage());
            }finally {
                resultSet.close();
            }
        }catch (Exception ex) {
            System.out.println("employeeDAO.readEmployee() msg: " + ex.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return e;
    }

}
