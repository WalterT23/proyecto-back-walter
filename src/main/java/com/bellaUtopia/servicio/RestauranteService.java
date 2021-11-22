package com.bellaUtopia.servicio;

import javax.inject.Inject;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;
import com.bellaUtopia.dao.RestauranteDao;
import com.bellaUtopia.entidad.Restaurante;

public class RestauranteService extends ServiceBase<Restaurante, DaoBase<Restaurante>> {

	@Inject
	private RestauranteDao dao;

	@Override
	public DaoBase<Restaurante> getDao() {
		return dao;
	}
}