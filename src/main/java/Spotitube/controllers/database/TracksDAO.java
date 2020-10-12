package Spotitube.controllers.database;

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
public class TracksDAO {

    private Connection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    public TracksDAO() {
    }

    public TracksDTO getTracksDTO(String token) {
        TracksDTO tracksDTO = new TracksDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT T.* FROM track T LEFT JOIN playlistTracks P ON P.idTrack = T.id WHERE P.idTrack IS NULL");

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
}
