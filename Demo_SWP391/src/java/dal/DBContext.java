package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KEISHA
 */
public abstract class DBContext<T> {

    protected Connection connect;

    public DBContext() {
        try {
<<<<<<< HEAD:Demo_SWP391/src/java/dal/DBContext.java
            String username = "thangle";
            String password = "1407";
=======
            String username = "son";
<<<<<<< HEAD:Demo_SWP391/src/java/dal/DBContext.java
            String password = "123456";
>>>>>>> main:SWP391_Group6/src/java/dal/DBContext.java
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=demo;encrypt=true;trustservercertificate=true;";
=======
            String password = "Son@123@";
            String url = "jdbc:sqlserver://103.252.92.181\\SQLEXPRESS:1433;databaseName=swp-son;encrypt=true;trustservercertificate=true;";
>>>>>>> origin/sonddt:SWP391_Group6/src/java/dal/DBContext.java
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

}
