package com.jacobs.visual.dto;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 8068697100453300771L;
	
	private Integer status;
	private String message;
	private Integer responseTime;
	private Integer queryTime;
	private Integer found;
	private Integer startAt;
	private List<Document> documents;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}
	public Integer getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Integer queryTime) {
		this.queryTime = queryTime;
	}
	public Integer getFound() {
		return found;
	}
	public void setFound(Integer found) {
		this.found = found;
	}
	public Integer getStartAt() {
		return startAt;
	}
	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", responseTime=" + responseTime + ", queryTime="
				+ queryTime + ", found=" + found + ", startAt=" + startAt + ", documents=" + documents + "]";
	}

}
