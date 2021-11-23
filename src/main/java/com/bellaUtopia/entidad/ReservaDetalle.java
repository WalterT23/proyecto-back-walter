package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reserva_detalle", schema = "public")
public class ReservaDetalle extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "reserva_detalle_id_seq", allocationSize=1, initialValue=1, sequenceName = "reserva_detalle_id_seq")
	@GeneratedValue(generator = "reserva_detalle_id_seq", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_reserva")
	private Integer idReserva;

	@Column(name = "id_horario")
	private Integer idHorario;

	@Column(name = "fecha")
    @Temporal(TemporalType.DATE)
	private Date fecha;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	public Integer getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}