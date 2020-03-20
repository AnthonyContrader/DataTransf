package it.contrader.dto;


public class ConversionDTO {
	
	private int idConversion;
	
	private int idUser;
	
	private String source;
	
	private String sourceType;
	
	private String outputType;
	
	private boolean changes;
	

	public ConversionDTO() {
		
	}
	public ConversionDTO (int idConversion, int idUser, String source, String sourceType, String outputType, boolean changes)
	{
	this.idConversion = idConversion;
	this.idUser = idUser;
	this.source = source;
	this.sourceType = sourceType;
	this.outputType = outputType;
	this.changes = changes;
	}
	
	public ConversionDTO (String source, String sourceType, String outputType ) {
		
		this.source = source;
		this.sourceType = sourceType;
		this.outputType = outputType;
		
	}
	
	public ConversionDTO (int idUser, String source, String sourceType, String outputType, boolean changes)
	{
	this.idUser = idUser;
	this.source = source;
	this.sourceType = sourceType;
	this.outputType = outputType;
	this.changes = changes;
	}
	
	public ConversionDTO (int idConversion, int idUser, String sourceType, String outputType)
	{
	this.idConversion = idConversion;
	this.idUser = idUser;
	this.sourceType = sourceType;
	this.outputType = outputType;
	}
	
	
	public int getIdConversion() {
		return idConversion;
	}
	public void setIdConversion(int idConversion) {
		this.idConversion = idConversion;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public boolean isChanges() {
		return changes;
	}
	public void setChanges(boolean changes) {
		this.changes = changes;
	}
	
	
	
	@Override
	public String toString() {
		
		return idConversion + "\t" + idUser + "\t" + source + "\t" + sourceType + "\t" + outputType + "\t" + changes + "\t";
	}

}
