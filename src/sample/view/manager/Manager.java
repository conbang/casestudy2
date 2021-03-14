package sample.view.manager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Manager {
    public void createManagerStage(){
        Stage manager = new Stage();
        try{
            Parent scene = FXMLLoader.load(getClass().getResource("sample/view/manager/manager.fxml"));
        }catch (Exception e){
            e.getMessage();
        }


    }
}
