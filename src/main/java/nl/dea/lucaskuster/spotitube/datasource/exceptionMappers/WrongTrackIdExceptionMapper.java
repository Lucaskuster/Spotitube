package nl.dea.lucaskuster.spotitube.datasource.exceptionMappers;

import nl.dea.lucaskuster.spotitube.datasource.exceptions.WrongTrackIdException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WrongTrackIdExceptionMapper implements ExceptionMapper<WrongTrackIdException> {
    @Override
    public Response toResponse(WrongTrackIdException e) {
        return Response.status(404).build();
    }
}
