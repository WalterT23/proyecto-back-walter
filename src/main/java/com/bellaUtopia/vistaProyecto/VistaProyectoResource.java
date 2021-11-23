package com.bellaUtopia.vistaProyecto;


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

@Named
@RequestScoped
@Path("/rest/vista-proyecto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VistaProyectoResource {

    @Inject
    private VistaProyectoService servicio;

    public VistaProyectoService getServicio() {
        return servicio;
    }



//    @GET
//    @Path("listar-clientes")
//    public Response listarClientes() {
//        List<Map<String, Object>> lista = null;
//        HashMap<String, Object> response = new HashMap<String, Object>();
//        lista = getServicio().listarClientes();
//
//        response.put("lista", lista);
//        response.put("totalDatos", lista.size());
//
//        return Response.ok(response).build();
//    }
    
    @GET
    @Path("listar-empleado")
    public Response listarEmpleado() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarEmpleado();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-empleado-detalles")
    public Response listarEmpleadoDetalles() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarEmpleadoDetalles();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-servicio")
    public Response listarServicio() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarServicio();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-servicio-producto")
    public Response listarServicioProducto() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarServicioProducto();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-servicio-realizado")
    public Response listarServicioRealizado(@QueryParam("idServicioRealizado") String idServicioRealizado, 
    		@QueryParam("pagado") String pagado ) {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarServicioRealizado(idServicioRealizado, pagado);

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }

     @GET
    @Path("listar-caja")
    public Response listarCaja() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarCaja();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }

    
    @GET
    @Path("listar-servicio-realizado-detalles")
    public Response listarServicioRealizadoDetalles(@QueryParam("idServicioRealizado") String idServicioRealizado) {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarServicioRealizadoDetalles(idServicioRealizado);

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
   
    @GET
    @Path("listar-inventario")
    public Response listarInventario() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarInventario();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-inventario-detalles")
    public Response listarInventarioDetalles() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarInventarioDetalles();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-facturas")
    public Response listarFacturas() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarFacturas();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-baja-stock")
    public Response listarBajaStock() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarBajaStock();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-orden-compra")
    public Response listarOrdenCompra() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarOrdenCompra();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-orden-compra-detalles")
    public Response listarOrdenCompraDetalles() {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarOrdenCompraDetalles();

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }
    
    @GET
    @Path("listar-usuario")
    public Response listarUsuario(@QueryParam("contrasenha") String contrasenha, 
    		@QueryParam("usuario") String usuario ) {
        List<Map<String, Object>> lista = null;
        HashMap<String, Object> response = new HashMap<String, Object>();
        lista = getServicio().listarUsuario(contrasenha, usuario);

        response.put("lista", lista);
        response.put("totalDatos", lista.size());

        return Response.ok(response).build();
    }


}
