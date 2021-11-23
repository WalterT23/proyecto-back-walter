package com.bellaUtopia.param;

import java.util.Date;
import java.util.List;

import com.bellaUtopia.entidad.Cliente;
import com.bellaUtopia.entidad.Horario;
import com.bellaUtopia.entidad.Mesa;
import com.bellaUtopia.entidad.Restaurante;

public class ReservaParam {
	private Restaurante restaurante;
	private Mesa mesa;
	private Cliente cliente;
	private List<Horario> listaHorarios;
	private Date fechaReserva;
	private Integer cantidad;
	
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Horario> getListaHorarios() {
		return listaHorarios;
	}
	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	

	

}
