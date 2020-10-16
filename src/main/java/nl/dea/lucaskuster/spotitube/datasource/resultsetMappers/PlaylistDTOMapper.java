package nl.dea.lucaskuster.spotitube.datasource.resultsetMappers;

import nl.dea.lucaskuster.spotitube.dto.LoginResponseDTO;
import nl.dea.lucaskuster.spotitube.dto.PlaylistDTO;
import nl.dea.lucaskuster.spotitube.dto.TracksDTO;
import nl.dea.lucaskuster.spotitube.services.PlaylistService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDTOMapper {

    public PlaylistDTO maptoPlaylistDTO(final ResultSet resultSet, TracksDTO tracksDTO, PlaylistService playlistService, LoginResponseDTO user) throws SQLException {
        var playlistDTO = new PlaylistDTO();

        playlistDTO.setId(resultSet.getInt("id"));
        playlistDTO.setName(resultSet.getString("name"));
        playlistDTO.setOwner(playlistService.checkOwner(user.getUser(), resultSet.getString("owner")));
        playlistDTO.setTracks(tracksDTO);

        return playlistDTO;
    }
}
