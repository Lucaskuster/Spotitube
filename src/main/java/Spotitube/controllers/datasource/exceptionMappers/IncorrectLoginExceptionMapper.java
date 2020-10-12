package Spotitube.controllers.datasource.exceptionMappers;

import Spotitube.controllers.datasource.exceptions.IncorrectLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectLoginExceptionMapper implements ExceptionMapper<IncorrectLoginException> {
    @Override
    public Response toResponse(IncorrectLoginException e) {
        return null;
    }
}
