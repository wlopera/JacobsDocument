package com.jacobs.visual.dto;

import java.io.Serializable;

public class Node implements Serializable {
	
	private static final long serialVersionUID = -6494405626836424204L;
	
	private String key;
	private String text;
	private String color;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Node [key=" + key + ", text=" + text + ", color=" + color + "]";
	}

}
