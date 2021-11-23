package com.bellaUtopia.servicio;

import javax.inject.Inject;

import com.bellaUtopia.dao.HorarioDao;
import com.bellaUtopia.entidad.Horario;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

public class HorarioService extends ServiceBase<Horario, DaoBase<Horario>> {

	@Inject
	private HorarioDao dao;

	@Override
	public DaoBase<Horario> getDao() {
		return dao;
	}
}