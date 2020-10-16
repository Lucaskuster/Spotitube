package nl.dea.lucaskuster.spotitube.controllers;

import nl.dea.lucaskuster.spotitube.datasource.LoginDAO;
import nl.dea.lucaskuster.spotitube.dto.LoginRequestDTO;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO){
        return Response.ok(loginDAO.login(loginRequestDTO)).build();
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}
