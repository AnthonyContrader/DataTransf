package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Changes;
import it.contrader.utils.ConnectionSingleton;

public class ChangesDAO implements DAO<Changes> {

	private final String QUERY_ALL = "SELECT * FROM changes";
	private final String QUERY_CREATE = "INSERT INTO changes (changesName, changes, idUser) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM changes WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE changes SET changesName=?, changes=?, idUser=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM changes WHERE id=?";

	@Override
	public List<Changes> getAll() {
		// TODO Auto-generated method stub
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

	@Override
	public Changes read(int id) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			return new Changes(resultSet.getInt("id"), resultSet.getString("changesName"), 
					resultSet.getString("changes"), resultSet.getInt("idUser"));
			
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public boolean insert(Changes dto) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, dto.getChangesName());
			preparedStatement.setString(2, dto.getChanges());
			preparedStatement.setInt(3, dto.getIdUser());
			
			preparedStatement.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public boolean update(Changes dto) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setString(1, dto.getChangesName());
			preparedStatement.setString(2, dto.getChanges());
			preparedStatement.setInt(3, dto.getIdUser());
			preparedStatement.setInt(4, dto.getIdUser());
			
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

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
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
