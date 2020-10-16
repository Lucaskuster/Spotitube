package Spotitube.lucaskuster.datasource.exceptionMappers;

import Spotitube.lucaskuster.datasource.exceptions.WrongPlaylistIdException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WrongPlaylistIdExceptionMapperTest {

    @Test
    public void WrongPlaylistIdExceptionStatusTest(){
        // Arrange
        var exception = new WrongPlaylistIdExceptionMapper();

        // Act en Assert
        assertEquals(404, exception.toResponse(new WrongPlaylistIdException()).getStatus() );
    }

    @Test
    public void WrongPlaylistIdExceptionEntityTest(){
        // Arrange
        var exception = new WrongPlaylistIdExceptionMapper();

        // Act
        var response = exception.toResponse(new WrongPlaylistIdException());

        // Assert
        assertTrue(response instanceof Response);
    }
}
