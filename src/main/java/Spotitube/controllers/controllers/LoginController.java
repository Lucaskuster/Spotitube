package Spotitube.controllers.controllers;

import Spotitube.controllers.dto.LoginRequestDTO;
import Spotitube.controllers.dto.LoginResponseDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response login(LoginRequestDTO loginRequestDTO){

        if(loginRequestDTO.getUser().equals("Lucas") && loginRequestDTO.getPassword().equals("Lucas")){
            var loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken("1234-1234-1234");
            loginResponseDTO.setUser("Lucas Kuster");
            return Response.ok(loginResponseDTO).build();
        }
        return Response.status(401).build();
    }
}
