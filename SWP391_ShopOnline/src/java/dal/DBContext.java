package dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBContext<T> {

    protected Connection connect;

    public DBContext() {
        try {
            String username = "thangle";
            String password = "1407";
            String url = "jdbc:sqlserver://localhost\\LDT-147:1433;databaseName=SWP391_Fall24;encrypt=true;trustServerCertificate=true; ";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
