
package lk.ijse.nrsms.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.nrsms.DB.DBConnection;
import lk.ijse.nrsms.model.Attendence;


public class AttendenceController {

    public static Attendence SearchSID(String requestSID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        ResultSet set = conn.prepareStatement("SELECT * FROM Attendence WHERE AID = + ' " +requestSID+"'").executeQuery();
            
        if(set.next()){
        return new Attendence(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                set.getString(4)
        );
    }
        return null;
    
    
    }

    public boolean saveAttendence(Attendence a1) throws SQLException, ClassNotFoundException {
        
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement stm = conn.prepareStatement("INSERT INTO Attendence VALUES(?,?,?,?)");
        stm.setString(1, a1.getAID());
        stm.setString(2, a1.getSID());
        stm.setString(3, a1.getADate());
        stm.setString(4, a1.getAStatus()
        );
        
        return stm.executeUpdate()>0;
    }

    public Attendence getAttendence(String requestSID) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        ResultSet set = conn.prepareStatement("SELECT * FROM Attendence WHERE AID = + ' " +requestSID+"'").executeQuery();
        
        if(set.next()){
        return new Attendence(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                set.getString(4)
        );
    }
        return null;
    }

    public boolean dropAttendence(String requestAID) throws SQLException, ClassNotFoundException {
       Connection conn = DBConnection.getInstance().getConnection();
       return conn.prepareStatement("DELETE FROM Attendence WHERE AID =+'"+requestAID+"'").executeUpdate()>0;
    }

    public boolean updateState(Attendence a1) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement stm = conn.prepareStatement("UPDATE Attendence SET SID=?,ADate=?,AStatus=? WHERE AID=?");
        
        
        stm.setString(1, a1.getSID());
        stm.setString(2, a1.getADate());
        stm.setString(3, a1.getAStatus());
        stm.setString(4, a1.getAID());
        
        return stm.executeUpdate()>0;  
    }
    
}
