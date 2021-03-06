package sample.model;
import sample.view.ErrorAlert;

import java.sql.*;

public class Login implements Dbconnectable {
    private String username;
    private String password;
    ErrorAlert errorAlert;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isLoginSucess() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(CONNECTION, ADMIN, PASSWORD);
            statement = connection.createStatement();
            String query = FINDUSER + " account='" + username + "' and password='" + password + "'";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                return true;
            } else {
                errorAlert = new ErrorAlert("user or password is wrong!");
                return false;
            }
        } catch (Exception e) {
            errorAlert = new ErrorAlert("Check network and try again!");
            return false;
        } finally {
            try {
                if (connection != null && statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("can't close server");
            }
        }
    }
}
