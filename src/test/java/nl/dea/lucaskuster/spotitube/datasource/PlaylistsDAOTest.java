package nl.dea.lucaskuster.spotitube.datasource;

import nl.dea.lucaskuster.spotitube.dto.LoginResponseDTO;
import nl.dea.lucaskuster.spotitube.dto.PlaylistDTO;
import nl.dea.lucaskuster.spotitube.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistsDAOTest {

    private PlaylistsDAO sut;
    private Connection connection;
    private LoginDAO loginDAO;

    @BeforeEach
    void setup() {
        this.sut = new PlaylistsDAO();

        SpotitubeDBConnection spotitubeConnection = mock(SpotitubeDBConnection.class);
        this.connection = mock(Connection.class);
        when(spotitubeConnection.createConnection()).thenReturn(connection);
        this.sut.setSpotitubeDBConnection(spotitubeConnection);

        this.loginDAO = mock(LoginDAO.class);
        this.sut.setLoginDAO(loginDAO);
    }

    //================================================ getAllPlaylistsDTO ================================================
    //TODO misschien hier meer testen
    @Test
    void getAllPlaylistsThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.getAllPlaylistsDTO(token));
    }

    //================================================ deletePlaylist ================================================
    @Test
    void deletePlaylistsThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var id = 1;

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.deletePlaylist(token, id));
    }

    //================================================ addPlaylist ===============================================
    @Test
    void addPlaylistThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var playlistDTO = new PlaylistDTO();

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.addPlaylist(token, playlistDTO));
    }

    //================================================ editPlaylist ===============================================
    @Test
    void editPlaylistThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var playlistDTO = new PlaylistDTO();
        var id = 1;

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.editPlaylist(token, playlistDTO, id));
    }

    //================================================ tracksInPlaylist ===============================================
    @Test
    void tracksInPlaylistThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var id = 1;

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.tracksInPlaylist(token, id));
    }

    //================================================ deleteTrackInPlaylist ===============================================
    @Test
    void deleteTrackInPlaylistThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var idP = 1;
        var idT = 1;

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.deleteTrackInPlaylist(token, idP, idT));
    }

    //================================================ addTrackToPlaylist ===============================================
    @Test
    void addTrackToPlaylistThrowsExceptionWhenConnectionThrowsException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(any())).thenThrow(new SQLException());
        var token = "1234-1234-1234";
        var trackDTO = new TrackDTO();
        var idT = 1;

        var loginResponseDTO = new LoginResponseDTO();
        when(loginDAO.getUserFromToken(token, connection)).thenReturn(loginResponseDTO);

        // Act and Assert
        assertThrows(ServerErrorException.class, () -> sut.addTrackToPlaylist(token, trackDTO, idT));
    }
}
