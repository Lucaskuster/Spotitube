package Spotitube.lucaskuster.controllers;

import Spotitube.lucaskuster.datasource.TracksDAO;
import Spotitube.lucaskuster.datasource.exceptions.IncorrectTokenException;
import Spotitube.lucaskuster.datasource.exceptions.WrongPlaylistIdException;
import Spotitube.lucaskuster.dto.TrackDTO;
import Spotitube.lucaskuster.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ServerErrorException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TracksControllerTest {
    private TracksController sut;
    private TracksDAO tracksDAO;

    @BeforeEach
    public void setup() {
        this.sut = new TracksController();
        this.tracksDAO = mock(TracksDAO.class);
        this.sut.setTracksDAO(tracksDAO);
    }

    @Test
    void trackTypeTest() {
        //arrange
        var token = "1234-1234-1234";
        var playlistId = 1;

        var track1 = new TrackDTO();
        var track2 = new TrackDTO();

        var tracksDTO = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2);
        tracksDTO.setTracks(tracksArray);

        when(tracksDAO.getTracksDTO(token, playlistId)).thenReturn(tracksDTO);

        //act
        var response = sut.tracks(playlistId, token);
        var entity = response.getEntity();

        //assert
        assertTrue(entity instanceof TracksDTO);
    }

    @Test
    void trackDataTest() {
        //arrange
        var token = "1234-1234-1234";
        var playlistId = 1;

        var track1 = new TrackDTO();
        var track2 = new TrackDTO();

        var tracksDTO = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2);
        tracksDTO.setTracks(tracksArray);

        when(tracksDAO.getTracksDTO(token, playlistId)).thenReturn(tracksDTO);

        //act
        var response = sut.tracks(playlistId, token);
        var tracks = response.getEntity();

        //assert
        assertEquals(tracksDTO, tracks);
    }

    @Test
    void trackIncorrectPlaylistIdTest() {
        //arrange
        var token = "1234-1234-1234";
        var playlistId = 30;

        var track1 = new TrackDTO();
        var track2 = new TrackDTO();

        var tracksDTO = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2);
        tracksDTO.setTracks(tracksArray);

        when(tracksDAO.getTracksDTO(token, playlistId)).thenThrow(new WrongPlaylistIdException());

        //act
        //assert
        assertThrows(WrongPlaylistIdException.class, () -> sut.tracks(playlistId, token));
    }

    @Test
    void trackIncorrectTokenTest() {
        //arrange
        var token = "1111-2222-333";
        var playlistId = 1;

        var track1 = new TrackDTO();
        var track2 = new TrackDTO();

        var tracksDTO = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2);
        tracksDTO.setTracks(tracksArray);

        when(tracksDAO.getTracksDTO(token, playlistId)).thenThrow(new IncorrectTokenException());

        //act
        //assert
        assertThrows(IncorrectTokenException.class, () -> sut.tracks(playlistId, token));
    }

    @Test
    void trackServerError() {
        //arrange
        var token = "1234-1234-1234";
        var playlistId = 1;

        var track1 = new TrackDTO();
        var track2 = new TrackDTO();

        var tracksDTO = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2);
        tracksDTO.setTracks(tracksArray);

        when(tracksDAO.getTracksDTO(token, playlistId)).thenThrow(new ServerErrorException(500));

        //act
        //assert
        assertThrows(ServerErrorException.class, () -> sut.tracks(playlistId, token));
    }
}