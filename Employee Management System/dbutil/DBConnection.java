package empmgmt.dbutil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;/**
 *
 * @author HP
 */
public class DBConnection {
    public static Connection conn;
    static{
        try{
            conn = DriverManager.getConnection("jbdc:oracle:thin:@//localhost:1521/xe", "myproject","mystudents");
            JOptionPane.showMessageDialog(null,"Connection opened");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Cannot open the Connection");
            ex.printStackTrace();
            //System.exit(0);
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
    public static void closeConnection(){
        try{
            conn.close();
            }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Cannot Close the Connection");
            ex.printStackTrace();
        }
    }
   
}

