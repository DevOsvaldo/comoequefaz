package org.comoequefaz.receitas.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.Collections;

@Provider
public class EntityNotFoundExceptionMapper
        implements ExceptionMapper<EntityNotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                Response.Status.NOT_FOUND.getStatusCode(),
                Response.Status.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                Collections.emptyList(),
                uriInfo.getPath()
        );
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }


}
