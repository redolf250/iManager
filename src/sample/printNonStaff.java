package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class printNonStaff
{

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea NonStaffTextareaP;

    @FXML
    private Button btnNonStaffP;

    @FXML
    private Button NonStaffDashP;

    scene changeScene=new scene();

    public void onHomeClick(ActionEvent event)
    {
        try {
            changeScene.getScene(event,"Dashboard/nonStaff.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onSearchClick(ActionEvent event)
    {

    }

    public void printerJob(ActionEvent event)
    {

    }
}
