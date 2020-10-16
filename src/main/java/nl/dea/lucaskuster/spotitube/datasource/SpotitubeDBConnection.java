package nl.dea.lucaskuster.spotitube.datasource;

import javax.inject.Singleton;
import javax.ws.rs.ServerErrorException;
import java.sql.*;
import java.util.Properties;

@Singleton
public class SpotitubeDBConnection {
    private Properties properties;

    public SpotitubeDBConnection() {
    }

    public Connection createConnection() {
        Connection cnEmps = null;

        try {
            properties = new ReadpropertiesFile().getProperties();
            String connectionUrl = properties.getProperty("URL");
            cnEmps = DriverManager.getConnection(connectionUrl);
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return cnEmps;
    }
}
