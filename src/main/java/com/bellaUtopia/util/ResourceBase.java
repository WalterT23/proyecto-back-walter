package com.bellaUtopia.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.bellaUtopia.excepciones.ApplicationException;
import com.bellaUtopia.excepciones.ManejoError;

/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 *
 * @param <E> Entity
 * @param <S> Service
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class ResourceBase<E,S extends ServiceBase<E, DaoBase<E>>> {
	
	@Context
	private ObjectMapper objectMapper;
	
//	@HeaderParam("Authorization")
//	protected String authorization;
//
//	@Inject
//	private TransactionUser user;
//	
//
//	@PostConstruct
//	public void extractUser() {
//	   user.setName(KeycloakClient.getTokenUser(authorization));
//	   HashMap<String, Object> otherAtributes = new HashMap<String, Object>();
//	   otherAtributes.put("authorization", authorization);
//	   user.setOtherAtributes(otherAtributes);
//	}

	
	public static final String ORDER_BY = "orderBy";

	public static final String ORDER_DIR = "orderDir";

	public static final String INICIO = "inicio";

	public static final String TOTAL_REGISTROS = "cantidad";

	public static final String MODE_SEARCH = "likeOr";

	public static final String VALUE_INICIO = "0";

	public static final String VALUE_REGISTROS = "10";

	public static final String PAGINA_INICIO = "1";
	
	public static final String EQUAL_AND = "false";

	protected static final Integer TODOS = -1;
	
	//el service en la clase hija va a ser inyectado
	public abstract S getService();
	
	public abstract E getEntity();
	
	protected abstract Class<E> getEntityKeyType();
	
	@GET
    @Path("/obtener")
    public Response obtener(@QueryParam("id")  Integer id) {
		E entidad = getService().obtener(id, getEntity());

        return Response.ok(entidad).build();
    }
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("listar")
	public Response listarPaginado( 
			@QueryParam("filtros") String filtrosJson,
			@QueryParam(TOTAL_REGISTROS) @DefaultValue(VALUE_REGISTROS) int cantidad, 
			@QueryParam(INICIO) @DefaultValue(VALUE_INICIO) int inicio, 
			@QueryParam(ORDER_BY) List<String> orderBy,
			@QueryParam(ORDER_DIR) List<String> orderDir,
			@QueryParam(MODE_SEARCH) @DefaultValue(EQUAL_AND) boolean likeOrSearch){
		
		Integer total = null;
        E filtros = null;
        List<E> lista = null;
        
        if (filtrosJson != null && !filtrosJson.isEmpty()) {
			try {
				
				ObjectMapper mapper = new ObjectMapper();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat.setTimeZone(TimeZone.getDefault());
				mapper.setDateFormat(dateFormat);				
				
				filtros =  mapper.readValue(filtrosJson, getEntityKeyType());
				if(filtros != null){
					likeOrSearch = true;
				}else{
					likeOrSearch = false;	
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(
					ManejoError.ERROR_ARGUMETO_MAL_FORMADO);
			}
		}else{
			filtros = getEntity();
			likeOrSearch = false;
		}
        total = getService().total(filtros, true);
        if (TODOS.equals(cantidad)) {

			lista = getService()
	        		.listarFiltrado(filtros, inicio, cantidad, orderBy, orderDir, true);
		}else{
			lista = getService()
	        		.listarFiltrado(filtros, inicio, cantidad, orderBy, orderDir, true);
		}
		
		return Response.ok(new ListaPaginada<E>(lista, total)).build();
    }
	
	
	
	@GET
	public Response listar( 
			@QueryParam(TOTAL_REGISTROS) @DefaultValue(VALUE_REGISTROS) int cantidad, 
			@QueryParam(INICIO) @DefaultValue(VALUE_INICIO) int inicio, 
			@QueryParam(ORDER_BY) List<String> orderBy,
			@QueryParam(ORDER_DIR) List<String> orderDir,
			@QueryParam(MODE_SEARCH) @DefaultValue(EQUAL_AND) boolean likeOrSearch){
        return Response.ok(getService()
        		.listarFiltrado(getEntity(), inicio, cantidad, orderBy, orderDir, likeOrSearch)).build();
    }
	
	
	@GET
	@Path("/total")
	public Response total( ){
        return Response.ok(getService().total(getEntity(),false)).build();
    }
	
	@PUT
	@Path("/modificar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificar(E e){
		return Response.ok(getService().modificar(e)).build();
	}
	
	@POST
	@Path("/insertar")
	public Response insertar(E e){
		return Response.ok(getService().insertar(e)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public void eliminar(@PathParam("id") Integer id){
		getService().eliminar(id, getEntity());
	}
}
