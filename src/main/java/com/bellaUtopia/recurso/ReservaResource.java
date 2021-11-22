package com.bellaUtopia.recurso;

import com.bellaUtopia.entidad.Reserva;
import com.bellaUtopia.servicio.ReservaService;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
@Path("/rest/reserva")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource extends ResourceBase<Reserva, ServiceBase<Reserva, DaoBase<Reserva>>> {

	@Inject
	private ReservaService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public Reserva getEntity() {
		return new Reserva();
	}

	@Override
	protected Class<Reserva> getEntityKeyType() {
		return Reserva.class;
	}
}