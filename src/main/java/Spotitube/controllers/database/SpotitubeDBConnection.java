package Spotitube.controllers.database;

import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class SpotitubeDBConnection {

    private String connectionUrl = "jdbc:sqlserver://localhost;database=Spotitube;integratedSecurity=true;";

    public SpotitubeDBConnection(){
    }

    public Connection createConnection() {
        Connection cnEmps = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            cnEmps = DriverManager.getConnection(connectionUrl);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cnEmps;
    }

//    public static void main(String[] args) {
//
//
//
//        try {
//            var select = connection.prepareStatement("select * from login");
//            ResultSet resultSet = select.executeQuery();
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("username"));
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
}

