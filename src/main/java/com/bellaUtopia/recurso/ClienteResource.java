package com.bellaUtopia.recurso;

import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.entidad.Restaurante;
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
@Path("/rest/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource extends ResourceBase<Cliente, ServiceBase<Cliente, DaoBase<Cliente>>> {

	@Inject
	private RestauranteService service;

	@Override
	public ServiceBase getService() {
		return service;
	}

	@Override
	public Cliente getEntity() {
		return new Cliente();
	}

	@Override
	protected Class<Cliente> getEntityKeyType() {
		return Cliente.class;
	}
}