package Spotitube.lucaskuster.controllers;

import Spotitube.lucaskuster.datasource.LoginDAO;
import Spotitube.lucaskuster.datasource.exceptions.IncorrectLoginException;
import Spotitube.lucaskuster.dto.LoginRequestDTO;
import Spotitube.lucaskuster.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    private LoginController sut;
    private LoginDAO loginDAO;

    @BeforeEach
    public void setup() {
        this.sut = new LoginController();
        this.loginDAO = mock(LoginDAO.class);
        this.sut.setLoginDAO(loginDAO);
    }

    @Test
    void loginTypeTest() {
        //arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("Lucas Kuster");

        when(loginDAO.login(loginRequestDTO)).thenReturn(loginResponseDTO);

        //act
        var response = sut.login(loginRequestDTO);
        var entity = response.getEntity();

        //assert
        assertTrue(entity instanceof LoginResponseDTO);
    }

    @Test
    void loginDataTest() {
        //arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("Lucas Kuster");

        when(loginDAO.login(loginRequestDTO)).thenReturn(loginResponseDTO);

        //act
        var response = sut.login(loginRequestDTO);
        var loginResponse = (LoginResponseDTO) response.getEntity();

        //assert
        assertEquals("Lucas Kuster", loginResponse.getUser());
    }

    @Test
    void loginWrongDataTest() {
        //arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Test");
        loginRequestDTO.setPassword("Test");

        var loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("Lucas Kuster");

        when(loginDAO.login(loginRequestDTO)).thenThrow(new IncorrectLoginException());

        //act
        //assert
        assertThrows(IncorrectLoginException.class, () -> sut.login(loginRequestDTO));
    }

    @Test
    void loginCorrectTokenTest() {
        //arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("1234-1234-1234");

        when(loginDAO.login(loginRequestDTO)).thenReturn(loginResponseDTO);

        //act
        var response = sut.login(loginRequestDTO);
        var loginResponse = (LoginResponseDTO) response.getEntity();

        //assert
        assertEquals("1234-1234-1234", loginResponse.getToken());
    }

    @Test
    void loginServerError() {
        //arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Lucas");
        loginRequestDTO.setPassword("Lucas");

        var loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("Lucas Kuster");

        when(loginDAO.login(loginRequestDTO)).thenThrow(new ServerErrorException(500));

        //act
        //assert
        assertThrows(ServerErrorException.class, () -> sut.login(loginRequestDTO));
    }
}