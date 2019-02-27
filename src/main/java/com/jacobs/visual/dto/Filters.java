package com.jacobs.visual.dto;

import java.io.Serializable;
import java.util.List;

public class Filters implements Serializable {

	private static final long serialVersionUID = -2094379281969143127L;
	
	private String field;
	private String operator;
	private String value;
	private List<Filters> clauses;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Filters> getClauses() {
		return clauses;
	}

	public void setClauses(List<Filters> clauses) {
		this.clauses = clauses;
	}

	@Override
	public String toString() {
		return "Filters [field=" + field + ", Operator=" + operator + ", value=" + value + ", clauses=" + clauses + "]";
	}
}
