package com.learn.javaweb.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApiWebException extends WebApplicationException {
    private static final long serialVersionUID = 1L;

	public ApiWebException(int statusCode, String message) {
        super(Response.status(statusCode)
                      .entity(message)
                      .type(MediaType.APPLICATION_JSON)
                      .build());
    }
}
