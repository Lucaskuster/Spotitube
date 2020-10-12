package Spotitube.controllers.controllers;

import Spotitube.controllers.datasource.PlaylistsDAO;
import Spotitube.controllers.dto.PlaylistDTO;
import Spotitube.controllers.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistsController {
    private PlaylistsDAO playlistsDAO;

    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlists(@QueryParam("token") String token) {
        return Response.ok(playlistsDAO.getPlaylistsDTO(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(playlistsDAO.delete(token, id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
        return Response.ok(playlistsDAO.add(token, playlistDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO, @PathParam("id") int id) {
        return Response.ok(playlistsDAO.edit(token, playlistDTO, id)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response.ok(playlistsDAO.tracks(token, id)).build();
    }

    @DELETE
    @Path("/{idP}/tracks/{idT}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackInPlaylist(@QueryParam("token") String token, @PathParam("idP") int idPlaylist, @PathParam("idT") int idTrack) {
        return Response.ok(playlistsDAO.deleteTrack(token, idPlaylist, idTrack)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, TrackDTO trackDTO, @PathParam("id") int id) {
        return Response.ok(playlistsDAO.addTrack(token, trackDTO, id)).build();
    }
}
