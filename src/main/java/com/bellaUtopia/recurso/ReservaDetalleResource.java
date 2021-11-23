package com.bellaUtopia.recurso;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bellaUtopia.entidad.ReservaDetalle;
import com.bellaUtopia.servicio.ReservaDetalleService;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;

@Named
@RequestScoped
@Path("/rest/reserva-detalle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaDetalleResource extends ResourceBase<ReservaDetalle, ServiceBase<ReservaDetalle, DaoBase<ReservaDetalle>>> {

	@Inject
	private ReservaDetalleService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public ReservaDetalle getEntity() {
		return new ReservaDetalle();
	}

	@Override
	protected Class<ReservaDetalle> getEntityKeyType() {
		return ReservaDetalle.class;
	}
}