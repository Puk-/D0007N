package ahs;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;

/** 
 * Communicate with database
 * @author Filip
 */
public class AppState {  
    
    public int getHighestID() throws SQLException{
        Connection cn = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("SELECT MAX(ID) FROM Matter"); 
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
            return -1;
        }catch(ClassNotFoundException | SQLException ex){
            throw new SQLException("Problem med db: " + ex.getMessage());
        }finally{
            if (cn!=null){
                cn.close();
            }
        }
    }
    
    public String updateMatter(Matter m) throws SQLException{
        String sRet = "failure";
        Connection cn = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("UPDATE Matter SET CONTENT = ?, COMMENT = ?, STATUS = ? WHERE ID = ?");
            stmt.setString(1, m.getContent());
            stmt.setString(2, m.getComment());
            stmt.setInt(3, m.getStatus());
            stmt.setInt(4, m.getId());
            
            int i = stmt.executeUpdate();
            
            if (i > 0) sRet = "Success";
            return sRet;
        }catch(ClassNotFoundException | SQLException ex){
            throw new SQLException("Problem med db: " + ex.getMessage());
        }finally{
            if (cn!=null){
                cn.close();
            }
        }
    }
    
    
    public String addMatter(Matter m) throws SQLException{
        String sRet = "failure";
        Connection cn = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("INSERT INTO Matter (id, content) VALUES (?,?)");
            stmt.setInt(1, m.getId());
            stmt.setString(2, m.getContent());
            
            int i = stmt.executeUpdate();
            
            if (i > 0) sRet = "Success";
            return sRet;
        }catch(ClassNotFoundException | SQLException ex){
            throw new SQLException("Problem med db: " + ex.getMessage());
        }finally{
            if (cn!=null){
                cn.close();
            }
        }
    }
    
    /**
     * Fetch matters
     * @return
     * @throws SQLException 
     */
    public ArrayList<Matter> getMatter() throws SQLException{
        Connection cn = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("SELECT * FROM Matter");
            
            ResultSet rs = stmt.executeQuery();
            ArrayList<Matter>matterList = new ArrayList<>();
            
            while(rs.next()){
                Matter m = new Matter(0);
                m.setId(rs.getInt("ID"));
                m.setEmployeeID(rs.getInt("EMPLOYEEID"));
                m.setStatus(rs.getInt("STATUS"));
                m.setTimeSpentHours(rs.getInt("TIMESPENTHOURS"));
                m.setTimeSpentMinutes(rs.getInt("TIMESPENTMINUTES"));
                m.setDate(rs.getString("DATE"));
                m.setContent(rs.getString("CONTENT"));
                m.setComment(rs.getString("COMMENT"));
                matterList.add(m);  
            }
            return matterList;
        }catch(ClassNotFoundException | SQLException ex){
            throw new SQLException("Problem med db: " + ex.getMessage());
        }finally{
            if (cn!=null){
                cn.close();
            }
        }
    }
    
}
