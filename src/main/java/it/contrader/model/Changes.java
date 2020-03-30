package it.contrader.model;

public class Changes {
	private int id;
	private String changesName;
	private String changes;
	private int idUser;
	private String removedTag;
	
	public Changes() {
		// TODO Auto-generated constructor stub
	}
	
	public Changes(String changesName, String changes, int idUser) {
		this.idUser = idUser;
		this.changesName = changesName;
		this.changes = changes;
	}
	
	public Changes(int id, String changesName, String changes, int idUser) {
		this.id = id;
		this.idUser = idUser;
		this.changesName = changesName;
		this.changes = changes;
	}
	public Changes(int id, String changesName, String changes, int idUser, String removedTag) {
		this.id = id;
		this.idUser = idUser;
		this.changesName = changesName;
		this.changes = changes;
		this.removedTag = removedTag;
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
	public String getRemovedTag() {
		return removedTag;
	}
	public void setRemovedTag(String removedTag) {
		this.removedTag = removedTag;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Changes c = (Changes) obj;
		
		if(id != c.id) {
			return false;
		}
		
		if(changesName != c.changesName) {
			return false;
		}
		
		if(changes != c.changes) {
			return false;
		}
		
		if(idUser != c.idUser) {
			return false;
		}
		
		if(removedTag != c.removedTag) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + "\t" + changesName + "\t" + changes + "\t" + idUser + "\t" + removedTag;
	}
}
