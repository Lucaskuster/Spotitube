package Spotitube.lucaskuster.datasource.resultsetMappers;

import Spotitube.lucaskuster.dto.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDTOMapperTest {
    private TrackDTOMapper sut;

    @BeforeEach
    void setup(){
        this.sut = new TrackDTOMapper();
    }
    @Mock
    private ResultSet resultSet;

    @Test
    void maptoTrackDTOTypeTest() throws SQLException {
        // Arrange
//        TrackDTO trackDTO = new TrackDTO();
//        trackDTO.setId(1);
//        trackDTO.setTitle("test");
//        trackDTO.setPerformer("test");
//        trackDTO.setDuration(1);
//        trackDTO.setAlbum("test");
//        trackDTO.setPlaycount(1);
//        trackDTO.setPublicationDate("testDate");
//        trackDTO.setDescription("test");
//        trackDTO.setOfflineAvailable(true);

        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("titel")).thenReturn("test");
        Mockito.when(resultSet.getString("performer")).thenReturn("test");
        Mockito.when(resultSet.getInt("duration")).thenReturn(1);
        Mockito.when(resultSet.getString("album")).thenReturn("test");
        Mockito.when(resultSet.getInt("playcount")).thenReturn(1);
        Mockito.when(resultSet.getString("publicationDate")).thenReturn("testDate");
        Mockito.when(resultSet.getString("description")).thenReturn("test");
        Mockito.when(resultSet.getBoolean("offlineAvailable")).thenReturn(true);

        // Act
        var response = sut.maptoTrackDTO(resultSet);

        // Assert
        Assertions.assertTrue(response instanceof TrackDTO);
    }
}
