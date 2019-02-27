package com.jacobs.visual.dto;

import java.io.Serializable;

public class Parameters implements Serializable{
	
	private static final long serialVersionUID = -7261877125380184689L;
	
	private String df;

	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	@Override
	public String toString() {
		return "Parameters [df=" + df + "]";
	}
}
