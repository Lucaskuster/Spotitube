package nl.dea.lucaskuster.spotitube.services;

public class PlaylistService {

    public boolean checkOwner(String ingelogdeUser, String eigenaar){
        return ingelogdeUser.equals(eigenaar);
    }
}
