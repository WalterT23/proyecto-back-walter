package com.bellaUtopia.servicio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bellaUtopia.dao.ReservaDao;
import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.entidad.Horario;
import com.bellaUtopia.entidad.Reserva;
import com.bellaUtopia.entidad.ReservaDetalle;
import com.bellaUtopia.param.ReservaParam;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

@Stateless
public class ReservaService extends ServiceBase<Reserva, DaoBase<Reserva>> {

	@Inject
	private ReservaDao dao;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private ReservaDetalleService reservaDetalleService;

	@Override
	public DaoBase<Reserva> getDao() {
		return dao;
	}
	
	public ReservaParam realizarReserva(ReservaParam reservaParam) {
		
		
		Cliente cliente = new Cliente();
		cliente.setCedula(reservaParam.getCliente().getCedula());
		List<Cliente> listClientes = clienteService.listarFiltrado(cliente);
		
		if(listClientes.size() == 0) {
			clienteService.insertar(reservaParam.getCliente());
		}else {
			cliente = listClientes.get(0);
		}
		
		Reserva reserva = new Reserva();
		
		reserva.setCantidad(reservaParam.getCantidad());
		reserva.setFecha(reservaParam.getFechaReserva());
		reserva.setIdCliente(cliente.getId());
		reserva.setIdMesa(reservaParam.getMesa().getId());
		reserva.setIdRestaurante(reservaParam.getRestaurante().getId());
		reserva.setRangoHora(1);
		
		dao.insert(reserva);
		
		for(Horario data : reservaParam.getListaHorarios()){
			ReservaDetalle detalle = new ReservaDetalle();
			detalle.setFecha(reservaParam.getFechaReserva());
			detalle.setIdHorario(data.getId());
			detalle.setIdReserva(reserva.getId());
			reservaDetalleService.insertar(detalle);
		}
		
		return reservaParam;
		
	}
	

	public void eliminarReserva(Integer id) {
		
		Reserva reserva = dao.obtenerEntidad(id, Reserva.class);

		ReservaDetalle detalle = new ReservaDetalle();
		detalle.setIdReserva(id);
		List<ReservaDetalle> listReservaDetalle = reservaDetalleService.listarFiltrado(detalle);
		
		dao.insert(reserva);
		
		for(ReservaDetalle data : listReservaDetalle){
			reservaDetalleService.eliminar( data.getId(), new ReservaDetalle());
		}
		
		dao.eliminar(id, new Reserva());
		
	}
}