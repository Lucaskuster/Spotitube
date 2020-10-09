package Spotitube.controllers;

import Spotitube.controllers.controllers.LoginController;
import Spotitube.controllers.dto.LoginRequestDTO;
import Spotitube.controllers.dto.LoginResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    @Test
    void loginTypeTest() {
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");
        //act
        Response response = LoginController.login(loginRequestDTO);
        Object entity = response.getEntity();

        //assert
        Assertions.assertTrue(entity instanceof LoginResponseDTO);
    }

    @Test
    void loginDataTest(){
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        //act
        Response response = LoginController.login(loginRequestDTO);
        LoginResponseDTO loginResponse = (LoginResponseDTO) response.getEntity();

        //assert
        Assertions.assertEquals("Lucas Kuster", loginResponse.getUser());
    }

    @Test
    void loginWrongDataTest(){
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Test");
        loginRequestDTO.setPassword("Test");

        //act
        Response response = LoginController.login(loginRequestDTO);

        //assert
        assertEquals(response.getStatus(), 401);
    }

    @Test
    void loginCorrectTokenTest(){
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        //act
        Response response = LoginController.login(loginRequestDTO);
        LoginResponseDTO loginResponse = (LoginResponseDTO) response.getEntity();

        //assert
        Assertions.assertEquals("1234-1234-1234", loginResponse.getToken());
    }

    @Test
    void loginFCorrectTokenInAppTest(){
        //arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        //act
        Response responseLogin = LoginController.login(loginRequestDTO);
        LoginResponseDTO loginResponse = (LoginResponseDTO) responseLogin.getEntity();

        //assert

    }

    //arrange

    //act

    //assert

}