package msgpkg;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.CachedRowSet;


/**
 *  messageBean
 * @author Filip
 */
@Named(value = "messageBean")
@RequestScoped
public class MessageBean {    
    private int id, status, employeeID, timeSpentHours, timeSpentMinutes, selectedID;
    private String date, content, comment;
    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTimeSpentHours() {
        return timeSpentHours;
    }

    public void setTimeSpentHours(int timeSpentHours) {
        this.timeSpentHours = timeSpentHours;
    }

    public int getTimeSpentMinutes() {
        return timeSpentMinutes;
    }

    public void setTimeSpentMinutes(int timeSpentMinutes) {
        this.timeSpentMinutes = timeSpentMinutes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String updateMatter() throws SQLException{        
        Connection cn = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("UPDATE Matter SET STATUS = ?, EMPLOYEEID = ?, DATE = ?, TIMESPENTHOURS = ?,"
                    + " TIMESPENTMINUTES = ?, COMMENT = ? WHERE ID = ?");
            stmt.setInt(1, status);
            stmt.setInt(2, employeeID);
            stmt.setString(3, date);
            stmt.setInt(4, timeSpentHours);
            stmt.setInt(5, timeSpentMinutes);
            stmt.setString(6, comment);
            stmt.setInt(7, selectedID);
            
            stmt.executeUpdate();            
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Källa: " + MessageBean.class.getName() + " Error: " + ex.getMessage());
        }
        finally{
            try{
                if (cn!=null){
                cn.close();
                }
            }catch(SQLException ex){
                System.out.println("Källa: " + MessageBean.class.getName() + " SQL'Error: " + ex.getMessage());
            }
        }
        return "index";
    }
    
    public ResultSet getMatter() throws SQLException{
        Connection cn = null;
        CachedRowSet result = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/MatterDB;create=true");
            PreparedStatement stmt = cn.prepareStatement("SELECT * FROM Matter");
            result = new CachedRowSetImpl();
            result.populate(stmt.executeQuery());
            
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Källa: " + MessageBean.class.getName() + " Error: " + ex.getMessage());
        }
        finally{
            try{
                if (cn!=null){
                cn.close();
                }
            }catch(SQLException ex){
                System.out.println("Källa: " + MessageBean.class.getName() + " SQL'Error: " + ex.getMessage());
            }
        }
        return result;  
    }
    
    
    
}
