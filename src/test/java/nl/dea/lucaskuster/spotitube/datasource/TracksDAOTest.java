package nl.dea.lucaskuster.spotitube.datasource;

import nl.dea.lucaskuster.spotitube.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TracksDAOTest {

    private TracksDAO sut;
    private Connection connection;
    private LoginDAO loginDAO;

    @BeforeEach
    void setup() {
        this.sut = new TracksDAO();

        SpotitubeDBConnection spotitubeConnection = mock(SpotitubeDBConnection.class);
        this.connection = mock(Connection.class);
        when(spotitubeConnection.createConnection()).thenReturn(connection);
        this.sut.setSpotitubeDBConnection(spotitubeConnection);

        this.loginDAO = mock(LoginDAO.class);
        this.sut.setLoginDAO(loginDAO);
    }

    //================================================ getAllTracks ================================================
    @Test
    void getAllTracksThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var idP = 1;
        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.getAllTracksDTO(token, idP));
    }
}
