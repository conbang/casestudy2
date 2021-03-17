package sample.model;

import com.mysql.cj.xdevapi.Result;

public interface Dbconnectable {
    String CONNECTION = "jdbc:mysql://localhost:3306/test";
    String ADMIN = "root";
    String PASSWORD = "";
    String FINDUSER = "SELECT * FROM account WHERE ";
    String INSERT = "INSERT INTO account (account,password,email,phone,status) VALUES";
    String CHAT = "INSERT INTO message(sendid,receive,msg) VALUES";
    String GETMESSAGE = "SELECT * FROM message WHERE ";
}
