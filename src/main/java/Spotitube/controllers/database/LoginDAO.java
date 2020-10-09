package Spotitube.controllers.database;

import javax.inject.Inject;

public class LoginDAO {
    private  SpotitubeDBConnection connection;

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection;
    }

    public LoginDAO() {
        
    }
}
