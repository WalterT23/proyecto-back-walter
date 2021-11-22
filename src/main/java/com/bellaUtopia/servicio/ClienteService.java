package com.bellaUtopia.servicio;

import com.bellaUtopia.dao.ClienteDao;
import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.util.DaoBase;
import com.bellaUtopia.util.ServiceBase;

import javax.inject.Inject;

public class ClienteService extends ServiceBase<Cliente, DaoBase<Cliente>> {

	@Inject
	private ClienteDao dao;

	@Override
	public DaoBase<Cliente> getDao() {
		return dao;
	}
}