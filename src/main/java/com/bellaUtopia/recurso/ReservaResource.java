package com.bellaUtopia.recurso;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.entidad.Reserva;
import com.bellaUtopia.param.ReservaParam;
import com.bellaUtopia.servicio.ReservaService;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;
import com.bellaUtopia.vistaProyecto.VistaProyectoService;

@Named
@RequestScoped
@Path("/rest/reserva")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource extends ResourceBase<Reserva, ServiceBase<Reserva, DaoBase<Reserva>>> {

	@Inject
	private ReservaService service;
	
	@Inject
	private VistaProyectoService vistaProyectoService;

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
	
	@POST
	@Path("/realizar-reserva")
	public Response realizarReserva(ReservaParam reservaParam){
		return Response.ok( service.realizarReserva(reservaParam)).build();
	}
	
	@POST
	@Path("/listar-reserva")
	public Response realizarReserva(Cliente cliente){
		return Response.ok( vistaProyectoService.listarReserva(cliente)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public void eliminar(@PathParam("id") Integer id){
		service.eliminarReserva(id);
	}
}