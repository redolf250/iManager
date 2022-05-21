package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class scene
{
    public void getScene(ActionEvent event, String sceneUrl) throws IOException {
        StackPane stackPane = FXMLLoader.load(getClass().getResource(sceneUrl));
        Scene scene=new Scene(stackPane);
        Stage myStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        myStage.setTitle("iManager");
        myStage.setScene(scene);
        Image image=new Image("sample/IconAsset/school.png");
        myStage.getIcons().add(image);
        myStage.setResizable(false);
        animation show=new animation();
        show.getScene((stackPane),scene);
        myStage.show();
    }
}
