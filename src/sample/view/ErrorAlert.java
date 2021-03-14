package sample.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

import java.awt.*;

public class ErrorAlert {

    public ErrorAlert(String Msg) {
        alertMsg(Msg);
    }

    public void alertMsg(String Msg) {
        Stage stage = new Stage();
        Text msg = new Text(Msg);
        FontAwesomeIconView icon = new FontAwesomeIconView();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 310, 130);
        stage.setScene(scene);
        stage.setTitle("Error!");
        msg.setStyle("-fx-fill: #bc2020;-fx-font-size: 20");
        anchorPane.setStyle("-fx-background-color: #017cc1");
        msg.setLayoutX(78);
        msg.setLayoutY(74);
        icon.setSize("40");
        icon.setLayoutX(39);
        icon.setLayoutY(78);
        icon.setFill(Paint.valueOf("#bc2020"));
        icon.setGlyphName("EXCLAMATION_CIRCLE");
        anchorPane.getChildren().add(msg);
        anchorPane.getChildren().add(icon);
        stage.show();
    }
}

