package nl.dea.lucaskuster.spotitube.datasource.exceptionMappers;

import nl.dea.lucaskuster.spotitube.datasource.exceptions.WrongPlaylistIdException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WrongPlaylistIdExceptionMapper implements ExceptionMapper<WrongPlaylistIdException> {
    @Override
    public Response toResponse(WrongPlaylistIdException e) {
        return Response.status(404).build();
    }
}
