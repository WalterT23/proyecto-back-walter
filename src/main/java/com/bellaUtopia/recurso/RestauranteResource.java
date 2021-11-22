package com.bellaUtopia.recurso;

import javax.inject.Inject;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;
import com.bellaUtopia.servicio.RestauranteService;
import com.bellaUtopia.entidad.Restaurante;

@Named
@RequestScoped
@Path("/rest/restaurante")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteResource extends ResourceBase<Restaurante, ServiceBase<Restaurante, DaoBase<Restaurante>>> {

	@Inject
	private RestauranteService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public Restaurante getEntity() {
		return new Restaurante();
	}

	@Override
	protected Class<Restaurante> getEntityKeyType() {
		return Restaurante.class;
	}
}