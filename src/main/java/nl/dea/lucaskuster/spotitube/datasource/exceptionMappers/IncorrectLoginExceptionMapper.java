package nl.dea.lucaskuster.spotitube.datasource.exceptionMappers;

import nl.dea.lucaskuster.spotitube.datasource.exceptions.IncorrectLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectLoginExceptionMapper implements ExceptionMapper<IncorrectLoginException> {
    @Override
    public Response toResponse(IncorrectLoginException e) {
        return Response.status(401).build();
    }
}
