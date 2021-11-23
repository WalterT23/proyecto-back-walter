package com.bellaUtopia.servicio;

import javax.inject.Inject;

import com.bellaUtopia.dao.ReservaDetalleDao;
import com.bellaUtopia.entidad.ReservaDetalle;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

public class ReservaDetalleService extends ServiceBase<ReservaDetalle, DaoBase<ReservaDetalle>> {

	@Inject
	private ReservaDetalleDao dao;

	@Override
	public DaoBase<ReservaDetalle> getDao() {
		return dao;
	}
}