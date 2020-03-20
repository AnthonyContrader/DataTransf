package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Conversion;



public class ConversionLogDao {

	private final String QUERY_CONVERSION_LOG = "SELECT * FROM conversion WHERE idUser = ?";
	private final String QUERY_ALL = "SELECT * FROM conversion";

public ConversionLogDao() {

}
	
	
	public List<Conversion> getAll() {
		List<Conversion> conversionList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Conversion conversion;
			while (resultSet.next()) {
				int idConversion = resultSet.getInt("idConversion");
				int idUser = resultSet.getInt("idUser");
				String source = resultSet.getString("source");
				String sourceType = resultSet.getString("sourceType");
				String outputType = resultSet.getString("outputType");
				conversion = new Conversion(idUser, idConversion, source, sourceType, outputType);
				conversionList.add(conversion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversionList;
	}
	
	
	public List<Conversion> getAllLogUser(int idUser) {
		
		
		List<Conversion> conversionList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CONVERSION_LOG);
			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();
			Conversion conversion;
			while (resultSet.next()) {
				int idConversion = resultSet.getInt("idConversion");
				String source = resultSet.getString("source");
				String sourceType = resultSet.getString("sourceType");
				String outputType = resultSet.getString("outputType");
				conversion = new Conversion(idConversion, source, sourceType, outputType);
				conversionList.add(conversion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversionList;
	}
	
	

}

