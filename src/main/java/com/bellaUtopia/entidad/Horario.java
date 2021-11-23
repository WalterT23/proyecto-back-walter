package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "horario", schema = "public")
public class Horario extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "horario_id_seq", allocationSize=1, initialValue=1, sequenceName = "horario_id_seq")
	@GeneratedValue(generator = "horario_id_seq", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "inicio_horario")
	private Integer inicioHorario;
	
	@Column(name = "fin_horario")
	private Integer finHorario;
	
	@Column(name = "orden")
	private Integer orden;

	@Column(name = "turno")
	private String turno;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getInicioHorario() {
		return inicioHorario;
	}
	public void setInicioHorario(Integer inicioHorario) {
		this.inicioHorario = inicioHorario;
	}
	public Integer getFinHorario() {
		return finHorario;
	}
	public void setFinHorario(Integer finHorario) {
		this.finHorario = finHorario;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
}