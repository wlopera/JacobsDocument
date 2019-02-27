package com.jacobs.visual.dto;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {

	private static final long serialVersionUID = -2979975918521263529L;

	private List<String> collections;
	private List<String> fields;
	private List<Filters> filters;
	private Parameters parameters;
	private String queryField;
	private String related;
	private Integer rows;
	private Integer start;

	public List<String> getCollections() {
		return collections;
	}

	public void setCollections(List<String> collections) {
		this.collections = collections;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<Filters> getFilters() {
		return filters;
	}

	public void setFilters(List<Filters> filters) {
		this.filters = filters;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Request [collections=" + collections + ", fields=" + fields + ", filters=" + filters + ", parameters="
				+ parameters + ", queryField=" + queryField + ", related=" + related + ", rows=" + rows + ", start="
				+ start + "]";
	}
}
