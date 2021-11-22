package com.bellaUtopia.servicio;

import com.bellaUtopia.dao.ReservaDao;
import com.bellaUtopia.entidad.Reserva;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

import javax.inject.Inject;

public class ReservaService extends ServiceBase<Reserva, DaoBase<Reserva>> {

	@Inject
	private ReservaDao dao;

	@Override
	public DaoBase<Reserva> getDao() {
		return dao;
	}
}