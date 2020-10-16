package nl.dea.lucaskuster.spotitube.datasource.resultsetMappers;

import nl.dea.lucaskuster.spotitube.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackDTOMapperTest {
    public static final String TITLE = "testTitle";
    private TrackDTOMapper sut;

    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        this.sut = new TrackDTOMapper();
        resultSet = mock(ResultSet.class);
    }


    @Test
    void maptoTrackDTOTypeTest() throws SQLException {
        // Arrange
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("titel")).thenReturn("test");
        when(resultSet.getString("performer")).thenReturn("test");
        when(resultSet.getInt("duration")).thenReturn(1);
        when(resultSet.getString("album")).thenReturn("test");
        when(resultSet.getInt("playcount")).thenReturn(1);
        when(resultSet.getString("publicationDate")).thenReturn("testDate");
        when(resultSet.getString("description")).thenReturn("test");
        when(resultSet.getBoolean("offlineAvailable")).thenReturn(true);

        // Act
        var response = sut.maptoTrackDTO(resultSet);

        // Assert
        assertTrue(response instanceof TrackDTO);
    }

    @Test
    void maptoTrackDTOContentTest() throws SQLException {
        // Arrange
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("titel")).thenReturn(TITLE);
        when(resultSet.getString("performer")).thenReturn("test");
        when(resultSet.getInt("duration")).thenReturn(1);
        when(resultSet.getString("album")).thenReturn("test");
        when(resultSet.getInt("playcount")).thenReturn(1);
        when(resultSet.getString("publicationDate")).thenReturn("testDate");
        when(resultSet.getString("description")).thenReturn("test");
        when(resultSet.getBoolean("offlineAvailable")).thenReturn(true);

        // Act
        var response = sut.maptoTrackDTO(resultSet);

        // Assert
        assertEquals(TITLE, response.getTitle());
    }
}
