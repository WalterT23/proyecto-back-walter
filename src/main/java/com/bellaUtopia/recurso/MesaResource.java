package com.bellaUtopia.recurso;

import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.entidad.Mesa;
import com.bellaUtopia.servicio.MesaService;
import com.bellaUtopia.servicio.RestauranteService;
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
@Path("/rest/mesa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MesaResource extends ResourceBase<Mesa, ServiceBase<Mesa, DaoBase<Mesa>>> {

	@Inject
	private MesaService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public Mesa getEntity() {
		return new Mesa();
	}

	@Override
	protected Class<Mesa> getEntityKeyType() {
		return Mesa.class;
	}
}