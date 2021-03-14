package sample.view;

public class User {
    private String username;
    private String password;
    private String status;
    private int id;
    private String email;

    public User(String username, String password, int id, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
