package it.contrader.model;

public class Conversion {
	private int idConversion;
	
	private int idUser;
	
	private String source;
	
	private String sourceType;
	
	private String outputType;
	
	private int changes;
	

	public Conversion() {
	}
	
	public Conversion(int idConversion, int idUser, String source, String sourceType, 
			String outputType, int changes){
	this.idConversion = idConversion;
	this.idUser = idUser;
	this.source = source;
	this.sourceType = sourceType;
	this.outputType = outputType;
	this.changes = changes;
	}
	
	public Conversion(int idUser, String source, String sourceType, String outputType, int changes){
	this.idUser = idUser;
	this.source = source;
	this.sourceType = sourceType;
	this.outputType = outputType;
	this.changes = changes;
	}
	
	public Conversion(int idConversion, int idUser, String source, String sourceType, String outputType){
		
		this.idConversion = idConversion;
		this.idUser = idUser;
		this.source = source;
		this.sourceType = sourceType;
		this.outputType = outputType;
		}
public Conversion(int idConversion, String source, String sourceType, String outputType){
		
		this.idConversion = idConversion;
		this.source = source;
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
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	
	
	@Override
	public boolean equals(Object obj) {if(this == obj) {
		return true;
	}
	
	if(obj == null) {
		return false;
	}
	
	if(getClass() != obj.getClass()) {
		return false;
	}
	
	Conversion c = (Conversion) obj;
	
	if(idConversion != c.idConversion) {
		return false;
	}
	
	if(source != c.source) {
		return false;
	}
	
	if(sourceType != c.sourceType) {
		return false;
	}
	
	if(outputType != c.outputType) {
		return false;
	}
	
	if(idUser != c.idUser) {
		return false;
	}
	
	if(changes != c.changes) {
		return false;
	}
	return true;
		
	}
	
	@Override
	public String toString() {	
		return idConversion + "\t" + idUser + "\t" + source + "\t" + sourceType + 
				"\t" + outputType + "\t" + changes + "\t";
	}
	
	
	
}
