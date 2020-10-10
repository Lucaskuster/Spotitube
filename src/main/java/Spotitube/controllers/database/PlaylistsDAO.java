package Spotitube.controllers.database;

import Spotitube.controllers.dto.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PlaylistsDAO {

    private TracksDTO leeg;
    private TracksDTO tracks;
    private TracksDTO heavymetaltracks;
    private PlaylistsDTO playlistsDTO;
    private Connection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    public PlaylistsDAO() {
        TracksDAO tracks = new TracksDAO();
        this.tracks = tracks.getTracksDTO();
        this.heavymetaltracks = tracks.getHeavyMetalTracksDTO();
    }

    public PlaylistsDTO getPlaylistsDTO(String token) {
//        if(token.equals(login.getLoginToken())) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT id, name, owner, length FROM playlist");

            ResultSet resultSet = select.executeQuery();
            int id;
            String name = "";
            Boolean owner;
            int length = 0;

            int i = 0;
            List<PlaylistDTO> playlistArray = new ArrayList<PlaylistDTO>();
            while (resultSet.next()) {
                PlaylistDTO playlistDTO = new PlaylistDTO();
                playlistDTO.setId(resultSet.getInt("id"));
                playlistDTO.setName(resultSet.getString("name"));
                playlistDTO.setOwner(resultSet.getBoolean("owner"));
                playlistDTO.setTracks(null);

                playlistArray.add(i, playlistDTO);
                length += resultSet.getInt("length");
                i++;
            }
            playlistsDTO.setPlaylists(playlistArray);
            playlistsDTO.setLength(length);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return playlistsDTO;

        //return null;//er moet een 401 komen
        // Wat dan terug geven als er geen response kan?
    }


    public PlaylistsDTO delete(String token, int id) {
        try {
            var delete = connection.prepareStatement
                    ("DELETE FROM playlist WHERE id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getPlaylistsDTO(token);
    }

    public PlaylistsDTO add(String token, PlaylistDTO playlistDTO) {
        try {
            var addStatement = connection.prepareStatement
                    ("INSERT INTO playlist (id, name, owner) VALUES (?, ?, ?)");
            addStatement.setInt(1, playlistDTO.getId());
            addStatement.setString(2, playlistDTO.getName());
            addStatement.setBoolean(3, playlistDTO.isOwner());
            addStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getPlaylistsDTO(token);
    }

    public PlaylistsDTO edit(String token, PlaylistDTO playlistDTO, int id) {
        try {
            var editStatement = connection.prepareStatement
                    ("UPDATE playlist SET name = ? WHERE id = ?");
            editStatement.setString(1, playlistDTO.getName());
            editStatement.setInt(2, id);

            editStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getPlaylistsDTO(token);
    }

    public TracksDTO tracks(int id) {
        return heavymetaltracks;
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
