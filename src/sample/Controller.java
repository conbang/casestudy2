package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.view.register.Validate;
import sample.model.Login;
import sample.view.ErrorAlert;
import sample.model.Register;
import java.io.IOException;

public class Controller {
    ErrorAlert error;
    public TextField login_user;
    public PasswordField login_psw;
    public TextField new_account;
    public PasswordField new_psw;
    public TextField new_email;
    public TextField new_phone;
    static Stage registerStage = null;

    public void openRegister() {
        try {
            Parent layout = FXMLLoader.load(getClass().getResource("view/register/register.fxml"));
            registerStage = new Stage();
            registerStage.setScene(new Scene(layout, 313, 394));
            registerStage.show();
        } catch (IOException e) {
            error = new ErrorAlert("unknown error!");
        }
    }

    public void register() {
        System.out.println(registerStage==null);
        String acocunt = new_account.getText();
        String password = new_psw.getText();
        String email = new_email.getText();
        String phone = new_phone.getText();
        Register register = new Register(acocunt, password, email, phone);
        if(register.isSuccessRegister()){
            registerStage.close();
        }

    }

    public void Login() {
        Validate validate = new Validate();
        String userName = login_user.getText();
        String password = login_psw.getText();
        Login login = null;
        if (!validate.isValidName(userName)) {
            error = new ErrorAlert("username is illegal!");
            return;
        }
        if (!validate.isValidName(password)) {
            error = new ErrorAlert("password is illegal!");
            return;
        }
        login = new Login(userName, password);
        login.isLoginSucess();
    }
}
