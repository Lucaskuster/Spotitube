package Spotitube.controllers.controllers;

import Spotitube.controllers.datasource.TracksDAO;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token){
            return Response.ok(tracksDAO.getTracksDTO(token, playlistId)).build();
    }

    @Inject
    public void setPlaylistsDAO(TracksDAO tracksDAO) {
        this.tracksDAO = tracksDAO;
    }
}
