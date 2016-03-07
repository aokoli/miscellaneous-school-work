package com.fms.dal;

import java.sql.SQLException;
import java.sql.Statement;

/* Doesn't Work */
public class TruncateDAO {

    public void truncateAll() throws SQLException{
        Statement statement = DBHelper.getLocalConnection().createStatement();
        String sqlQuery = " Set Foreign_Key_Checks = 0;  truncate Schedule; truncate Request; truncate Unit; truncate facility; Set Foreign_Key_Checks = 1;  ";

        try{
            statement.execute(sqlQuery);
        }catch (Exception e){
            System.out.println("TruncateDAO.truncateAll()");
        }finally {
            DBHelper.closeConnection();
            statement.close();
        }
    }

}
