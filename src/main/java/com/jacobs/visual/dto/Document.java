package com.jacobs.visual.dto;

import java.io.Serializable;

public class Document implements Serializable {

	private static final long serialVersionUID = -2551189038860485280L;

	private String collection;
	private Field fields;

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public Field getFields() {
		return fields;
	}

	public void setFields(Field fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Document [collection=" + collection + ", fields=" + fields + "]";
	}

}
