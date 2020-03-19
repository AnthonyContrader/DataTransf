package it.contrader.dao;

import java.sql.*;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Conversion;

public class ConversionDAO {
	private final String QUERY_CREATE = "INSERT INTO conversion (idUser, source, sourceType, outputType, changes) VALUES (?,?,?,?,?)";

public boolean insert(Conversion conversionToInsert) {
	Connection connection = ConnectionSingleton.getInstance();
	try {	
		PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
		preparedStatement.setInt(1, conversionToInsert.getIdUser());
		preparedStatement.setString(2, conversionToInsert.getSource());
		preparedStatement.setString(3, conversionToInsert.getSourceType());
		preparedStatement.setString(4, conversionToInsert.getOutputType());
		preparedStatement.setBoolean(5, conversionToInsert.isChanges());
		preparedStatement.execute();
		return true;
	} catch (SQLException e) {
		return false;
	}
}}