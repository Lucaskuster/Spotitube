package Spotitube.controllers.database;

import Spotitube.controllers.dto.PlaylistDTO;
import Spotitube.controllers.dto.PlaylistsDTO;
import Spotitube.controllers.dto.TrackDTO;
import Spotitube.controllers.dto.TracksDTO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PlaylistsDAO {

    private PlaylistsDTO playlistsDTO;
    private TracksDTO leeg;
    private TracksDTO tracks;
    private TracksDTO heavymetaltracks;

    private  SpotitubeDBConnection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection;
    }

    public PlaylistsDAO() {
        TracksDAO tracks = new TracksDAO();
        this.tracks = tracks.getTracksDTO();
        this.heavymetaltracks = tracks.getHeavyMetalTracksDTO();

        var playlist1 = new PlaylistDTO();
        playlist1.setId(1);
        playlist1.setName("Death metal");
        playlist1.setOwner(true);
        playlist1.setTracks(heavymetaltracks);
        var playlist2 = new PlaylistDTO();
        playlist2.setId(2);
        playlist2.setName("Pop");
        playlist2.setOwner(false);
        playlist2.setTracks(leeg);
        var playlists = new PlaylistsDTO();
        List<PlaylistDTO> playlistArray = List.of(playlist1, playlist2);
        playlists.setPlaylists(playlistArray);
        playlists.setLength(123445);

        this.playlistsDTO = playlists;
    }

    public PlaylistsDTO delete(int id) {
        var playlist1 = new PlaylistDTO();
        playlist1.setId(2);
        playlist1.setName("Pop");
        playlist1.setOwner(false);
        playlist1.setTracks(leeg);

        java.util.List<PlaylistDTO> playlistArray = List.of(playlist1);
        playlistsDTO.setPlaylists(playlistArray);
        playlistsDTO.setLength(6445);

        return playlistsDTO;
    }

    public PlaylistsDTO add() {
        var playlist3 = new PlaylistDTO();
        playlist3.setId(3);
        playlist3.setName("Progressive Rock");
        playlist3.setOwner(false);
        playlist3.setTracks(leeg);

        java.util.List<PlaylistDTO> playlistArray = List.of(playlistsDTO.getPlaylists().get(0),
                playlistsDTO.getPlaylists().get(1), playlist3);
        playlistsDTO.setPlaylists(playlistArray);
        playlistsDTO.setLength(123445);

        return playlistsDTO;
    }


    public PlaylistsDTO edit(int id) {

        var playlist1 = new PlaylistDTO();
        playlist1.setId(1);
        playlist1.setName("Heavy metal");
        playlist1.setOwner(true);
        playlist1.setTracks(heavymetaltracks);
        var playlist2 = new PlaylistDTO();
        playlist2.setId(2);
        playlist2.setName("Pop");
        playlist2.setOwner(false);
        playlist2.setTracks(leeg);
        List<PlaylistDTO> playlistArray = List.of(playlist1, playlist2);
        playlistsDTO.setPlaylists(playlistArray);
        playlistsDTO.setLength(123445);

        return playlistsDTO;
    }

    public TracksDTO tracks(int id) {
        var playlists = playlistsDTO.getPlaylists();
        var playlist = playlists.get(0);
        TracksDTO tracks;
        tracks = playlist.getTracks();
        return tracks;
    }

    public TracksDTO deleteTrack(int idPlaylist, int idTrack) {
        TracksDTO tracks = heavymetaltracks;

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

        List<TrackDTO> tracksArray = List.of(track4);
        tracks.setTracks(tracksArray);

        return tracks;
    }

    public PlaylistsDTO getPlaylistsDTO() {
        return playlistsDTO;
    }

    public TrackDTO addTrack() {
        TracksDTO tracks = heavymetaltracks;

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

        java.util.List<TrackDTO> trackArray = List.of(playlistsDTO.getPlaylists().get(0).getTracks().getTracks().get(0),
                playlistsDTO.getPlaylists().get(0).getTracks().getTracks().get(1), track2);
        tracks.setTracks(trackArray);
        playlistsDTO.getPlaylists().get(0).setTracks(tracks);

        return track2;
    }
}
