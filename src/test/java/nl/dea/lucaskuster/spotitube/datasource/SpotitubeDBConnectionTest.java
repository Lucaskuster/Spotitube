package nl.dea.lucaskuster.spotitube.datasource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class SpotitubeDBConnectionTest {

    private SpotitubeDBConnection sut;

    @BeforeEach
    void setup() {
        this.sut = new SpotitubeDBConnection();
    }

    @Test
    public void createConnectionTypeTest() {
        // Arrange and Act
        var response = sut.createConnection();

        //Assert
        Assertions.assertTrue(response instanceof  Connection);
    }
}
