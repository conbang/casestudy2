package sample.view.chat;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Dbconnectable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChatWindow implements Dbconnectable {
    Stage chatWindow;
    AnchorPane layout;
    ScrollPane screen;
    TextArea chat;
    Button send;
    String account;
    String friend;
    String Msg = "";

    public ChatWindow(String account, String friend) {
        this.account = account;
        this.friend = friend;
        chatWindow = new Stage();
        layout = new AnchorPane();
        screen = new ScrollPane();
        layout.setStyle("-fx-background-color: #017cc1");
        screen.setLayoutX(14);
        screen.setLayoutY(14);
        screen.setPrefHeight(305);
        screen.setPrefWidth(330);
        chat = new TextArea();
        Thread getChat = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = null;
                Statement statement = null;
                try {
                    connection = DriverManager.getConnection(CONNECTION, ADMIN, PASSWORD);
                    statement = connection.createStatement();
                    String query = GETMESSAGE + "(sendid='" + account + "' OR sendid='" + friend +
                            "') AND (receive='" + account + "' OR receive='" + friend + "')";
                    while (chatWindow.isShowing()) {
                        ResultSet resultSet = statement.executeQuery(query);
                        String result = "";
                        while (resultSet.next()) {
                            result += resultSet.getString("sendid") + " : " + resultSet.getString("msg") + "\n";
                        }
                        if (result != Msg) {
                            Msg = result;
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                screen.setContent(new Text(Msg));
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null & connection != null) {
                            statement.close();
                            connection.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getChat.start();
        chat.setLayoutX(14);
        chat.setLayoutY(345);
        chat.setPrefHeight(50);
        chat.setPrefWidth(330);
        send = new Button();
        send.setLayoutX(290);
        send.setLayoutY(354);
        send.setText("SEND");
        send.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection connection = null;
                        Statement statement = null;
                        String getChat = chat.getText();
                        if (!getChat.equals("")) {
                            try {
                                connection = DriverManager.getConnection(CONNECTION, ADMIN, PASSWORD);
                                statement = connection.createStatement();
                                String query = CHAT + "('" + account + "','" + friend + "','" + chat.getText() + "')";
                                statement.execute(query);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        chat.setText("");
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (statement != null & connection != null) {
                                        statement.close();
                                        connection.close();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }
        });
        chatWindow.setScene(new Scene(layout, 360, 400));
        layout.getChildren().

                addAll(screen, chat, send);
        chatWindow.setTitle(friend);
        chatWindow.show();
    }
}
