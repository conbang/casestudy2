package sample.model;
import sample.view.ErrorAlert;
import sample.view.register.Validate;

import java.sql.*;

public class Register implements Dbconnectable {
    private String username;
    private String password;
    private String email;
    private String phone;
    Validate validate;
    ErrorAlert errorAlert;

    public Register(String username, String password, String email, String phone) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public boolean isSuccessRegister() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(CONNECTION, ADMIN, PASSWORD);
            statement = connection.createStatement();
            System.out.println("connect success");
            validate = new Validate();
            if(!validate.isValidName(username)){
                errorAlert = new ErrorAlert("invalid account!");
                return false;
            }else if(!validate.isValidEmail(password)){
                errorAlert = new ErrorAlert("invalid email!");
                return false;
            }else if(!validate.isValidPhone(phone)){
                errorAlert = new ErrorAlert("invalid phone!");
                return false;
            }
            String query = FINDUSER + "account='" + this.username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                errorAlert = new ErrorAlert("this username has exist!");
                return false;
            } else {
                query = INSERT + "('" + this.username + "','"
                        + this.password + "','"
                        + this.email + "','"
                        + this.phone
                        + "')";
                try{
                    statement.execute(query);
                    return true;
                }catch (Exception e){
                    e.getMessage();
                    return false;
                }
            }
        } catch (Exception e) {
            errorAlert = new ErrorAlert("Can't connect to server!");
            return false;
        } finally {
            try {
                if (connection != null || statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("can't close server");
            }
        }
    }

}
