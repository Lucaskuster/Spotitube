package Spotitube.controllers.database;

import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class SpotitubeDBConnection {
    SpotitubeDBConnection dbConnection;
    Connection connection;

    private String connectionUrl = "jdbc:sqlserver://localhost;database=Spotitube;integratedSecurity=true;";

    public SpotitubeDBConnection(){
        this.dbConnection = new SpotitubeDBConnection();
        this.connection = dbConnection.createConnection();
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

    public Connection getConnection() {
        return connection;
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

