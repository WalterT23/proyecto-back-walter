package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mesas", schema = "public")
public class Mesa extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "mesas_id_seq", allocationSize=1, initialValue=1, sequenceName = "mesas_id_seq")
	@GeneratedValue(generator = "mesas_id_seq", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nro_piso")
	private Integer nroPiso;

	@Column(name = "nombre_mesa")
	private String nombre;

	@Column(name = "id_restaurante")
	private Integer idRestaurante;

	@Column(name = "posicion")
	private String posicion;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getNroPiso(){
		return nroPiso;
	}
	public void setNroPiso(Integer nroPiso){
		this.nroPiso = nroPiso;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public Integer getIdRestaurante(){
		return idRestaurante;
	}
	public void setIdRestaurante(Integer idRestaurante){
		this.idRestaurante = idRestaurante;
	}
	public String getPosicion(){
		return posicion;
	}
	public void setPosicion(String posicion){
		this.posicion = posicion;
	}
}