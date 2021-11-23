package com.bellaUtopia.recurso;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bellaUtopia.entidad.Horario;
import com.bellaUtopia.servicio.HorarioService;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;

@Named
@RequestScoped
@Path("/rest/horario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HorarioResource extends ResourceBase<Horario, ServiceBase<Horario, DaoBase<Horario>>> {

	@Inject
	private HorarioService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public Horario getEntity() {
		return new Horario();
	}

	@Override
	protected Class<Horario> getEntityKeyType() {
		return Horario.class;
	}
}