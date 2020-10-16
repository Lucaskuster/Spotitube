package nl.dea.lucaskuster.spotitube.datasource.exceptionMappers;

import nl.dea.lucaskuster.spotitube.datasource.exceptions.IncorrectTokenException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectTokenExceptionMapperTest {

    @Test
    public void IncorrectTokenExceptionStatusTest(){
        // Arrange
        var exception = new IncorrectTokenExceptionMapper();

        // Act en Assert
        assertEquals(401, exception.toResponse(new IncorrectTokenException()).getStatus() );
    }

    @Test
    public void IncorrectTokenExceptionEntityTest(){
        // Arrange
        var exception = new IncorrectTokenExceptionMapper();

        // Act
        var response = exception.toResponse(new IncorrectTokenException());

        // Assert
        assertTrue(response instanceof Response);
    }
}
