package Spotitube.controllers.database;

import Spotitube.controllers.dto.LoginRequestDTO;
import Spotitube.controllers.dto.LoginResponseDTO;
import Spotitube.controllers.services.LoginService;

import javax.inject.Inject;
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

    public LoginResponseDTO loginDAO(LoginRequestDTO loginRequestDTO) {
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
                return loginResponseDTO;
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
        //welke status anders?
    }
    
//    public String getLoginToken(){
//        return logintoken;
//////        String token = null;
//////        try {
//////            var select = connection.prepareStatement
//////                    ("SELECT token FROM login WHERE username = ? ");
//////            select.setString(1, username);
//////            ResultSet resultSet = select.executeQuery();
//////
//////            token = resultSet.getString("token");
//////        } catch (SQLException throwables) {
//////            throwables.printStackTrace();
//////        }
//////        return token;
//    }
}
