package com.bellaUtopia.servicio;

import java.util.Date;

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
		Cliente cliente = reservaParam.getCliente();
		if(cliente.getId() == null) {
			clienteService.insertar(reservaParam.getCliente());
		}else {
			cliente = clienteService.obtenerEntidad(reservaParam.getCliente().getId(), Cliente.class);
			if(cliente.getId() == null) {
				clienteService.insertar(reservaParam.getCliente());
			}
		}
		
		Reserva reserva = new Reserva();
		
		reserva.setCantidad(reservaParam.getCantidad());
		reserva.setFecha(new Date());
		reserva.setIdCliente(cliente.getId());
		reserva.setIdMesa(reservaParam.getMesa().getId());
		reserva.setIdRestaurante(reservaParam.getRestaurante().getId());
		reserva.setRangoHora(1);
		
		dao.insert(reserva);
		
		for(Horario data : reservaParam.getListaHorarios()){
			ReservaDetalle detalle = new ReservaDetalle();
			detalle.setFecha(new Date());
			detalle.setIdHorario(data.getId());
			detalle.setIdReserva(reserva.getId());
			reservaDetalleService.insertar(detalle);
		}
		
		return reservaParam;
		
	}
}