package com.bellaUtopia.entidad;


import com.bellaUtopia.util.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cliente", schema = "public")
public class Cliente extends EntityBase implements Serializable{

	private static final long serialVersionUID = -5758895232658125275L;
	@SequenceGenerator(name = "CLIENTE_ID_CLIENTE_SEQ", allocationSize=1, initialValue=1, sequenceName = "cliente_id_cliente_seq_1")
	@GeneratedValue(generator = "CLIENTE_ID_CLIENTE_SEQ", strategy=GenerationType.SEQUENCE)


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "cedula")
	private String cedula;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getCedula(){
		return cedula;
	}
	public void setCedula(String cedula){
		this.cedula = cedula;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
	}
}