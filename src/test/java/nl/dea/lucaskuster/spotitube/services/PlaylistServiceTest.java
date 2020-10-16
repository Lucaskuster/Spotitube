package nl.dea.lucaskuster.spotitube.services;

import nl.dea.lucaskuster.spotitube.dto.PlaylistDTO;
import nl.dea.lucaskuster.spotitube.dto.TrackDTO;
import nl.dea.lucaskuster.spotitube.dto.TracksDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Track;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlaylistServiceTest {

    private PlaylistService sut;

    @BeforeEach
    public void setup() {
        this.sut = new PlaylistService();
    }

    @Test
    public void checkOwnerDataCorrectTest() {
        // Arrange
        var ingelogdeUser = "Lucas";
        var eigenaar = "Lucas";

        // Act
        var owner = sut.checkOwner(ingelogdeUser, eigenaar);

        // Assert
        assertTrue(owner);
    }

    @Test
    public void checkOwnerDataIncorrectTest() {
        // Arrange
        var ingelogdeUser = "Lucas";
        var eigenaar = "NietLucas";

        // Act
        var owner = sut.checkOwner(ingelogdeUser, eigenaar);

        // Assert
        assertFalse(owner);
    }

    @Test
    public void lenghtPlaylistDataTest(){
        // Arrange
        var length = 10;
        var playlistDTO = new PlaylistDTO();
        var tracksDTO = new TracksDTO();
        var trackDTO = new TrackDTO();

        trackDTO.setDuration(length);
        ArrayList<TrackDTO> trackArray = new ArrayList<>();
        trackArray.add(trackDTO);
        tracksDTO.setTracks(trackArray);
        playlistDTO.setTracks(tracksDTO);

        // Act
        var response = sut.lengthPlaylist(playlistDTO);

        // Assert
        assertEquals(length, response);
    }
}