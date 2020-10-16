package Spotitube.lucaskuster.datasource.exceptionMappers;

import Spotitube.lucaskuster.datasource.exceptions.IncorrectLoginException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectLoginExceptionMapperTest {

    @Test
    public void IncorrectLoginExceptionStatusTest(){
        // Arrange
        var exception = new IncorrectLoginExceptionMapper();

        // Act en Assert
        assertEquals(401, exception.toResponse(new IncorrectLoginException()).getStatus() );
    }

    @Test
    public void IncorrectLoginExceptionEntityTest(){
        // Arrange
        var exception = new IncorrectLoginExceptionMapper();

        // Act
        var response = exception.toResponse(new IncorrectLoginException());

        // Assert
        assertTrue(response instanceof Response);
    }
}
