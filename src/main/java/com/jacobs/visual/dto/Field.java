package com.jacobs.visual.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Field implements Serializable {

	private static final long serialVersionUID = 5826035957609602639L;
	
	@JsonProperty("PONumber")
	private String PONumber;
	
	@JsonProperty("DocumentDate_date")
	private String DocumentDate_date;
	
	@JsonProperty("Batchname")
	private String Batchname;
	
	@JsonProperty("PartNumber")
	private String PartNumber;
	
	@JsonProperty("AssemblyNumber")
	private String AssemblyNumber;
	
	@JsonProperty("DocumentType")
	private String DocumentType;
	
	@JsonProperty("Filename")
	private String Filename;
	
	@JsonProperty("DocumentNumber")
	private String DocumentNumber;
	
	@JsonProperty("WorkAuthorizationNumber")
	private String WorkAuthorizationNumber;

	public String getPONumber() {
		return PONumber;
	}

	public void setPONumber(String PONumber) {
		this.PONumber = PONumber;
	}

	public String getDocumentDate_date() {
		return DocumentDate_date;
	}

	public void setDocumentDate_date(String DocumentDate_date) {
		this.DocumentDate_date = DocumentDate_date;
	}

	public String getBatchname() {
		return Batchname;
	}

	public void setBatchname(String Batchname) {
		this.Batchname = Batchname;
	}

	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String PartNumber) {
		this.PartNumber = PartNumber;
	}

	public String getAssemblyNumber() {
		return AssemblyNumber;
	}

	public void setAssemblyNumber(String AssemblyNumber) {
		this.AssemblyNumber = AssemblyNumber;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String DocumentType) {
		this.DocumentType = DocumentType;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String Filename) {
		this.Filename = Filename;
	}

	public String getDocumentNumber() {
		return DocumentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		DocumentNumber = documentNumber;
	}

	public String getWorkAuthorizationNumber() {
		return WorkAuthorizationNumber;
	}

	public void setWorkAuthorizationNumber(String WorkAuthorizationNumber) {
		this.WorkAuthorizationNumber = WorkAuthorizationNumber;
	}

	@Override
	public String toString() {
		return "Field [PONumber=" + PONumber + ", DocumentDate_date=" + DocumentDate_date + ", Batchname=" + Batchname
				+ ", PartNumber=" + PartNumber + ", AssemblyNumber=" + AssemblyNumber + ", DocumentType=" + DocumentType
				+ ", Filename=" + Filename + ", DocumentNumber=" + DocumentNumber + ", WorkAuthorizationNumber="
				+ WorkAuthorizationNumber + "]";
	}

}