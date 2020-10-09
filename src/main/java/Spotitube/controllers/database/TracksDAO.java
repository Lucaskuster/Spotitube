package Spotitube.controllers.database;

import Spotitube.controllers.dto.TrackDTO;
import Spotitube.controllers.dto.TracksDTO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.util.List;

@Singleton
public class TracksDAO {

    private TracksDTO tracksDTO;
    private TracksDTO heavyMetalTracksDTO;

    private Connection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    public TracksDAO() {

        var track1 = new TrackDTO();
        track1.setId(3);
        track1.setTitle("Ocean and a rock");
        track1.setPerformer("Lisa Hannigan");
        track1.setDuration(337);
        track1.setAlbum("Sea sew");
        track1.setPlaycount(0);
        track1.setPublicationDate(null);
        track1.setDescription(null);
        track1.setOfflineAvailable(false);

        var track2 = new TrackDTO();
        track2.setId(4);
        track2.setTitle("So Long, Marianne");
        track2.setPerformer("Leonard Cohen");
        track2.setDuration(546);
        track2.setAlbum("Songs of Leonard Cohen");
        track2.setPlaycount(0);
        track2.setPublicationDate(null);
        track2.setDescription(null);
        track2.setOfflineAvailable(false);

        var track3 = new TrackDTO();
        track3.setId(5);
        track3.setTitle("One");
        track3.setPerformer("Metallica");
        track3.setDuration(423);
        track3.setAlbum(null);
        track3.setPlaycount(37);
        track3.setPublicationDate("18-03-2001");
        track3.setDescription("Long version");
        track3.setOfflineAvailable(true);

        TracksDTO tracks = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track1, track2, track3);
        tracks.setTracks(tracksArray);
        this.tracksDTO = tracks;
        this.heavyMetalTracksDTO = tracksHeavyMetal();
    }

    public TracksDTO tracksHeavyMetal() {
        var track4 = new TrackDTO();
        track4.setId(1);
        track4.setTitle("Song for someone");
        track4.setPerformer("The Frames");
        track4.setDuration(350);
        track4.setAlbum("The cost");
        track4.setPlaycount(0);
        track4.setPublicationDate(null);
        track4.setDescription(null);
        track4.setOfflineAvailable(false);

        var track5 = new TrackDTO();
        track5.setId(2);
        track5.setTitle("The cost");
        track5.setPerformer("The Frames");
        track5.setDuration(423);
        track5.setAlbum(null);
        track5.setPlaycount(37);
        track5.setPublicationDate("19-03-2006");
        track5.setDescription("Title song from the Album The Cost");
        track5.setOfflineAvailable(true);

        TracksDTO tracks = new TracksDTO();
        List<TrackDTO> tracksArray = List.of(track4, track5);
        tracks.setTracks(tracksArray);
        return tracks;
    }

    public TracksDTO getTracksDTO() {
        return tracksDTO;
    }

    public TracksDTO getHeavyMetalTracksDTO() {
        return heavyMetalTracksDTO;
    }
}
