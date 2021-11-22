package com.bellaUtopia.excepciones;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 * Con RollBack true, para manejo de las excepciones de la aplicación
 */
@javax.ejb.ApplicationException(rollback=true)
public class ApplicationException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	public ApplicationException(){
		super();
	}

	public ApplicationException(String msg) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(new ExceptionEntity(msg)).type(MediaType.APPLICATION_JSON_TYPE).build());
	}
	
	public ApplicationException(int estado, String msg, String detalles) {
		super(Response.status(estado)
		        .entity(new ExceptionEntity(msg,detalles)).type(MediaType.APPLICATION_JSON_TYPE).build());
	}
	
	public ApplicationException(String msg, String detalles) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(new ExceptionEntity(msg,detalles)).type(MediaType.APPLICATION_JSON_TYPE).build());
	}
	
	public ApplicationException(ExceptionEntity msg) {
		super(Response.status(msg.getStatus())
		        .entity(msg).type(MediaType.APPLICATION_JSON_TYPE).build());
	}

	public ApplicationException(Exception ex) {
		super(ex, Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(ex).type(MediaType.APPLICATION_JSON_TYPE).build());
	}
}
