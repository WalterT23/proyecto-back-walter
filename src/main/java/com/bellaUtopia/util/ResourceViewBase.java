package com.bellaUtopia.util;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
 * @param <E> Corresponde al ViewDTO o resultado
 * @param <P> ViewParam, filtros de la vista
 * @param <V> El View que implementa el query y sus condiciones
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class ResourceViewBase<E,P,V extends VistaBase<E,P>,S extends ServiceViewBase<E,P, V>> extends Base<P>{
	
	@Context
	private ObjectMapper objectMapper;
	
	
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
	
	
	public abstract S getServicio();
	
	public abstract E getDTO();
	
	protected abstract Class<P> getParamType();
	
	
	
	
	
	
	/**
	 * Retorna un view paginado
	 * 
	 * @param filtrosJson : filtros posibles para el view
	 * @param cantidad: cantidad a traer
	 * @param inicio : desde cuál fila va a traer
	 * @param orderBy : Ordenado por ...
	 * @param orderDir: Dirección de ordenado ...
	 * 
	 * @return Responde una ListaPagina{Lista, total}
	 * 
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 * 
	 */
	@GET
	@Path("vista")
	public Response listarPaginado( 
			@QueryParam("filtros") String filtrosJson,
			@QueryParam(TOTAL_REGISTROS) @DefaultValue(VALUE_REGISTROS) int cantidad, 
			@QueryParam(INICIO) @DefaultValue(VALUE_INICIO) int inicio, 
			@QueryParam(ORDER_BY) List<String> orderBy,
			@QueryParam(ORDER_DIR) List<String> orderDir){
		Integer total = null;
        P filtros = null;
        List<E> lista = null;
        
        if (filtrosJson != null && !filtrosJson.isEmpty()) {
			try {
				filtros =  new ObjectMapper().readValue(filtrosJson, getParamType());
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(
					ManejoError.ERROR_ARGUMETO_MAL_FORMADO);
			}
		}else{
			throw new ApplicationException(
					ManejoError.ERROR_ARGUMETO_MAL_FORMADO, ManejoError.ERROR_REQUIERE_FILTROS);
		}
        
        total = getServicio().obtenerTotal(filtros);
        if (TODOS.equals(cantidad)) {

			lista = getServicio().listar(filtros,
	        				null, null, null, null);
		}else{
			lista = getServicio().listar(filtros,
					inicio, cantidad, orderBy, orderDir);
		}
		
		return Response.ok(new ListaPaginada<E>(lista, total)).build();
    }
	
	
}
