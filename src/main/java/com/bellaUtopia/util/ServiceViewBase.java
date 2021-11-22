package com.bellaUtopia.util;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 *
 * @param <R> El DTO Resultante
 * @param <P> El parámetro o filtro
 * @param <V> La vista
 */
public abstract class ServiceViewBase<R, P, V extends VistaBase<R, P>> extends Base<P> {


	public abstract V getVista();

	/**
	 * Lista el resultado del view
	 * @param param: pojo que contiene los filtros del view
	 * @param inicio: número de inicio de filas a traer
	 * @param cantidad: cantidad de filas a traer
	 * @param orderBy: ordenado por
	 * @param orderDir: ordenado ascendente, descendente
	 * @return Una lista de DTOs resultantes
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	public List<R> listar(P param, Integer inicio, Integer cantidad, List<String> orderBy, List<String> orderDir) {
		List<R> lista = null;

		HashMap<String, Object> parametros = getParametros(param);
		List<String> condiciones = getVista().getCondiciones(param);

		lista = getVista().generarQueryNativo(getVista().getQuery(), condiciones, parametros, inicio, cantidad, orderBy, orderDir);

		return lista;
	}
	
	/**
	 * Obtiene el total según los parámetros
	 * @param filtros: Pojo que contiene los parámetros para el select
	 * @return total de filas según filtros
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	public Integer obtenerTotal(P filtros){
		Integer total = null;
		List<String> condiciones = getVista().getCondiciones(filtros);
		HashMap<String, Object> parametros = getParametros(filtros);
		
		total = getVista().total(getVista().getQuery(), condiciones, parametros);
		return total;
	}
}
