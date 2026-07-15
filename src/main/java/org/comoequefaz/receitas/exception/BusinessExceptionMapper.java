package org.comoequefaz.receitas.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class BusinessExceptionMapper
        implements ExceptionMapper<BusinessException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BusinessException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                Response.Status.CONFLICT.getStatusCode(),
                Response.Status.CONFLICT.getReasonPhrase(),
                exception.getMessage(),
                null,
                uriInfo.getPath()
        );

        return Response.status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .build();
    }
}