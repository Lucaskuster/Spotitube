package Spotitube.lucaskuster.datasource.exceptionMappers;

import Spotitube.lucaskuster.datasource.exceptions.IncorrectTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectTokenExceptionMapper implements ExceptionMapper<IncorrectTokenException> {

    @Override
    public Response toResponse(IncorrectTokenException e) {
        return Response.status(401).build();
    }
}