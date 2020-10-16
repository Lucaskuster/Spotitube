package Spotitube.lucaskuster.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {

    private PlaylistService sut;

    @BeforeEach
    public void setup(){
        this.sut = new PlaylistService();
    }

    @Test
    public void checkOwnerDataCorrectTest(){
        // Arrange
        var ingelogdeUser = "Lucas";
        var eigenaar = "Lucas";

        // Act
        var owner = sut.checkOwner(ingelogdeUser, eigenaar);

        // Assert
        assertTrue(owner);
    }

    @Test
    public void checkOwnerDataIncorrectTest(){
        // Arrange
        var ingelogdeUser = "Lucas";
        var eigenaar = "NietLucas";

        // Act
        var owner = sut.checkOwner(ingelogdeUser, eigenaar);

        // Assert
        assertFalse(owner);
    }

//    @Test
//    public void checkOwnerTypeTest(){
//        // Arrange
//        var ingelogdeUser = "Lucas";
//        var eigenaar = "Lucas";
//
//        // Act
//        var response = sut.checkOwner(ingelogdeUser, eigenaar);
//
//        //assert
//        assertEquals(true, response instanceof boolean);
//    }
}