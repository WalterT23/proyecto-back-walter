package com.bellaUtopia.util;

import java.util.List;


public abstract class ServiceBase<E,D extends DaoBase<E>> {

	//el dao en la clase hija va a ser inyectado
	public abstract D getDao();
	
	public E insertar(E e ) {
		return getDao().insert((E) e);
	}
	
	public E modificar( E e){
		return getDao().modificar(e);
	}
	
	public E obtener(Object id, E e){
		E entidad = getDao().obtener(id, e);
		return entidad;
    }
	
	public E obtenerEntidad(Object id, Class<E> e){
		E entidad = getDao().obtenerEntidad(id, e);
		return entidad;
    }
	
	public List<E> listarFiltrado(E e, int inicio, int cantidad, List<String> orderBy, List<String> orderDir,
			boolean likeOrSearch) {
		return getDao().listarFiltrado(e, inicio, cantidad, orderBy, orderDir, likeOrSearch);
	}
	
	public List<E> listarFiltrado(E e) {
		return getDao().listarFiltrado(e);
	}
	
	public Integer total(E e, Boolean likeOrSearch) {
		return getDao().total(e, likeOrSearch);
	}	
	
	public void eliminar(Object id, E e){
		getDao().eliminar(id, e);
	}
	
	public void eliminarEntidad(Object id, Class<E> e){
		getDao().eliminarEntidad(id, e);
	}
	
	public String numberToString(Integer number){

		n2t numero = new n2t(number);
		return numero.convertirLetras(number);
	}
}
