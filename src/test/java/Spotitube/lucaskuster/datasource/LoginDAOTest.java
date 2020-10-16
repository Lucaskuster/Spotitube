package Spotitube.lucaskuster.datasource;

import Spotitube.lucaskuster.dto.LoginRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginDAOTest {
    private LoginDAO sut;
    private SpotitubeDBConnection connection;

    @BeforeEach
    void setup() {
        this.sut = new LoginDAO();
        this.connection = mock(SpotitubeDBConnection.class);
        this.sut.setSpotitubeDBConnection(connection);
    }

    //================================================ login ================================================

    @Test
    void loginDataTest() {
        // Arrange
        var loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setPassword("Lucas");
        var loginRequestDTODB = new LoginRequestDTO();
        loginRequestDTODB.setPassword("Lucas");

        when(sut.passwordControl(loginRequestDTO)).thenReturn(true);

//        var fixture = Mockito.mock(sut.getClass()).passwordControl(loginRequestDTO);
//        Mockito.when(fixture).thenReturn(true);
//        var passwordControl = sut.passwordControl(loginRequestDTO);
        // Act
        var loginResponse = sut.login(loginRequestDTODB);
        // Assert
        assertEquals(loginRequestDTO, loginResponse);
//        Assertions.assertEquals(passwordControl, true);
    }

    //        LoginDAO loginDAOSpy = Mockito.spy(loginDAO.passwordControl(loginRequestDTO));
//        when(loginDAOSpy.passwordControl(loginRequestDTO)).thenReturn(true);
//        Mockito.doReturn(true).when(loginDAOSpy.passwordControl(loginRequestDTO));

//        when(loginDAO.passwordControl(loginRequestDTO)).thenReturn(true);

//        boolean passwordCorrect = loginDAOSpy.login(loginRequestDTO);

//        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
//
//        Method privateMethod = LoginDAO.class.getDeclaredMethod("passwordControl", LoginRequestDTO());
//        privateMethod.setAccessible(true);
//        // invoke the private method for test
//        privateMethod.invoke(LoginDAO, null);

//        when(connection.createConnection()).thenReturn((Connection) connection);


    @Test
    void loginTypeTest() {

    }

    @Test
    void loginServerErrorTest() {

    }

    @Test
    void loginIncorrectLoginTest() {

    }

    //================================================ passwordControl ================================================


    //================================================ getUserFromToken ================================================

    @Test
    void getUserFromToken() {
    }
}