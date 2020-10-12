package Spotitube.controllers.datasource;

import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class SpotitubeDBConnection {

    public SpotitubeDBConnection() {
    }

    public Connection createConnection() {
        Connection cnEmps = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;database=Spotitube;integratedSecurity=true;";
            cnEmps = DriverManager.getConnection(connectionUrl);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cnEmps;
    }
}
