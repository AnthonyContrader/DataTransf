package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.contrader.model.Conversion;
import it.contrader.utils.ConnectionSingleton;

public class ConversionDAO {
	private final String QUERY_CREATE = "INSERT INTO conversion (idUser, source, sourceType, outputType, changes ) VALUES (?,?,?,?,?)";

	public boolean insert (Conversion ConversionToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, ConversionToInsert.getIdUser());
			preparedStatement.setString(2, ConversionToInsert.getSource());
			preparedStatement.setString(3, ConversionToInsert.getSourceType());
			preparedStatement.setString(4, ConversionToInsert.getOutputType());
			preparedStatement.setInt(5, ConversionToInsert.getChanges());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}

