package nl.dea.lucaskuster.spotitube.controllers;

import nl.dea.lucaskuster.spotitube.datasource.TracksDAO;

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
            return Response.ok(tracksDAO.getAllTracksDTO(token, playlistId)).build();
    }

    @Inject
    public void setTracksDAO(TracksDAO tracksDAO) {
        this.tracksDAO = tracksDAO;
    }
}
