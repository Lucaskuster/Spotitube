package nl.dea.lucaskuster.spotitube.datasource.resultsetMappers;

import nl.dea.lucaskuster.spotitube.dto.LoginResponseDTO;
import nl.dea.lucaskuster.spotitube.dto.PlaylistDTO;
import nl.dea.lucaskuster.spotitube.dto.TracksDTO;
import nl.dea.lucaskuster.spotitube.services.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistDTOMapperTest {
    public static final String USER = "user";
    private PlaylistDTOMapper sut;

    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        this.sut = new PlaylistDTOMapper();
        resultSet = mock(ResultSet.class);
    }


    @Test
    void mapToPlaylistDTOTypeTest() throws SQLException {
        // Arrange
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("test");
        when(resultSet.getString("owner")).thenReturn(USER);
        var tracksDTO = new TracksDTO();
        var playlistService = new PlaylistService();
        var loginResponse = new LoginResponseDTO();
        var user = loginResponse;
        user.setUser(USER);


        // Act
        var response = sut.maptoPlaylistDTO(resultSet, tracksDTO, playlistService, user);

        // Assert
        assertTrue(response instanceof PlaylistDTO);
    }

    @Test
    void mapToPlaylistDTODataTest() throws SQLException {
        // Arrange
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("test");
        when(resultSet.getString("owner")).thenReturn(USER);
        var tracksDTO = new TracksDTO();
        var playlistService = new PlaylistService();
        var loginResponse = new LoginResponseDTO();
        var user = loginResponse;
        user.setUser(USER);


        // Act
        var response = sut.maptoPlaylistDTO(resultSet, tracksDTO, playlistService, user);

        // Assert
        assertEquals("test", response.getName());
    }
}
