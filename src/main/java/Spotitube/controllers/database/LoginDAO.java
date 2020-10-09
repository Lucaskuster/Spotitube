package Spotitube.controllers.database;

import Spotitube.controllers.dto.LoginRequestDTO;
import Spotitube.controllers.dto.LoginResponseDTO;
import Spotitube.controllers.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection connection;
    private LoginService loginService = new LoginService();

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }

    public Response loginDAO(LoginRequestDTO loginRequestDTO) {
        try {
            var select = connection.prepareStatement
                    ("SELECT username, password FROM login WHERE username = ? ");
            select.setString(1, loginRequestDTO.getUser());

            ResultSet resultSet = select.executeQuery();
            String username = "";
            String password = "";

            while (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }

            var loginCorrect = loginRequestDTO.getPassword().equals(password);

            if (loginCorrect) {
                var token = loginService.createToken();
                var insert = connection.prepareStatement
                        ("UPDATE LOGIN SET token = ? WHERE username = ?");

                insert.setString(1, token);
                insert.setString(2, username);
                insert.execute();

                var loginResponseDTO = new LoginResponseDTO();
                loginResponseDTO.setUser(username);
                loginResponseDTO.setToken(token);
                return Response.ok(loginResponseDTO).build();
            }
            return Response.status(401).build();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Response.status(400).build();
        //welke status anders?
    }
}
