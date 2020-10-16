package Spotitube.lucaskuster.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginServiceTest {

    private LoginService sut;

    @BeforeEach
    public void setup(){
        this.sut = new LoginService();
    }

    @Test
    public void createTokenDataTest(){
        // Act
        var token = sut.createToken();
        //assert
        assertTrue(token.contains("uuid ="));
    }

    @Test
    public void createTokenTypeTest(){
        // Act
        var token = sut.createToken();

        //assert
        assertTrue(token instanceof String);
    }
}
