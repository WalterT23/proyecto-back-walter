package com.bellaUtopia.util;

import java.util.List;

public class ListaPaginada<T> {
	private List<T> lista;
	private Integer total;
	
	
	public ListaPaginada(List<T> lista, Integer total) {
		super();
		this.lista = lista;
		this.total = total;
	}
	
	
	public List<T> getLista() {
		return lista;
	}
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
}
