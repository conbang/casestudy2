package sample.model;

import com.mysql.cj.xdevapi.Result;

public interface Dbconnectable {
    String CONNECTION = "jdbc:mysql://localhost:3306/test";
    String ADMIN = "root";
    String PASSWORD = "";
    String FINDUSER = "SELECT account FROM account WHERE ";
    String INSERT = "INSERT INTO account (account,password,email,phone) VALUES";
}
