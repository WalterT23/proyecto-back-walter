package com.bellaUtopia.util;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bellaUtopia.excepciones.ApplicationException;
import com.bellaUtopia.excepciones.ManejoError;

/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 *
 * @param <T> Implementación de la entidad
 */
@Stateless
public class DaoBase<T> {

	@PersistenceContext
	public EntityManager em;

	private Class<T> entityBeanType;

	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Obtener una fila a partir del id
	 * @param id : identificación de la tabla
	 * @param e : entidad
	 * @return Retorna la entidad mapeada
	 * @throws ApplicationException
	 * 
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	public T obtener(Object id, T e) throws ApplicationException {
		try {
			return (T) getEntityManager().find(e.getClass(), id);
		} catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_OBTENER, exp.getMessage());
		}

	}
	


	/**
	 * Obtener una fila a partir del id
	 * @param id : identificación de la tabla
	 * @param e : entidad.Class
	 * @return Retorna la entidad mapeada
	 * @throws ApplicationException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T obtenerEntidad(Object id, Class<T> e) throws ApplicationException {
		try {
			return (T) getEntityManager().find(e, id);
		} catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_OBTENER, exp.getMessage());
		}

	}
	
	/**
	 * Insertar una fila a la tabla
	 * @param t
	 * @return
	 */
	@Transactional
	public T insert(T t) {
		try{
			getEntityManager().persist(t);
		}catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_INSERTAR, exp.getMessage());
		}
		return t;
	}

	/**
	 * Se obtiene por alguna propiedad única, igual se prevé que no traiga más filas
	 * @param t : La entidad
	 * @param parametros : filtros
	 * @param like : si es like mode o la clausula es =
	 * @return el entity filtrado
	 */
	@SuppressWarnings("unchecked")
	public T obtenerPorNombre(T t,  HashMap<String, Object> parametros, Boolean like){
			String schema = getSchemaFromEntity(t);
			String table = getTableNameFromEntity(t);
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ").append(schema).append(".").append(table).append(" ");
			StringBuilder where = new StringBuilder();
			int i = 0;
			for (String key : parametros.keySet()) {
				if (i > 0 )
					where.append(" AND ");
				else
					where.append(" WHERE ");
				where.append(like ? key+" like '%'||:"+key+"||'%' " :  key+" = :"+key);
				i++;
			}
			sql.append(where);
			
			//Se trae el total para que sólo se haga el select el el total es 1
			StringBuilder sqlTotal = new StringBuilder();
			sqlTotal.append("select count(*) from (")
			.append(sql)
			.append(") as q ");
			BigInteger total = null;
			try{
				Query q = em.createNativeQuery(sqlTotal.toString());
				parametros.forEach((k,v) ->q.setParameter(k.toString(), v));
				total= (BigInteger)q.getSingleResult();
			}catch (Exception e) {
				throw new ApplicationException(ManejoError.ERROR_QUERY_NATIVO+sqlTotal.toString(), e.getMessage());
			}
			System.out.println(total);
			if(total.intValue() == 1){
				try{
					Query q = em.createNativeQuery(sql.toString()
						,t.getClass());
				
					parametros.forEach((k,v) ->q.setParameter(k.toString(), v));	
					t = (T)q.getSingleResult();
				}catch (Exception e) {
					throw new ApplicationException(ManejoError.ERROR_QUERY_NATIVO+sql.toString(), e.getMessage());
				}
			}else{
				throw new ApplicationException(ManejoError.ERROR_ENTITY_NULL_VACIO,ManejoError.ERROR_RESULTADO_MULTIPLE );
			}
		
		return t;
	}
	/**
	 * Modificar una fila de la tabla
	 * @param t
	 * @return
	 * @throws ApplicationException
	 */
	//@SuppressWarnings("unchecked")
	@Transactional
	public T modificar(T t) throws ApplicationException {
		try {
			/*T o = (T) getEntityManager().find(t.getClass(), id);
			System.out.println(o);
			if (o == null) {
				throw new ApplicationException("El objeto a actualizar no existe");
			}*/
			getEntityManager().merge(t);
			return t;
		} catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_ACTUALIZAR, exp.getMessage());
		
		}
	}
	
	/**
	 * Eliminar una fila de la tabla
	 * @param id
	 * @param t
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void eliminar(Object id, T t) throws ApplicationException {
		try {
			T o = (T) getEntityManager().find(t.getClass(), id);
			if (o == null) {
				throw new ApplicationException(ManejoError.ERROR_OBJETO_NULL);
			}
			getEntityManager().remove(o);
		} catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_ELIMINAR, exp.getMessage());
		}
	}
	/**
	 * Eliminar una fila de la tabla
	 * @param id
	 * @param t
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void eliminarEntidad(Object id, Class<T>  t) throws ApplicationException {
		try {
			T o = (T) getEntityManager().find(t , id);
			if (o == null) {
				throw new ApplicationException(ManejoError.ERROR_OBJETO_NULL);
			}
			getEntityManager().remove(o);
		} catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_ELIMINAR, exp.getMessage());
		}
	}
	

	
	/**
	 * Lista filtrado para traer la lista paginada
	 * @param t
	 * @param inicio
	 * @param cantidad
	 * @param orderBy
	 * @param orderDir
	 * @param likeOrSearch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> listarFiltrado(T t, int inicio, int cantidad, List<String> orderBy, List<String> orderDir,
			boolean likeOrSearch) throws ApplicationException {
		
		List<T> lista = null;
		try{
			Session session = (Session) getEntityManager().getDelegate();
			Criteria crit = session.createCriteria(t.getClass());
			crit.setFirstResult(inicio);
			if (cantidad != -1) {
				crit.setMaxResults(cantidad);
			}
			if (likeOrSearch) {
				Criterion disjunt = likeOrRestriction(t);
				crit.add(disjunt);
			} else {
				crit.add(Example.create(t));
			}
			int i;
			
			if (orderBy.size() == orderDir.size()) {
				for (i = 0; i < orderBy.size(); i++) {
					System.out.println(orderDir.get(i));
					if (orderDir.get(i).equalsIgnoreCase("asc")) {
						crit.addOrder(Order.asc(orderBy.get(i)));
					}
					if (orderDir.get(i).equalsIgnoreCase("desc")) {
						crit.addOrder(Order.desc(orderBy.get(i)));
					}
				}
			}
			lista = crit.list();
		}catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_LISTAR, exp.getMessage());
		}
		
		return lista;
	}
	
	/**
	 * Lista filtrada sin paginar
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> listarFiltrado(T t) throws ApplicationException {
		
		List<T> lista = null;
		try{
			Session session = (Session) getEntityManager().getDelegate();
			Criteria crit = session.createCriteria(t.getClass());
				crit.add(Example.create(t));
			lista = crit.list();
		}catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_LISTAR, exp.getMessage());
		}
		
		return lista;
	}

	/**
	 * genera la codición de tipo
	 * LIKE o = 
	 * 
	 * @param t
	 * @return
	 */
	private Criterion likeOrRestriction(T t) throws ApplicationException {
		List<Criterion> predicateList = new ArrayList<Criterion>();

		for (Field f : t.getClass().getDeclaredFields()) {
			if (f.getAnnotation(Column.class) != null) {
				f.setAccessible(true);
				Object fieldValue = null;

				try {
					fieldValue = f.get(t);
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}

				// Si el campo es null, se ignora como restriccion
				if (fieldValue == null)
					continue;

				if (f.getType() == String.class) {
					// Comparacion con like
					predicateList
							.add(Restrictions.like(f.getName(), (String) fieldValue, MatchMode.START).ignoreCase());
				} else {
					// Comparacion con operador de igualdad
					predicateList.add(Restrictions.eq(f.getName(), fieldValue));
				}
			}
		}

		return Restrictions.or(predicateList.toArray(new Criterion[0]));
	}
	
	/**
	 * Retorna el total según los criterios del select
	 * @param t
	 * @param likeOrSearch
	 * @return
	 */
	public Integer total(T t, boolean likeOrSearch) throws ApplicationException {
		Integer total = null;
		try{
			Session session = (Session) getEntityManager().getDelegate();
			Criteria crit = session.createCriteria(t.getClass());
			if (likeOrSearch) {
				Criterion disjunt = likeOrRestriction(t);
				crit.add(disjunt);
			} else {
				crit.add(Example.create(t));
			}
			crit.setProjection(Projections.rowCount());
			Long totalResults = (Long) crit.uniqueResult();
			if (totalResults == null) {
				totalResults = new Long(0);
			}
			total = totalResults.intValue();
		}catch (Exception exp) {
			throw new ApplicationException(ManejoError.ERROR_LISTAR, exp.getMessage());
		}
		return total;
	}

	private String getSchemaFromEntity(T t){
		return t.getClass().getAnnotation(Table.class).schema();
	}
	
	private String getTableNameFromEntity(T t){
		return t.getClass().getAnnotation(Table.class).name();
	}
}
