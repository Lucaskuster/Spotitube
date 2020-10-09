package Spotitube.controllers.controllers;

import Spotitube.controllers.database.LoginDAO;
import Spotitube.controllers.dto.LoginRequestDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    private LoginDAO loginDAO;

    @Inject
    public void setPlaylistsDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO){
        var result = loginDAO.loginDAO(loginRequestDTO);
        return Response.ok(result.getEntity()).build();
    }
}
