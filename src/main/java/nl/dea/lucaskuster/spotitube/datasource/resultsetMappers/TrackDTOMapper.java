package nl.dea.lucaskuster.spotitube.datasource.resultsetMappers;

import nl.dea.lucaskuster.spotitube.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDTOMapper {

    public TrackDTO maptoTrackDTO(final ResultSet resultSet) throws SQLException {
        var trackDTO = new TrackDTO();

        trackDTO.setId(resultSet.getInt("id"));
        trackDTO.setTitle(resultSet.getString("titel"));
        trackDTO.setPerformer(resultSet.getString("performer"));
        trackDTO.setDuration(resultSet.getInt("duration"));
        trackDTO.setAlbum(resultSet.getString("album"));
        trackDTO.setPlaycount(resultSet.getInt("playcount"));
        trackDTO.setPublicationDate(resultSet.getString("publicationDate"));
        trackDTO.setDescription(resultSet.getString("description"));
        trackDTO.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

        return trackDTO;
    }
}
