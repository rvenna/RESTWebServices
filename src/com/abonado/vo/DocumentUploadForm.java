package com.abonado.vo;

import javax.ws.rs.FormParam;

public class DocumentUploadForm {
	
	private String fileName;
	private byte[] fileContent;
	
	public String getFileName() {
		return fileName;
	}
	
	@FormParam("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	
	@FormParam("fileContent")
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	
	

}
