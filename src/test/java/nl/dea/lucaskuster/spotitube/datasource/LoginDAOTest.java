package nl.dea.lucaskuster.spotitube.datasource;

import nl.dea.lucaskuster.spotitube.dto.LoginRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginDAOTest {
    private LoginDAO sut;
    private Connection connection;

    @BeforeEach
    void setup() {
        this.sut = new LoginDAO();
        SpotitubeDBConnection spotitubeConnection = mock(SpotitubeDBConnection.class);
        this.connection = mock(Connection.class);

        when(spotitubeConnection.createConnection()).thenReturn(connection);

        this.sut.setSpotitubeDBConnection(spotitubeConnection);
    }

    //================================================ login ================================================
    @Test
    void loginThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var loginRequestDTO = new LoginRequestDTO();

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.login(loginRequestDTO));
    }

    //================================================ getUserFromToken ================================================
    @Test
    void userFromTokenThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1243-1234";

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.getUserFromToken(token, connection));
    }
}