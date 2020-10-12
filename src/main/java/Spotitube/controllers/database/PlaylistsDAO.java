package Spotitube.controllers.database;

import Spotitube.controllers.dto.PlaylistDTO;
import Spotitube.controllers.dto.PlaylistsDTO;
import Spotitube.controllers.dto.TrackDTO;
import Spotitube.controllers.dto.TracksDTO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PlaylistsDAO {

    private PlaylistsDTO playlistsDTO;
    private Connection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    public PlaylistsDAO() {
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
            var idStatement = connection.prepareStatement
                    ("SELECT MAX(id) id FROM playlist");
            ResultSet resultSet = idStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");

            var addStatement = connection.prepareStatement
                    ("INSERT INTO playlist (id, name, owner) VALUES (?, ?, ?)");
            addStatement.setInt(1, id + 1);
            addStatement.setString(2, playlistDTO.getName());
            addStatement.setBoolean(3, true);
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

    public TracksDTO tracks(String token, int idPlaylist) {
        TracksDTO tracksDTO = new TracksDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT T.* FROM track T INNER JOIN playlistTracks P ON T.id = P.idTrack WHERE P.idPlaylist = ?");
            select.setInt(1, idPlaylist);

            ResultSet resultSet = select.executeQuery();

            int id;
            String titel = "";
            String performer = "";
            int duration;
            String album = "";
            int playcount;
            String publicationDate = "";
            String description = "";
            boolean offlineAvailable;

            int i = 0;
            List<TrackDTO> trackArray = new ArrayList<TrackDTO>();

            while (resultSet.next()) {
                TrackDTO trackDTO = new TrackDTO();
                trackDTO.setId(resultSet.getInt("id"));
                trackDTO.setTitle(resultSet.getString("titel"));
                trackDTO.setPerformer(resultSet.getString("performer"));
                trackDTO.setDuration(resultSet.getInt("duration"));
                trackDTO.setAlbum(resultSet.getString("album"));
                trackDTO.setPlaycount(resultSet.getInt("playcount"));
                trackDTO.setPublicationDate(resultSet.getString("publicationDate"));
                trackDTO.setDescription(resultSet.getString("description"));
                trackDTO.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

                trackArray.add(i, trackDTO);
                i++;
            }
            tracksDTO.setTracks(trackArray);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tracksDTO;
    }


    public TracksDTO deleteTrack(String token, int idPlaylist, int idTrack) {
        try {
            var delete = connection.prepareStatement
                    ("DELETE FROM playlistTracks WHERE idTrack = ?");
            delete.setInt(1, idTrack);
            delete.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tracks(token, idPlaylist);
    }

    public TracksDTO addTrack(String token, TrackDTO trackDTO, int idPlaylist) {
        try {
            var addStatement = connection.prepareStatement
                    ("INSERT INTO playlistTracks (idPlaylist, idTrack) VALUES (?, ?)");
            addStatement.setInt(1, idPlaylist);
            addStatement.setInt(2, trackDTO.getId());
            addStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tracks(token, idPlaylist);
    }
}
