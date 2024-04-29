package application.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class dbClient {

	public static ResultSet executeCommand(boolean isSelect, String commandString, List<Object> parameters) {
		PreparedStatement statement;
		Connection connection = SqlConnection.getConnection();
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(commandString);
			if (parameters != null) {
				int i = 1;
				for (Object parameter : parameters) {
					statement.setObject(i, parameter);
					i++;
				}
			}
			if (isSelect == true) {
				resultSet = statement.executeQuery();
				return resultSet;
			} else {
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

}