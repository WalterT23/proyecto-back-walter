package com.bellaUtopia.servicio;

import com.bellaUtopia.dao.MesaDao;
import com.bellaUtopia.entidad.Mesa;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

import javax.inject.Inject;

public class MesaService extends ServiceBase<Mesa, DaoBase<Mesa>> {

	@Inject
	private MesaDao dao;

	@Override
	public DaoBase<Mesa> getDao() {
		return dao;
	}
}