package com.fms.dal;


import com.fms.facility.IUnit;
import com.fms.facilityschedule.ISchedule;
import com.fms.inspection.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class InspectionDAO {

    public static int createInspection(IInspection inspection, IUnit unit, ISchedule schedule) throws SQLException {
        int returnId = 0;
        Statement statement = DBHelper.getLocalConnection().createStatement();
        int employeeid = 1;
        if(inspection.getTechnician() != null){
            employeeid = inspection.getTechnician().getEmployeeId();
        }
        String sqlQuery = "Insert into Inspection (unitId, scheduleId, actualStartDate, actualEndDate, technicianId, maintenanceReqd, probDesc) Values ('" +
                unit.getUnitId() + "','" +
                schedule.getScheduleId() + "','" +
                inspection.getActualStartDate() + "','" +
                inspection.getActualEndDate() + "','" +
                employeeid + "','" +
                inspection.getIsMaintenanceReqd() + "','" +
                inspection.getProbDesc() + "');";
        try{
            statement.execute(sqlQuery);
            String getNewId = "Select * from Inspection where Id = (Select Max(Id) from Inspection);";
            ResultSet rs = statement.executeQuery(getNewId);
            try{
                while (rs.next()){
                    returnId = rs.getInt("Id");
                    inspection.setInspectionId(returnId);
                }
            }catch (Exception e){
                System.out.println("InspectionDAO.createInspection() returnId : " + e.getMessage());
            }finally {
                rs.close();
            }
        }catch (Exception e){
            System.out.println("InspectionDAO.createInspection() msg: " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return returnId;
    }

    public static ArrayList<IInspection> listInspectionsForUnit(IUnit unit) throws SQLException{
        ArrayList<IInspection> inspectionList = new ArrayList<IInspection>();
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = "Select * from Inspection where unitId = " + unit.getUnitId() + ";";
        int employeeid = 1;
        try{
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            try{
                while(resultSet.next()){
                    IInspection i = new Inspection();
                    i.setInspectionId(resultSet.getInt("Id"));
                    i.setActualStartDate(resultSet.getString("actualStartDate"));
                    i.setActualEndDate(resultSet.getString("actualEndDate"));
                    employeeid = resultSet.getInt("technicianId");
                    i.setTechnician(EmployeeDAO.readEmployee(employeeid));
                    i.setIsMaintenanceReqd(resultSet.getBoolean("maintenanceReqd"));
                    i.setProbDesc(resultSet.getString("probDesc"));
                    inspectionList.add(i);
                }
            }catch (Exception e){
                System.out.println("InspectionDAO.listInspectionsForUnit() resultSet : " + e.getMessage());
            }finally {
                resultSet.close();
            }
        }catch(Exception e){
            System.out.println("InspectionDAO.listInspectionsForUnit() : " + e.getMessage());
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
        return inspectionList;
    }

}
