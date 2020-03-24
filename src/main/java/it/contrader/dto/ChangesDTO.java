package it.contrader.dto;

public class ChangesDTO {
	
	private int id;
	private String changesName;
	private String changes;
	private int idUser;
	
	public ChangesDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ChangesDTO(String changesName, String changes, int idUser) {
		this.idUser = idUser;
		this.changesName = changesName;
		this.changes = changes;
	}
	
	public ChangesDTO(int id, String changesName, String changes, int idUser) {
		this.id = id;
		this.idUser = idUser;
		this.changesName = changesName;
		this.changes = changes;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChangesName() {
		return changesName;
	}
	public void setChangesName(String changesName) {
		this.changesName = changesName;
	}
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + "\t" + changesName + "\t" + changes + "\t" + idUser;
	}
	
}
