package Spotitube.controllers.controllers;

import Spotitube.controllers.database.TracksDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {
    private TracksDAO tracksDAO;

    @Inject
    public void setPlaylistsDAO(TracksDAO tracksDAO) {
        this.tracksDAO = tracksDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token){
        if (token.equals("1234-1234-1234")){
            return Response.ok(tracksDAO.getTracksDTO()).build();
        }
        return Response.status(403).build();
    }
}