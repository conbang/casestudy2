package sample.view.manager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.model.Dbconnectable;
import sample.view.ErrorAlert;
import sample.view.chat.ChatWindow;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Manager implements Dbconnectable {
    String username;
    ErrorAlert errorAlert;
    public Manager(String username) {
        this.username = username;
    }

    public void createManagerStage() throws Exception {
        Stage stage = new Stage();
        AnchorPane layout = new AnchorPane();
        FontAwesomeIconView avatar = new FontAwesomeIconView();
        avatar.setGlyphName("USER");
        avatar.setFill(Paint.valueOf("#673cb5"));
        avatar.setLayoutY(50);
        avatar.setLayoutX(30);
        avatar.setSize("25");
        Text account = new Text();
        account.setText(username);
        account.setLayoutX(58);
        account.setLayoutY(50);
        account.setStyle("-fx-font-size: 14");
        Text status = new Text();
        status.setText("online");
        status.setLayoutY(73);
        status.setLayoutX(60);
        status.setStyle("-fx-fill: #1ada0c ");
        Circle circle = new Circle();
        circle.setLayoutX(44);
        circle.setLayoutY(70);
        circle.setRadius(7);
        circle.setStroke(Paint.valueOf("black"));
        circle.setFill(Paint.valueOf("#1fff70"));
        TextField search = new TextField();
        search.setLayoutX(26);
        search.setLayoutY(90);
        search.setPromptText("add friend");
        search.setPrefWidth(215);
        search.setPrefHeight(26);
        FontAwesomeIconView addfriend = new FontAwesomeIconView();
        addfriend.setGlyphName("USER_PLUS");
        addfriend.setLayoutX(211);
        addfriend.setLayoutY(111);
        addfriend.setFill(Paint.valueOf("#b2b2b2"));
        addfriend.setSize("22");
        AnchorPane friend = new AnchorPane();
        friend.setPrefWidth(267);
        friend.setPrefHeight(429);
        friend.setLayoutX(0);
        friend.setLayoutY(147);
        friend.setStyle("-fx-background-color: white");
        GridPane listFriend = new GridPane();
        int i = 0;
        System.out.println(getListFriend());
        for (Map.Entry<String,String> entry :getListFriend().entrySet()){
            RowConstraints newRow = new RowConstraints();
            Insets insets = new Insets(0, 0, 0, 30);
            Text friendAccount = new Text(entry.getKey());
            newRow.setPrefHeight(40);
            listFriend.getRowConstraints().add(newRow);
            Circle statusFriend = new Circle();
            if(entry.getValue().equals("online")){
                statusFriend.setFill(Paint.valueOf("#1fff70"));
            }else{
                statusFriend.setFill(Paint.valueOf("#B2B2B2"));
            }
            statusFriend.setRadius(7);
            listFriend.setPadding(insets);
            listFriend.add(statusFriend, 0, i);
            listFriend.add(friendAccount, 1, i);
            friendAccount.setOnMouseClicked(new EventHandler<javafx.event.Event>(){
                @Override
                public void handle(javafx.event.Event event) {
                    ChatWindow chatWindow = new ChatWindow(username,friendAccount.getText());

                }
            });
            i++;
        }
        friend.getChildren().add(listFriend);
        layout.getChildren().addAll(avatar, account, status, circle, search, addfriend, friend);
        layout.setStyle("-fx-background-color: #017cc1");
        stage.setScene(new Scene(layout, 267, 578));
        stage.show();

    }
    public HashMap<String,String> getListFriend() {
        Connection connection = null;
        Statement statement = null;
        HashMap<String,String> listFriend = new HashMap<>();
        try {
            connection = DriverManager.getConnection(CONNECTION, ADMIN, PASSWORD);
            statement = connection.createStatement();
            String query = FINDUSER + "account!='" + username + "'";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                listFriend.put(result.getString("account"),result.getString("status"));
            }
        } catch (Exception e) {
            errorAlert = new ErrorAlert("Check network and try again!");
        } finally {
            try {
                if (connection != null && statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("can't close server");
            }
            return listFriend;
        }
    }

}
