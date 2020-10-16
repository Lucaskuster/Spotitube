package Spotitube.lucaskuster.datasource;

import Spotitube.lucaskuster.datasource.exceptions.IncorrectLoginException;
import Spotitube.lucaskuster.datasource.exceptions.IncorrectTokenException;
import Spotitube.lucaskuster.dto.LoginRequestDTO;
import Spotitube.lucaskuster.dto.LoginResponseDTO;
import Spotitube.lucaskuster.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection connection;
    private LoginService loginService = new LoginService();

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            if (passwordControl(loginRequestDTO)) {
                var token = loginService.createToken();
                var insert = connection.prepareStatement
                        ("UPDATE LOGIN SET token = ? WHERE username = ?");
                insert.setString(1, token);
                insert.setString(2, loginRequestDTO.getUser());
                insert.execute();

                var loginResponseDTO = new LoginResponseDTO();
                loginResponseDTO.setUser(loginRequestDTO.getUser());
                loginResponseDTO.setToken(token);
                return loginResponseDTO;
            }
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        throw new IncorrectLoginException();
    }

    public boolean passwordControl(LoginRequestDTO loginRequestDTO) {
        try {
            var select = connection.prepareStatement
                    ("SELECT password FROM login WHERE username = ? ");
            select.setString(1, loginRequestDTO.getUser());
            ResultSet resultSet = select.executeQuery();
            resultSet.next();

            return loginRequestDTO.getPassword().equals(resultSet.getString("password"));
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
    }

    public LoginResponseDTO getUserFromToken(String token, Connection connection) {
        var loginResponseDTO = new LoginResponseDTO();
        try {
            var select = connection.prepareStatement
                    ("SELECT username FROM login WHERE token = ? ");
            select.setString(1, token);
            var resultSet = select.executeQuery();

            if (!resultSet.next()) {
                throw new IncorrectTokenException();
            }

            var username = resultSet.getString("username");
            loginResponseDTO.setUser(username);
        } catch (SQLException throwables) {
            throw new ServerErrorException(500);
        }
        return loginResponseDTO;
    }

    @Inject
    public void setSpotitubeDBConnection(SpotitubeDBConnection connection) {
        this.connection = connection.createConnection();
    }
}









