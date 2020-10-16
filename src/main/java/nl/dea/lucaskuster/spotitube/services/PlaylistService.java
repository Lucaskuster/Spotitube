package nl.dea.lucaskuster.spotitube.services;

import nl.dea.lucaskuster.spotitube.dto.PlaylistDTO;
import nl.dea.lucaskuster.spotitube.dto.TrackDTO;

public class PlaylistService {

    public boolean checkOwner(String ingelogdeUser, String eigenaar){
        return ingelogdeUser.equals(eigenaar);
    }

    public int lengthPlaylist(PlaylistDTO playlistDTO){
        var tracks = playlistDTO.getTracks();
        var lenghtPlaylist = 0;
        for (TrackDTO track : tracks.getTracks()) {
            lenghtPlaylist += track.getDuration();
        }
        return lenghtPlaylist;
    }
}
