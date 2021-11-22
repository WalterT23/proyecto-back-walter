package com.bellaUtopia.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bellaUtopia.excepciones.ApplicationException;
import com.bellaUtopia.excepciones.ManejoError;

/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 *
 * @param <V> Implementación del DTO (Respuesta mapeada del view)
 * @param <P> Implementación del Param (Filtros de la sentencia)
 */
@Stateless
public abstract class VistaBase<V, P> {

	public static final String ORDER_BY = "orderBy";

	public static final String ORDER_DIR = "orderDir";

	public static final String INICIO = "inicio";

	public static final String TOTAL_REGISTROS = "cantidad";
	
	public abstract String getQuery();
	
	public abstract List<String> getCondiciones(P p);
	
	public abstract Class<V> getDTOType();
	
	@PersistenceContext
	public EntityManager em;
	
	private EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * Obtiene el query, embebe en un select mayor para traer paginado
	 * 
	 * @param query : el select
	 * @param condiciones: Lista de condiciones sin WHERE, ni AND
	 * @param parametros : parámetros no nulos
	 * @param inicio : desde donde se pagina
	 * @param cantidad : cantidad a paginar
	 * @param orderBy : ordenado por
	 * @param orderDir : dirección del ordenamiento
	 * 
	 * @return Lista de DTO correspondiente a la respuesta
	 * 
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	public List<V> generarQueryNativo(String query,List<String> condiciones, HashMap<String, Object> parametros,
			Integer inicio, Integer cantidad, List<String> orderBy, List<String> orderDir) throws ApplicationException{
		List<V> values = null;
		String sql = armarQuery(query+obtenerCondiciones(condiciones), inicio, cantidad, orderBy, orderDir);
		try{
			Query q = em.createNativeQuery(sql
				,getDTOType());
		
			parametros.forEach((k,v) ->q.setParameter(k.toString(), v));	
			values = q.getResultList();
			
		}catch (Exception e) {
			throw new ApplicationException(ManejoError.ERROR_QUERY_NATIVO+sql,e.getMessage());
		}
		return values;
		
	}
	
	
	
	/**
	 * Retorna un string con las condiciones para el sql nativo.
	 * 
	 * @param condiciones: proveídos por el view para filtrar la sentencia
	 * @return un string del formato WHERE condicion1 AND condicion2 ...
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	private String obtenerCondiciones(List<String> condiciones) {
		StringBuilder where = new StringBuilder();
		int i = 0;
		for (String condicion : condiciones) {
			if (i > 0 )
				where.append(" AND ");
			else
				where.append(" WHERE ");
			where.append(condicion);
			i++;
		}
		return where.toString();
	}
	
	/**
	 * Retorna un string con el siguiente formato
	 * SELECT * FROM (el select del view) q
	 * order by orden direccion, orden direccion LIMIT cantidad SETOFF inicio
	 * @param query : query del view
	 * @param cantidad: cantidad a traer
	 * @param inicio : desde cuál fila va a traer
	 * @param orderBy : Ordenado por ...
	 * @param orderDir: Dirección de ordenado ...
	 * @return Un string un el select a ejecutar
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	private String armarQuery( String query, Integer inicio, Integer cantidad, List<String> orderBy, List<String> orderDir){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from (")
		.append(query)
		.append(") as q ");
		int i = 0;
		if(orderBy != null  && orderDir != null ){
			if(orderBy.size() > 0 && (orderBy.size() == orderDir.size())){
				sql.append(" order by ");
				for(String ordenar : orderBy){
					if(i > 0)
						sql.append(",");
					sql.append(ordenar).append(" ").append(orderDir.get(i));
					i++;
				}
			}
		}
		if(inicio != null && cantidad != null){
			sql.append(" LIMIT ").append(cantidad)
				.append(" OFFSET ").append(inicio);
		}
		
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	
	
	
	
	/**
	 * Retorna el total de filas resultantes de la sentencia
	 * 
	 * @param query Sentencia sql del view
	 * @param condiciones Condiciones proveidas por el view
	 * @param parametros no nulos obtenidos desde la petición
	 * @return Total de filas resultantes de la sentencia
	 * 
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	public Integer total(String query, List<String> condiciones,HashMap<String, Object> parametros) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from (")
		.append(query+obtenerCondiciones(condiciones))
		.append(") as q ");
		BigInteger total = null;
		try{
			Query q = em.createNativeQuery(sql.toString());
			parametros.forEach((k,v) ->q.setParameter(k.toString(), v));
			total= (BigInteger)q.getSingleResult();
		}catch (Exception e) {
			throw new ApplicationException(ManejoError.ERROR_QUERY_NATIVO+sql.toString(), e.getMessage());
		}
		
		return total.intValue();
	}
	

}
