package Spotitube.lucaskuster.datasource.exceptionMappers;

import Spotitube.lucaskuster.datasource.exceptions.WrongTrackIdException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WrongTrackIdExceptionMapperTest {

    @Test
    void WrongTrackIdExceptionStatusTest(){
        // Arrange
        var exception = new WrongTrackIdExceptionMapper();

        // Act en Assert
        assertEquals(404, exception.toResponse(new WrongTrackIdException()).getStatus());
    }

    @Test
    void WrongTrackIdExceptionEntityTest(){
        // Arrange
        var exception = new WrongTrackIdExceptionMapper();

        // Act
        var response = exception.toResponse(new WrongTrackIdException());

        // Assert
        assertTrue(response instanceof Response);
    }
}
