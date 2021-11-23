package com.bellaUtopia.vistaProyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.excepciones.ApplicationException;

public class VistaProyectoService {


	@PersistenceContext
	public EntityManager em;
	private String query = "";
	private Boolean first = true;

	public List<Map<String, Object>> listadoMesas() throws ApplicationException {
		try {
			List<Object[]> postComments = null;
			String query = "   select m2.nro_piso nroPiso, m2.nombre_mesa nombreMesa, m2.posicion, m2.capacidad,\n" +
					"   case when r.id != null then 'NO' else 'SI' end disponible,\n" +
					"   h2.inicio_horario inicio, h2.fin_horario fin " +
					"   from mesas m2\n" +
					"   join restaurante r2 on m2.id_restaurante = r2.id\n" +
					"   left join reserva r on r2.id = r.id_restaurante and r.fecha = '2021-11-23' \n" +
					"   left join reserva_detalle rd on r.id = rd.id_reserva \n" +
					"   left join horario h2 on rd.id_horario = h2.id " ;
			query += "where r2.id ";

			postComments = em.createNativeQuery(query.toString()).
					getResultList();

			List<Map<String, Object>> resultlist = new ArrayList<>();
			for (Object[] oResultArray : postComments) {
				Map<String, Object> oMapResult = new HashMap<>();
				oMapResult.put("nroPiso", oResultArray[0]);
				oMapResult.put("nombreMesa", oResultArray[1]);
				oMapResult.put("posicion", oResultArray[2]);
				oMapResult.put("capacidad", oResultArray[3]);
				oMapResult.put("disponible", oResultArray[4]);
				oMapResult.put("inicio", oResultArray[5]);
				oMapResult.put("fin", oResultArray[6]);

				resultlist.add(oMapResult);
			}
			return resultlist;

		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	


	public  List<Map<String, Object>> listarReserva(Cliente cliente) throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select r.id as idReserva, r.fecha, r.cantidad as cantidadPersonas, h.inicio_horario, h.fin_horario,\r\n" + 
						"c.cedula, c.nombre, c.apellido, rest.nombre as nombreRestaurante, m.nro_piso, m.nombre_mesa, m.posicion\r\n" + 
						"from reserva r\r\n" + 
						"join reserva_detalle rd ON (rd.id_reserva = r.id)\r\n" + 
						"join horario h ON (h.id = rd.id_horario)\r\n" + 
						"join cliente c ON (c.id = r.id_cliente)\r\n" + 
						"join restaurante rest ON (rest.id = r.id_restaurante)\r\n" + 
						"join mesas m ON (m.id = r.id_mesa) ";
				
				query += " WHERE c.id = "+cliente.getId().toString()+ " OR c.cedula = '"+cliente.getCedula()+"'";


		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idReserva", oResultArray[0]);
		            oMapResult.put("fechaReserva", oResultArray[1]);	
		            oMapResult.put("cantidadPersonas", oResultArray[2]);
		            oMapResult.put("inicio_horario", oResultArray[3]);
		            oMapResult.put("fin_horario", oResultArray[4]);
		            oMapResult.put("cedula", oResultArray[5]);
		            oMapResult.put("nombre", oResultArray[6]);
		            oMapResult.put("apellido", oResultArray[7]);
		            oMapResult.put("nombreRestaurante", oResultArray[8]);
		            oMapResult.put("nro_piso", oResultArray[9]);
		            oMapResult.put("nombre_mesa", oResultArray[10]);
		            oMapResult.put("posicion", oResultArray[11]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarEmpleado() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select p.nombres as Nombre, p.apellidos as Apellido, p.documento as cedula, p.telefono as Telefono, "
						+ "em.fecha_ingreso, em.fecha_salida, em.id_empleado, em.id_persona, p.email as email "
					+  "  from empleado em "
						  + " join persona p ON p.id_persona=em.id_persona ";


		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("nombre", oResultArray[0]);
		            oMapResult.put("apellido", oResultArray[1]);	
		            oMapResult.put("cedula", oResultArray[2]);
		            oMapResult.put("telefono", oResultArray[3]);
		            oMapResult.put("fecha_ingreso", oResultArray[4]);
		            oMapResult.put("fecha_salida", oResultArray[5]);
		            oMapResult.put("id_empleado", oResultArray[6]);
		            oMapResult.put("id_persona", oResultArray[7]);
		            oMapResult.put("email", oResultArray[8]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarEmpleadoDetalles() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select de.salario,de.fecha_asignacion, r.descripcion, de.id_empleado, de.id_empleado_detalles, r.id_rubro "
					+ "	from empleado_detalles de "
					+ "	join rubro r ON r.id_rubro=de.id_rubro " ;


		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("salario", oResultArray[0]);
		            oMapResult.put("fecha_asignacion", oResultArray[1]);	
		            oMapResult.put("descripcion", oResultArray[2]);	
		            oMapResult.put("idEmpleado", oResultArray[3]);	
		            oMapResult.put("idEmpleadoDetalles", oResultArray[4]);
		            oMapResult.put("idRubro", oResultArray[5]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	

public  List<Map<String, Object>> listarServicio() throws ApplicationException{
	try{				
			List<Object[]> postComments = null;		
			
			String query = " select s.descripcion as descripcion, s.precio as precio, sum(sp.costo_producto) as costo_producto,s.iva as iva, "
					+ " s.id_servicio, s.id_rubro as id_rubro, r.descripcion as rubro, sum(sp.cant_producto) as cant_producto "
					+ " from (servicio s full join servicio_productos sp on s.id_servicio = sp.id_servicio) "
					+ " join rubro r ON r.id_rubro = s.id_rubro "
					+ " group by s.descripcion, s.precio, s.id_servicio, s.id_rubro, r.descripcion ";


	        postComments = em.createNativeQuery(query.toString()).
	                getResultList();

	        List<Map<String, Object>> resultlist = new ArrayList<>();
	        for (Object[] oResultArray : postComments) {
	            Map<String, Object> oMapResult = new HashMap<>();
	            oMapResult.put("descripcion", oResultArray[0]);
	            oMapResult.put("precio", oResultArray[1]);	
	            oMapResult.put("costo_producto", oResultArray[2]);
	            oMapResult.put("iva", oResultArray[3]);
	            oMapResult.put("id_servicio", oResultArray[4]);
	            oMapResult.put("id_rubro", oResultArray[5]);
	            oMapResult.put("rubro", oResultArray[6]);
	            oMapResult.put("cant_producto", oResultArray[7]);
	         resultlist.add(oMapResult);
	        }
	        return resultlist;
	}catch (Exception e) {
		throw new ApplicationException(e);
	}		
}

	public  List<Map<String, Object>> listarServicioRealizado(String idServicioRealizado, String pagado) throws ApplicationException{
		try{				
				List<Object[]> postComments = null;	
				first = true;
				query = " select sr.id_servicio_realizado, p.documento, p.nombres, p.apellidos, sr.monto_total, sr.fecha_operacion, sr.id_cliente, sr.pagado "
				+ " FROM servicio_realizado sr "
				+ " JOIN cliente c ON sr.id_cliente = c.id_cliente "
				+ " JOIN persona p ON c.id_persona = p.id_persona ";
				if(idServicioRealizado != null){
					this.addWhere(query, first);
					query += " sr.id_servicio_realizado = "+idServicioRealizado;
				}				

				if(pagado != null){
					this.addWhere(query, first);
					query += " sr.pagado = "+pagado;
				}

				query += " order by sr.id_servicio_realizado desc ";
		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();


		            oMapResult.put("idServicioRealizado", oResultArray[0]);
		            oMapResult.put("documento", oResultArray[1]);	
		            oMapResult.put("nombres", oResultArray[2]);
		            oMapResult.put("apellidos", oResultArray[3]);
		            oMapResult.put("montoTotal", oResultArray[4]);
		            oMapResult.put("fechaOperacion", oResultArray[5]);
		            oMapResult.put("idCliente", oResultArray[6]);
		            oMapResult.put("pagado", oResultArray[7]);


		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarServicioProducto() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select sp.id_servicio_productos, sp.id_servicio, sp.cant_producto, sp.costo_producto, p.id_producto, p.descripcion "
					+ "	from servicio_productos sp "
					+ "	join producto p ON p.id_producto=sp.id_producto " ;


		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idServicioProductos", oResultArray[0]);
		            oMapResult.put("idServicio", oResultArray[1]);
		            oMapResult.put("cantProducto", oResultArray[2]);
		            oMapResult.put("costoProducto", oResultArray[3]);
		            oMapResult.put("idProducto", oResultArray[4]);	
		            oMapResult.put("descripcion", oResultArray[5]);	
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarCaja() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select c.id_caja, p.nombres, p.apellidos , c.fecha, c.monto_total_ingreso, c.monto_total_egreso, c.id_empleado, c.estado "
								+ " from caja c "
								+ " join empleado e ON e.id_empleado=c.id_empleado "
								+ " join persona p ON p.id_persona=e.id_persona " ;

								 postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idCaja", oResultArray[0]);
		            oMapResult.put("nombres", oResultArray[1]);
		            oMapResult.put("apellidos", oResultArray[2]);
		            oMapResult.put("fecha", oResultArray[3]);
		            oMapResult.put("monto_total_ingreso", oResultArray[4]);	
		            oMapResult.put("monto_total_egreso", oResultArray[5]);	
		            oMapResult.put("idEmpleado", oResultArray[6]);	
		            oMapResult.put("estado", oResultArray[7]);	
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarServicioRealizadoDetalles(String idServicioRealizado) throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " SELECT srd.id_servicio_realizado_detalles, srd.id_servicio, srd.id_servicio_realizado, srd.id_empleado, r.id_rubro, "
						+ " s.descripcion as descripcion_servicio, s.precio, s.iva as porcentaje_iva, r.descripcion as descripcion_rubro, p.nombres, p.apellidos "
						+ " FROM servicio_realizado_detalles srd  "
						+ " JOIN servicio s ON srd.id_servicio = s.id_servicio "
						+ " JOIN empleado e ON srd.id_empleado = e.id_empleado "
						+ " JOIN persona p ON e.id_persona = p.id_persona "
						+ " JOIN rubro r ON s.id_rubro = r.id_rubro "
						+ " WHERE srd.id_servicio_realizado = " + idServicioRealizado + "; ";



		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();


		            oMapResult.put("idServicioRealizadoDetalles", oResultArray[0]);
		            oMapResult.put("idServicio", oResultArray[1]);
		            oMapResult.put("idServicioRealizado", oResultArray[2]);
		            oMapResult.put("idEmpleado", oResultArray[3]);	
		            oMapResult.put("idRubro", oResultArray[4]);
		            oMapResult.put("descripcionServicio", oResultArray[5]);
		            oMapResult.put("precio", oResultArray[6]);
		            oMapResult.put("porcentajeIva", oResultArray[7]);
		            oMapResult.put("descripcionRubro", oResultArray[8]);
		            oMapResult.put("nombres", oResultArray[9]);
		            oMapResult.put("apellidos", oResultArray[10]);

		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	public void addWhere(String query, Boolean first){

		if(first){
			this.first = false;
			this.query += " WHERE ";
		}else{
			this.query += " AND ";			
		}
	}
	
	public  List<Map<String, Object>> listarInventario() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select i.id_inventario, i.id_empleado, i.fecha, p.nombres, p.apellidos "
						+ " from inventario i "
						+ " JOIN empleado e ON i.id_empleado = e.id_empleado "
						+ " JOIN persona p ON e.id_persona = p.id_persona ";
	
	
		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();
	
		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idInventario", oResultArray[0]);
		            oMapResult.put("idEmpleado", oResultArray[1]);	
		            oMapResult.put("fecha", oResultArray[2]);
		            oMapResult.put("nombres", oResultArray[3]);
		            oMapResult.put("apellidos", oResultArray[4]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarInventarioDetalles() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select id.id_inventario_detalles, id.id_inventario, id.stock_minimo, "
						+ " id.stock_actual, id.realizar_compra, id.id_producto, p.descripcion as producto "
						+ " from inventario_detalles id "
						+ " join producto p ON id.id_producto = p.id_producto ";

		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idInventarioDetalles", oResultArray[0]);		            
		            oMapResult.put("idInventario", oResultArray[1]);
		            oMapResult.put("stockMinimo", oResultArray[2]);	
		            oMapResult.put("stockActual", oResultArray[3]);
		            oMapResult.put("realizarCompra", oResultArray[4]);
		            oMapResult.put("idProducto", oResultArray[5]);
		            oMapResult.put("producto", oResultArray[6]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarFacturas() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select fc.fecha fecha, fc.monto_total, fc.iva5, fc.iva10, fc.exenta, pr.nombres  || '  ' || pr.apellidos as cliente, fc.id_factura "
						+ " from factura fc "
						+ " left join servicio_realizado sr on sr.id_servicio_realizado = fc.id_servicio_realizado "
						+ " left join cliente cl on cl.id_cliente = sr.id_cliente "
						+ " left join persona pr on pr.id_persona = cl.id_persona ";

		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();

		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("fecha", oResultArray[0]);		            
		            oMapResult.put("montoTotal", oResultArray[1]);
		            oMapResult.put("iva5", oResultArray[2]);	
		            oMapResult.put("iva10", oResultArray[3]);
		            oMapResult.put("exenta", oResultArray[4]);
		            oMapResult.put("cliente", oResultArray[5]);
		            oMapResult.put("idFactura", oResultArray[6]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarBajaStock() throws ApplicationException{
			try{				
					List<Object[]> postComments = null;		
					
					String query = " select bs.id_baja_stock, bs.id_empleado, bs.fecha, bs.motivo_baja, bs.id_producto, pr.descripcion, p.nombres, "
							+ " p.apellidos, bs.cantidad_baja "
							+ " from baja_stock bs "
							+ " JOIN producto pr ON bs.id_producto = pr.id_producto "
							+ " JOIN empleado e ON bs.id_empleado = e.id_empleado "
							+ " JOIN persona p ON e.id_persona = p.id_persona ";
		
		
			        postComments = em.createNativeQuery(query.toString()).
			                getResultList();
		
			        List<Map<String, Object>> resultlist = new ArrayList<>();
			        for (Object[] oResultArray : postComments) {
			            Map<String, Object> oMapResult = new HashMap<>();
			            oMapResult.put("idBajaStock", oResultArray[0]);
			            oMapResult.put("idEmpleado", oResultArray[1]);
			            oMapResult.put("fecha", oResultArray[2]);
			            oMapResult.put("motivoBaja", oResultArray[3]);
			            oMapResult.put("idProducto", oResultArray[4]);
			            oMapResult.put("descripcion", oResultArray[5]);
			            oMapResult.put("nombres", oResultArray[6]);
			            oMapResult.put("apellidos", oResultArray[7]);
			            oMapResult.put("cantidadBaja", oResultArray[8]);
			         resultlist.add(oMapResult);
			        }
			        return resultlist;
			}catch (Exception e) {
				throw new ApplicationException(e);
			}		
		}
	
	public  List<Map<String, Object>> listarOrdenCompra() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select oc.id_ordenes_compra, oc.fecha, oc.id_estado, e.descripcion, oc.recepcionado, oc.costo_estimado, oc.costo_total,"
						+ "oc.id_proveedor, p.nombre, oc.generado "
						+ " from ordenes_compra oc "
						+ " JOIN estados e ON oc.id_estado = e.id_estado "
						+ " JOIN proveedor p ON oc.id_proveedor = p.id_proveedor ";
	
	
		        postComments = em.createNativeQuery(query.toString()).
		                getResultList();
	
		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idOrdenesCompra", oResultArray[0]);
		            oMapResult.put("fecha", oResultArray[1]);	
		            oMapResult.put("idEstado", oResultArray[2]);
		            oMapResult.put("descripcion", oResultArray[3]);
		            oMapResult.put("recepcionado", oResultArray[4]);
		            oMapResult.put("costoEstimado", oResultArray[5]);
		            oMapResult.put("costoTotal", oResultArray[6]);
		            oMapResult.put("idProveedor", oResultArray[7]);
		            oMapResult.put("nombre", oResultArray[8]);
		            oMapResult.put("generado", oResultArray[9]);
		         resultlist.add(oMapResult);
		        }
		        return resultlist;
		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}

	public  List<Map<String, Object>> listarOrdenCompraDetalles() throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select od.id_ordenes_compra_detalles, od.id_ordenes_compra, "
						+ " od.id_producto, p.descripcion, p.costo, od.cantidad "
						+ " from ordenes_compra_detalles od "
						+ " join producto p ON od.id_producto = p.id_producto ";
				postComments = em.createNativeQuery(query.toString()).
		                getResultList();
		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("idOrdenesCompraDetalles", oResultArray[0]);		            
		            oMapResult.put("idOrdenesCompra", oResultArray[1]);
		            oMapResult.put("idProducto", oResultArray[2]);	
		            oMapResult.put("descripcion", oResultArray[3]);
		            oMapResult.put("costo", oResultArray[4]);
		            oMapResult.put("cantidad", oResultArray[5]);
		            resultlist.add(oMapResult);
		        }
		        return resultlist;

		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
	
	public  List<Map<String, Object>> listarUsuario(String contrasenha, String usuario) throws ApplicationException{
		try{				
				List<Object[]> postComments = null;		
				
				String query = " select us.contrasenha, us.usuario, pr.nombres  || '  ' || pr.apellidos as cliente, pr.documento, em.id_empleado "
						+ " from usuario us "
						+ " join empleado em on em.usuario = us.usuario "
						+ " join persona pr on pr.id_persona = em.id_persona " 
						+ " where us.contrasenha = '" + contrasenha + "' and us.usuario = '" + usuario + "'" ; 
						
				
				postComments = em.createNativeQuery(query.toString()).
		                getResultList();
		        List<Map<String, Object>> resultlist = new ArrayList<>();
		        for (Object[] oResultArray : postComments) {
		            Map<String, Object> oMapResult = new HashMap<>();
		            oMapResult.put("contrasenha", oResultArray[0]);		            
		            oMapResult.put("usuario", oResultArray[1]);
		            oMapResult.put("nombre", oResultArray[2]);	
		            oMapResult.put("documento", oResultArray[3]);
		            oMapResult.put("idEmpleado", oResultArray[4]);
		            resultlist.add(oMapResult);
		        }
		        return resultlist;

		}catch (Exception e) {
			throw new ApplicationException(e);
		}		
	}
}