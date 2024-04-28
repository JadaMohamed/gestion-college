package application.repositories;

import java.sql.ResultSet;

import application.database.SqlConnection;

public class LoginRepository {
    public static int validateLogin(String email, String password) {
        int count = 0;
        try {
            java.sql.Connection connectDB = SqlConnection.getConnection();
            String verifyLogin = "SELECT count(1) FROM administrateur WHERE email = ? AND motDePass = ? ";
            java.sql.PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                count = queryResult.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
