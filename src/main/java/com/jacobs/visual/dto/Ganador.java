package com.jacobs.visual.dto;

import java.io.Serializable;

public class Ganador implements Serializable {

	private static final long serialVersionUID = 2377436682310689321L;
	
	private Long id;
	private int temporada;
	private String campeon;
	private String resultado;
	private String subCampeon;
	private String sede;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public String getCampeon() {
		return campeon;
	}
	public void setCampeon(String campeon) {
		this.campeon = campeon;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getSubCampeon() {
		return subCampeon;
	}
	public void setSubCampeon(String subCampeon) {
		this.subCampeon = subCampeon;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	@Override
	public String toString() {
		return "Ganador [id=" + id + ", temporada=" + temporada + ", campeon=" + campeon + ", resultado=" + resultado
				+ ", subCampeon=" + subCampeon + ", sede=" + sede + "]";
	}

}
