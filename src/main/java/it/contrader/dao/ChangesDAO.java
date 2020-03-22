package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Changes;

public class ChangesDAO {

	private final String QUERY_ALL = "SELECT * FROM changes";
	private final String QUERY_CREATE = "INSERT INTO changes (changesName, changes, idUser) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM changes WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE changes SET changesName=?, changes=?, idUser=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM changes WHERE id=?";
	
	public ChangesDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Changes> getAll(){
		List<Changes> changesList = new ArrayList<Changes>();
		
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String changesName = resultSet.getString("changesName");
				String changes = resultSet.getString("changes");
				int idUser = resultSet.getInt("idUser");
				changesList.add(new Changes(id, changesName, changes, idUser));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return changesList;
	}
	
	public boolean insert(Changes changesToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, changesToInsert.getChangesName());
			preparedStatement.setString(2, changesToInsert.getChanges());
			preparedStatement.setInt(3, changesToInsert.getIdUser());
			
			preparedStatement.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public Changes read(int changesId) {
		
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, changesId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			return new Changes(resultSet.getInt("id"), resultSet.getString("changesName"), 
					resultSet.getString("changes"), resultSet.getInt("idUser"));
			
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public boolean update(Changes changesToUpdate) {
		
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setString(1, changesToUpdate.getChangesName());
			preparedStatement.setString(2, changesToUpdate.getChanges());
			preparedStatement.setInt(3, changesToUpdate.getIdUser());
			preparedStatement.setInt(4, changesToUpdate.getIdUser());
			
			if(preparedStatement.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public boolean delete(int id) {
		
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			
			if(preparedStatement.executeUpdate() != 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
}
