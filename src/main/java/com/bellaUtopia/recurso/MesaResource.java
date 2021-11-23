package com.bellaUtopia.recurso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bellaUtopia.entidad.Mesa;
import com.bellaUtopia.servicio.MesaService;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ResourceBase;
import com.bellaUtopia.util.ServiceBase;
import com.bellaUtopia.vistaProyecto.VistaProyectoService;

@Named
@RequestScoped
@Path("/rest/mesa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MesaResource extends ResourceBase<Mesa, ServiceBase<Mesa, DaoBase<Mesa>>> {

	@Inject
	private MesaService service;
	
	@Inject
	private VistaProyectoService vistaProyectoService;

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
	
	@GET
	@Path("mesas-libres")
	public Response listadoMesas( 
			@QueryParam("idRestaurante") Integer idRestaurante,
			@QueryParam("fecha") String fecha,
			@QueryParam("horarios") String horarios){

        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = vistaProyectoService.listadoMesas(idRestaurante, fecha, horarios);

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
	}
	
	@GET
	@Path("mesas-reservadas")
	public Response listadoMesasReservadas( 
			@QueryParam("idRestaurante") Integer idRestaurante,
			@QueryParam("fecha") String fecha,
			@QueryParam("horarios") String horarios){

        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = vistaProyectoService.listadoMesasReservadas(idRestaurante, fecha, horarios);

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
	}
}