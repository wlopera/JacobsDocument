package com.jacobs.visual.dto;

import java.io.Serializable;

public class Link implements Serializable {
	
	private static final long serialVersionUID = -344426349439310470L;
	
	private String from;
	private String to;
	private String text;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Link [from=" + from + ", to=" + to + ", text=" + text + "]";
	}
	
}
