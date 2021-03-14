package sample.view.register;

import java.util.regex.Pattern;

public class Validate {
    public boolean isValidName(String username) {
        String regex = "^[A-Za-z]\\w{4,29}$";
        return check(regex, username);
    }

    public boolean isValidEmail(String email) {
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+" +
                "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        return check(regex, email);
    }

    public boolean isValidPhone(String phone) {
        String regex = "^[0-9]{9}";
        return check(regex, phone);
    }

    public boolean check(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }
}
