package Spotitube.controllers.services;

public class PlaylistService {

    public boolean checkOwner(String ingelogdeUser, String eigenaar){
        return ingelogdeUser.equals(eigenaar);
    }
}
