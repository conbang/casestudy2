package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.view.manager.Manager;
import sample.view.register.Validate;
import sample.model.Login;
import sample.view.ErrorAlert;
import sample.model.Register;
import java.io.IOException;

public class Controller {
    ErrorAlert error;
    public AnchorPane login;
    public TextField login_user;
    public PasswordField login_psw;
    public TextField new_account;
    public PasswordField new_psw;
    public TextField new_email;
    public TextField new_phone;
    static Stage registerStage = null;
    Manager manager;

    public void openRegister() {
        try {
            if(registerStage == null){
                Parent layout = FXMLLoader.load(getClass().getResource("view/register/register.fxml"));
                registerStage = new Stage();
                registerStage.setScene(new Scene(layout, 313, 394));
                registerStage.show();
            }
        } catch (IOException e) {
            error = new ErrorAlert("unknown error!");
        }
    }

    public void register() {
        String account = new_account.getText();
        String password = new_psw.getText();
        String email = new_email.getText();
        String phone = new_phone.getText();
        Validate validate = new Validate();
        if (!validate.isValidName(account)) {
            error = new ErrorAlert("invalid account!");
            return;
        } else if (!validate.isValidEmail(email)) {
            error = new ErrorAlert("invalid email!");
            return;
        } else if (!validate.isValidPhone(phone)) {
            error = new ErrorAlert("invalid phone!");
            return;
        }
        Register register = new Register(account, password, email, phone);
        if(register.isSuccessRegister()){
            registerStage.close();
        }

    }

    public void Login() {
        Validate validate = new Validate();
        String userName = login_user.getText();
        String password = login_psw.getText();
        Login loginAccount = null;
        if (!validate.isValidName(userName)) {
            error = new ErrorAlert("username is illegal!");
            return;
        }
        if (!validate.isValidName(password)) {
            error = new ErrorAlert("password is illegal!");
            return;
        }
        loginAccount = new Login(userName, password);
        if(loginAccount.isLoginSucess()){
            System.out.println("login success");
            try{
                manager = new Manager(userName);
                manager.createManagerStage();
            }catch (Exception e){
                e.getMessage();
            }

        };
    }
}
