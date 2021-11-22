package com.bellaUtopia.excepciones;

public class ExceptionEntity {

	private Integer estado;
	private String error;
	private String mensaje;

	public ExceptionEntity(Integer estado, String error, String mensaje) {
		super();
		this.estado = estado;
		this.error = error;
		this.mensaje = mensaje;
	}
	
	public ExceptionEntity(String error, String mensaje) {
		super();
		this.error = error;
		this.estado = 500;
		this.mensaje = mensaje;
	}

	public ExceptionEntity(String error) {
		super();
		this.error = error;
		this.estado = 1;
	}

	public Integer getStatus() {
		return estado;
	}

	
	public void setStatus(Integer status) {
		this.estado = status;
	}

	public String getHeader() {
		return error;
	}

	public void setHeader(String header) {
		this.error = header;
	}

	public String getMessages() {
		return mensaje;
	}

	public void setMessages(String messages) {
		this.mensaje = messages;
	}

}
