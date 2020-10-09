package Spotitube.controllers.controllers;

import Spotitube.controllers.database.PlaylistsDAO;
import Spotitube.controllers.dto.LoginResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistsController {
    private PlaylistsDAO playlistsDAO;
    private LoginResponseDTO loginResponseDTO;

    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlists(@QueryParam("token") String token) {
        //if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.getPlaylistsDTO()).build();
       // }
       // return Response.status(403).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        //if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.delete(id)).build();
//        }
//        return Response.status(403).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token) {
//        if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.add()).build();
//        }
//        return Response.status(403).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
//        if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.edit(id)).build();
//        }
//        return Response.status(403).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
//        if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.tracks(id)).build();
//        }
//        return Response.status(403).build();
    }

    @DELETE
    @Path("/{idP}/tracks/{idT}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackInPlaylist(@QueryParam("token") String token, @QueryParam("idP") int idPlaylist, @QueryParam("idT") int idTrack){
//        if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.deleteTrack(idPlaylist, idTrack)).build();
//        }
//        return Response.status(403).build();
        //Let op!! doordat er maar 1 afspeellijst in staat, staat er bij pop dezelfde lijst. De response "leeg"
        //schrijft dus niet over die afspeellijst heen.
    }

    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token){
//        if (token.equals(loginResponseDTO.getToken())) {
            return Response.ok(playlistsDAO.addTrack()).build();
//        }
//        return Response.status(403).build();
    }

}
