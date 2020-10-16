package nl.dea.lucaskuster.spotitube.datasource;

import javax.ws.rs.ServerErrorException;
import java.io.IOException;
import java.util.Properties;

public class ReadpropertiesFile {
    private Properties properties;

    public ReadpropertiesFile() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            throw new ServerErrorException(500);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
