package Spotitube.lucaskuster.datasource;

import Spotitube.lucaskuster.datasource.exceptions.WrongPlaylistIdException;
import Spotitube.lucaskuster.datasource.exceptions.WrongTrackIdException;
import Spotitube.lucaskuster.datasource.resultsetMappers.TrackDTOMapper;
import Spotitube.lucaskuster.dto.PlaylistDTO;
import Spotitube.lucaskuster.dto.PlaylistsDTO;
import Spotitube.lucaskuster.dto.TrackDTO;
import Spotitube.lucaskuster.dto.TracksDTO;
import Spotitube.lucaskuster.services.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDAO {

    private Connection connection;
    private TrackDTOMapper trackDTOMapper;
    private LoginDAO loginDAO = new LoginDAO();
    private PlaylistService playlistService = new PlaylistService();

    public PlaylistsDAO() {
    }

    public PlaylistsDTO getAllPlaylistsDTO(String token) {
        var user = loginDAO.getUserFromToken(token, connection);

        var playlistsDTO = new PlaylistsDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT id, name, owner, length FROM playlist");

            var resultSet = select.executeQuery();

            var lengthBereken = 0;

            var i = 0;
            List<PlaylistDTO> playlistArray = new ArrayList<>();
            while (resultSet.next()) {
                var playlistDTO = new PlaylistDTO();
                var tracksDTO = tracksInPlaylist(token, resultSet.getInt("id"));
                playlistDTO.setId(resultSet.getInt("id"));
                playlistDTO.setName(resultSet.getString("name"));
                playlistDTO.setOwner(playlistService.checkOwner(user.getUser(), resultSet.getString("owner")));
                playlistDTO.setTracks(tracksDTO);

                playlistArray.add(i, playlistDTO);

                var tracks = tracksDTO.getTracks();
                for (TrackDTO track : tracks) {
                    lengthBereken += track.getDuration();
                }
                i++;
            }
            playlistsDTO.setPlaylists(playlistArray);
            playlistsDTO.setLength(lengthBereken);

        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return playlistsDTO;
    }

    public PlaylistsDTO deletePlaylist(String token, int id) {
        loginDAO.getUserFromToken(token, connection);
        try {
            var checkPlaylist = connection.prepareStatement
                    ("SELECT id FROM playlist WHERE id = ?");
            checkPlaylist.setInt(1, id);
            ResultSet resultSet = checkPlaylist.executeQuery();

            if(!resultSet.next()){
                throw new WrongPlaylistIdException();
            }

            var delete = connection.prepareStatement
                    ("DELETE FROM playlist WHERE id = ?");
            delete.setInt(1, id);
            delete.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllPlaylistsDTO(token);
    }

    public PlaylistsDTO add(String token, PlaylistDTO playlistDTO) {
        var user = loginDAO.getUserFromToken(token, connection);
        try {
            var addStatement = connection.prepareStatement
                    ("INSERT INTO playlist (name, owner) VALUES (?, ?)");
            addStatement.setString(1, playlistDTO.getName());
            addStatement.setString(2, user.getUser());
            addStatement.execute();
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return getAllPlaylistsDTO(token);
    }

    public PlaylistsDTO edit(String token, PlaylistDTO playlistDTO, int id) {
        loginDAO.getUserFromToken(token, connection);
        try {
            var checkPlaylist = connection.prepareStatement
                    ("SELECT id FROM playlist WHERE id = ?");
            checkPlaylist.setInt(1, id);
            ResultSet resultSet = checkPlaylist.executeQuery();

            if(!resultSet.next()){
                throw new WrongPlaylistIdException();
            }

            var editStatement = connection.prepareStatement
                    ("UPDATE playlist SET name = ? WHERE id = ?");
            editStatement.setString(1, playlistDTO.getName());
            editStatement.setInt(2, id);

            editStatement.execute();
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return getAllPlaylistsDTO(token);
    }

    public TracksDTO tracksInPlaylist(String token, int idPlaylist) {
        loginDAO.getUserFromToken(token, connection);
        var tracksDTO = new TracksDTO();
        try {

            var select = connection.prepareStatement
                    ("SELECT T.id, T.titel, T.performer, T.duration, T.album, T.playcount, T.publicationDate, T.description, " +
                            " P.offlineAvailable FROM track T INNER JOIN playlistTracks P ON T.id = P.idTrack WHERE P.idPlaylist = ?");
            select.setInt(1, idPlaylist);
            var resultSet = select.executeQuery();

            var i = 0;
            List<TrackDTO> trackArray = new ArrayList<>();

            while (resultSet.next()) {
                var trackDTO = trackDTOMapper.maptoTrackDTO(resultSet);

                trackArray.add(i, trackDTO);
                i++;
            }
            tracksDTO.setTracks(trackArray);

        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return tracksDTO;
    }


    public TracksDTO deleteTrack(String token, int idPlaylist, int idTrack) {
        loginDAO.getUserFromToken(token, connection);
        try {
            var checkPlaylist = connection.prepareStatement
                    ("SELECT id FROM playlist WHERE id = ?");
            checkPlaylist.setInt(1, idPlaylist);
            ResultSet resultSetPlaylist = checkPlaylist.executeQuery();

            if(!resultSetPlaylist.next()){
                throw new WrongPlaylistIdException();
            }

            var checkTrack = connection.prepareStatement
                    ("SELECT id FROM playlist WHERE id = ?");
            checkTrack.setInt(1, idPlaylist);
            ResultSet resultSetTrack = checkTrack.executeQuery();

            if(!resultSetTrack.next()){
                throw new WrongTrackIdException();
            }

            var delete = connection.prepareStatement
                    ("DELETE FROM playlistTracks WHERE idTrack = ?");
            delete.setInt(1, idTrack);
            delete.execute();

        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return tracksInPlaylist(token, idPlaylist);
    }

    public TracksDTO addTrack(String token, TrackDTO trackDTO, int idPlaylist) {
        loginDAO.getUserFromToken(token, connection);
        try {
            var addStatement = connection.prepareStatement
                    ("INSERT INTO playlistTracks (idPlaylist, idTrack, offlineAvailable) VALUES (?, ?, ?)");
            addStatement.setInt(1, idPlaylist);
            addStatement.setInt(2, trackDTO.getId());
            addStatement.setBoolean(3, trackDTO.isOfflineAvailable());
            addStatement.execute();

        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return tracksInPlaylist(token, idPlaylist);
    }

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    @Inject
    public void setTrackDTOMapper(TrackDTOMapper trackDTOMapper) {
        this.trackDTOMapper = trackDTOMapper;
    }
}
