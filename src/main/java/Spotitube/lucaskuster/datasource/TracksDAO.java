package Spotitube.lucaskuster.datasource;

import Spotitube.lucaskuster.datasource.resultsetMappers.TrackDTOMapper;
import Spotitube.lucaskuster.dto.TrackDTO;
import Spotitube.lucaskuster.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TracksDAO {

    private Connection connection;
    private TrackDTOMapper trackDTOMapper;
    private final LoginDAO loginDAO = new LoginDAO();

    public TracksDAO() {
    }

    public TracksDTO getTracksDTO(String token, int playlistId) {
        loginDAO.getUserFromToken(token, connection);
        TracksDTO tracksDTO = new TracksDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT T.* FROM track T where id not in (select P.idTrack FROM playlistTracks P WHERE P.idPlaylist = ?)");
            select.setInt(1, playlistId);
            var resultSet = select.executeQuery();

            int i = 0;
            List<TrackDTO> trackArray = new ArrayList<>();
            var trackDTO = new TrackDTO();

            while (resultSet.next()) {
                trackDTO = trackDTOMapper.maptoTrackDTO(resultSet);
                trackArray.add(i, trackDTO);
                i++;
            }
            tracksDTO.setTracks(trackArray);
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return tracksDTO;
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
