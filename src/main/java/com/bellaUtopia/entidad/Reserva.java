package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reserva", schema = "public")
public class Reserva extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "RESERVA_ID_RESERVA_SEQ", allocationSize=1, initialValue=1, sequenceName = "reserva_id_reserva_seq_1")
	@GeneratedValue(generator = "RESERVA_ID_RESERVA_SEQ", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_restaurante")
	private Integer idRestaurante;

	@Column(name = "id_mesa")
	private Integer idMesa;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "rango_hora")
	private Integer rangoHora;

	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(name = "cantidad")
	private Integer cantidad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Integer getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getRangoHora() {
		return rangoHora;
	}

	public void setRangoHora(Integer rangoHora) {
		this.rangoHora = rangoHora;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}