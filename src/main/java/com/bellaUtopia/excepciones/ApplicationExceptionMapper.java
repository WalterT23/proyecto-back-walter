package com.bellaUtopia.excepciones;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Walter Insaurralde<kirawoi@gmail.com>
 *
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	@Override
    public Response toResponse(Exception exception)
    {
		if (exception instanceof WebApplicationException) {
	        WebApplicationException webEx = (WebApplicationException)exception;
	        ExceptionEntity entidad = null;
	        int estado = webEx.getResponse().getStatus();
	        switch (estado) {
			case 400:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_400, exception.getMessage());
				break;
			case 401:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_401, exception.getMessage());
				break;
			case 403:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_403, exception.getMessage());
				break;
			case 404:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_404, exception.getMessage());
				break;
			case 405:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_405, exception.getMessage());
				break;
			case 406:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_406, exception.getMessage());
				break;
			case 415:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_415, exception.getMessage());
				break;
			case 503:
				entidad = new ExceptionEntity(estado, ManejoError.ERROR_503, exception.getMessage());
				break;

			default:
				entidad = new ExceptionEntity(500, ManejoError.ERROR_500+exception.getMessage(), exception.getMessage());
				break;
			}
	        
	        return Response
	                .status(entidad.getStatus())
	                .entity( entidad)
	                .type( MediaType.APPLICATION_JSON_TYPE)
	                .build();
	    } else {
	        return Response
	                .status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity( new ExceptionEntity(500, ManejoError.ERROR_500+exception.getMessage(), exception.getMessage()))
	                .type( MediaType.APPLICATION_JSON_TYPE)
	                .build();
	    }
       
    }

}