package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ConversionDTO;
import it.contrader.utils.ConnectionSingleton;

public class ConversionLogDAO  {

	private final String QUERY_CONVERSION_LOG = "SELECT * FROM conversion WHERE idUser = ?";
	private final String QUERY_ALL = "SELECT * FROM conversion";

	public ConversionLogDAO() {
	}
	
	
	public List<ConversionDTO> getAll() {
		List<ConversionDTO> conversionList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			ConversionDTO conversion;
			while (resultSet.next()) {
				int idConversion = resultSet.getInt("idConversion");
				int idUser = resultSet.getInt("idUser");
				int changes = resultSet.getInt("changes");
				String source = resultSet.getString("source");
				String sourceType = resultSet.getString("sourceType");
				String outputType = resultSet.getString("outputType");
				conversion = new ConversionDTO(idConversion, idUser, source, sourceType, outputType, changes);
				conversionList.add(conversion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversionList;
	}
	
	
	public List<ConversionDTO> getAllLogUser(int idUser) {
		
		
		List<ConversionDTO> conversionList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CONVERSION_LOG);
			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();
			ConversionDTO conversion;
			while (resultSet.next()) {
				int idConversion = resultSet.getInt("idConversion");
				String source = resultSet.getString("source");
				String sourceType = resultSet.getString("sourceType");
				String outputType = resultSet.getString("outputType");
				conversion = new ConversionDTO(idConversion, idUser, source, sourceType);
				conversionList.add(conversion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conversionList;
	}
	
}
