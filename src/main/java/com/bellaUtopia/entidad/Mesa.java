package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mesas", schema = "public")
public class Mesa extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "MESA_ID_MESA_SEQ", allocationSize=1, initialValue=1, sequenceName = "mesa_id_mesa_seq_1")
	@GeneratedValue(generator = "MESA_ID_MESA_SEQ", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nro_piso")
	private String nroPiso;

	@Column(name = "nombre_mesa")
	private String nombre;

	@Column(name = "id_restaurante")
	private String idRestaurante;

	@Column(name = "posicion")
	private String posicion;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getNroPiso(){
		return nroPiso;
	}
	public void setNroPiso(String nroPiso){
		this.nroPiso = nroPiso;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getIdRestaurante(){
		return idRestaurante;
	}
	public void setIdRestaurante(String idRestaurante){
		this.idRestaurante = idRestaurante;
	}
	public String getPosicion(){
		return posicion;
	}
	public void setPosicion(String posicion){
		this.posicion = posicion;
	}
}